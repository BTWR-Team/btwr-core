package org.btwr.core.mixin.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.config.ModConfig;
import org.btwr.core.entity.SpawnConditions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Inject(method = "allowsSpawning", at = @At("HEAD"), cancellable = true)
    private void setAllowsSpawning(BlockView world, BlockPos pos, EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (!ModConfig.btwMobSpawningLogic.get()) return;

        BlockState state = world.getBlockState(pos);
        cir.setReturnValue(SpawnConditions.allowsSpawning(state, world, pos, type));
    }
}