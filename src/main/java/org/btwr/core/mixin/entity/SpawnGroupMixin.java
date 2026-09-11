package org.btwr.core.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.SpawnGroup;
import org.btwr.core.config.ModConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SpawnGroup.class)
public abstract class SpawnGroupMixin {

    @Shadow @Final private String name;

    @ModifyReturnValue(method = "getCapacity", at = @At("RETURN"))
    private int modifyCapacity(int maxInstancesPerChunk) {
        if (ModConfig.increasedMonsterSpawnsPerChunk.get()) {
            if (this.name.equals(SpawnGroup.MONSTER.getName())) {
                return 90;
            }
        }

        return maxInstancesPerChunk;
    }

}