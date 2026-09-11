package org.btwr.core.mixin.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.btwr.shared_library.api.block.util.FireBlockUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract boolean isOnFire();
    @Shadow private Box boundingBox;
    @Shadow private World world;

    /**
    @Inject(method = "baseTick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            shift = At.Shift.AFTER
    ))
    private void afterOnFireDamage(CallbackInfo ci) {
        if (this.isOnFire() && this.world instanceof ServerWorld) {
            if (world.btwr$difficulty().get(ModDifficulties.SHOULD_BURNING_ENTITIES_SET_FIRES)) {
                this.tryToSetFireToBlocksInContact();
            }
        }
    }
    **/

    @Unique
    public void tryToSetFireToBlocksInContact() {
        Box boundingBox = this.boundingBox.expand(0.1D, 0.1D, 0.1D);
        int minX = MathHelper.floor(boundingBox.minX);
        int minY = MathHelper.floor(boundingBox.minY);
        int minZ = MathHelper.floor(boundingBox.minZ);
        int maxX = MathHelper.floor(boundingBox.maxX);
        int maxY = MathHelper.floor(boundingBox.maxY);
        int maxZ = MathHelper.floor(boundingBox.maxZ);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    BlockState state = this.world.getBlockState(new BlockPos(x, y, z));
                    if (state == null || FireBlockUtils.canFireReplaceBlock(world, new BlockPos(x, y, z))) {
                        FireBlockUtils.checkForFireSpreadAndDestructionToOneBlockLocation(world, new BlockPos(x, y, z));
                    }
                }
            }
        }
    }


}