package org.btwr.core.mixin.entity;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EndermanEntity.class)
public interface EndermanEntityInvoker {
    @Invoker("teleportRandomly") boolean btw$teleportRandomly();
}