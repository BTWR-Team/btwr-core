package org.btwr.core.mixin.world;

import net.minecraft.server.world.ServerWorld;
import org.btwr.core.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @ModifyConstant(method = "tickChunk", constant = @Constant(intValue = 100000))
    private int modifyLightningChance(int constant) {
        if (ModConfig.increasedLightningStrikeChance.get()) {
            return 50000;
        }

        return constant;
    }
}