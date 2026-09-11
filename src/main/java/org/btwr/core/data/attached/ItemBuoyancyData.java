package org.btwr.core.data.attached;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.btwr.core.tag.BTWRTags;

public record ItemBuoyancyData(float buoyancy) {

    public static ItemBuoyancyData forDefault() {
        return new ItemBuoyancyData(-1.0f);
    }

    public static final Codec<ItemBuoyancyData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("buoyancy").forGetter(ItemBuoyancyData::buoyancy)).apply(instance, ItemBuoyancyData::new)
    );

    public static PacketCodec<ByteBuf, ItemBuoyancyData> PACKET_CODEC = PacketCodecs.codec(CODEC);

    public void tick(ItemEntity itemEntity) {
        Box box = itemEntity.getBoundingBox();
        int numDepthCheks = 10;
        double d = 0.0D;
        double boundingYOffset = 0.10D;
        double boxHeight = box.maxY - box.minY;

        for (int j = 0; j < numDepthCheks; j++) {
            double sliceMinY = box.minY + (boxHeight * j) * 0.375D + boundingYOffset;
            double sliceMaxY = box.minY + (boxHeight * (j + 1)) * 0.375D + boundingYOffset;
            Box slice = new Box(box.minX, sliceMinY, box.minZ, box.maxX, sliceMaxY, box.maxZ);

            if (isBoxTouchingWater(slice, itemEntity)) {
                d += 1.0D / numDepthCheks;
            } else {
                break;
            }
        }

        if (d > 0.001D) {
            Vec3d velocity = itemEntity.getVelocity();

            if (!isInUndertow(itemEntity)) {
                float buoyancyShifted = this.getBouyancyFor(itemEntity.getStack());
                velocity = velocity.add(0.0D, 0.04D * buoyancyShifted * d, 0.0D);
            }

            velocity = new Vec3d(velocity.x * 0.9D, velocity.y * 0.9D, velocity.z * 0.9D);
            itemEntity.setVelocity(velocity);
        }
    }

    private boolean isBoxTouchingWater(Box box, ItemEntity itemEntity) {
        int minX = MathHelper.floor(box.minX);
        int maxX = MathHelper.floor(box.maxX);
        int minY = MathHelper.floor(box.minY);
        int maxY = MathHelper.floor(box.maxY);
        int minZ = MathHelper.floor(box.minZ);
        int maxZ = MathHelper.floor(box.maxZ);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (itemEntity.getWorld().getFluidState(new BlockPos(x, y, z)).isIn(FluidTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isInUndertow(ItemEntity itemEntity) {
        Box box = itemEntity.getBoundingBox();

        int minI = MathHelper.floor(box.minX);
        int maxI = MathHelper.floor(box.maxX + 1.0D);
        int minJ = MathHelper.floor(box.minY);
        int maxJ = MathHelper.floor(box.maxY + 1.0D);
        int minK = MathHelper.floor(box.minZ);
        int maxK = MathHelper.floor(box.minZ + 1.0D);

        for (int i = minI; i < maxI; i++) {
            for (int j = minJ; j < maxJ; j++) {
                for (int k = minK; k < maxK; k++) {
                    if (doesBlockHaveUndertow(new BlockPos(i, j, k), itemEntity)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean doesBlockHaveUndertow(BlockPos pos, ItemEntity itemEntity) {
        return isFallingWaterAt(pos, itemEntity)
                || isFallingWaterAt(pos.down(), itemEntity)
                || isFallingWaterAt(pos.up(), itemEntity);
    }

    private boolean isFallingWaterAt(BlockPos pos, ItemEntity itemEntity) {
        FluidState fluidState = itemEntity.getWorld().getFluidState(pos);
        if (!fluidState.isIn(FluidTags.WATER)) {
            return false;
        }

        return fluidState.contains(FlowableFluid.FALLING) && fluidState.get(FlowableFluid.FALLING);
    }

    public float getBouyancyFor(ItemStack stack) {
        if (stack.isIn(BTWRTags.Items.BUOYANT_ITEMS)) {
            return 1f;
        }

        if (stack.isIn(BTWRTags.Items.NEUTRAL_BUOYANT_ITEMS)) {
            return 0f;
        }

        // Default to non-bouyant for all items outside the tags
        return -1f;
    }

}