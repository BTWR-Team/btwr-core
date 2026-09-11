package org.btwr.core.util;

import net.minecraft.block.entity.BeaconBlockEntity;
import org.btwr.core.api.world.BlightSpreadRegistry;
import org.btwr.core.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.btwr.core.mixin.accessor.BeaconBlockEntityAccessor;

public class BlightSpreader {

    private static final BlightSpreader INSTANCE = new BlightSpreader();

    private BlightSpreader() {}

    public static BlightSpreader getInstance() {
        return INSTANCE;
    }

    private static final int BLIGHT_SPREAD_RANGE = 3;
    private static final double BLIGHT_SPREAD_RANGE_SQ = ((double) BLIGHT_SPREAD_RANGE * (double) BLIGHT_SPREAD_RANGE);
    private static final int SOULFORGED_BEACON_BLIGHT_SPREAD_FREQUENCY = 10000;
    public static final int[] soulforgedBeaconBlightSpreadRange = new int[]{0, 8, 16, 32, 64};

    public void spreadBlightInAreaFromSkull(WitherSkullEntity entity) {
        int centerX = MathHelper.floor(entity.getPos().getX());
        int centerY = MathHelper.floor(entity.getPos().getY());
        int centerZ = MathHelper.floor(entity.getPos().getZ());

        for (int tempX = centerX - BLIGHT_SPREAD_RANGE; tempX <= centerX + BLIGHT_SPREAD_RANGE; tempX++) {
            for (int tempY = centerY - BLIGHT_SPREAD_RANGE; tempY <= centerY + BLIGHT_SPREAD_RANGE; tempY++) {
                for (int tempZ = centerZ - BLIGHT_SPREAD_RANGE; tempZ <= centerZ + BLIGHT_SPREAD_RANGE; tempZ++) {
                    double deltaX = tempX - centerX;
                    double deltaY = tempY - centerY;
                    double deltaZ = tempZ - centerZ;

                    double distSq = (deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ);

                    if (distSq <= BLIGHT_SPREAD_RANGE_SQ) {
                        BlockPos spreadPos = new BlockPos(tempX, tempY, tempZ);
                        attemptSpreadBlightToBlock(entity, spreadPos);
                    }
                }
            }
        }
    }

    public void checkForBlightSpreadFromBeacon(BeaconBlockEntity blockEntity) {
        World world = blockEntity.getWorld();

        if (world == null) return;

        if (world.getRandom().nextInt(SOULFORGED_BEACON_BLIGHT_SPREAD_FREQUENCY) == 0) {
            BeaconBlockEntityAccessor access = (BeaconBlockEntityAccessor)blockEntity;
            int level = access.getLevel();

            if (level == 0) return; // beacon not active, no spread

            int range = soulforgedBeaconBlightSpreadRange[level];

            BlockPos blockEntityPos = blockEntity.getPos();

            int x = blockEntityPos.getX() + world.getRandom().nextInt(range * 2 + 1) - range;
            int y = world.getRandom().nextInt(256);
            int z = blockEntityPos.getZ() + world.getRandom().nextInt(range * 2 + 1) - range;

            BlockPos spreadPos = new BlockPos(x, y, z);
            BlockState state = world.getBlockState(spreadPos);

            if (BlightSpreadRegistry.canBlightSpreadTo(world, spreadPos, state, 0)) {
                world.setBlockState(spreadPos, ModBlocks.BLIGHT.getDefaultState());
            }
        }
    }

    private void attemptSpreadBlightToBlock(WitherSkullEntity entity, BlockPos pos) {
        World world = entity.getWorld();
        BlockState state = world.getBlockState(pos);

        if (BlightSpreadRegistry.canBlightSpreadTo(world, pos, state, 0)) {
            BlockState aboveState = world.getBlockState(pos.up());

            if (aboveState.getOpacity(world, pos.up()) <= 2) {
                world.setBlockState(pos, ModBlocks.BLIGHT.getDefaultState(), Block.NOTIFY_ALL);
            }
        }
    }

}