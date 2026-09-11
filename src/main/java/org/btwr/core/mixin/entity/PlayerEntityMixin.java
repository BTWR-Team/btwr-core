package org.btwr.core.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import org.btwr.core.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyReturnValue(method = "shouldRenderName", at = @At("RETURN"))
    private boolean hidePlayerNametag(boolean original) {
        if (ModConfig.hardcorePlayerNames.get()) {
            return false;
        }

        return original;
    }
}
