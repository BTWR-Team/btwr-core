package org.btwr.core.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends HostileEntity {
    protected AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
    @Inject(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 3))
    private void onInitGoals(CallbackInfo ci) {
        AbstractSkeletonEntity self = (AbstractSkeletonEntity)(Object)this;
        World world = self.getWorld();

        if (world.btwr$difficulty().get(ModDifficulties.SHOULD_SKELETONS_SEEK_SPIDER_MOUNTS)) {
            goalSelector.add(4, new SeekMountGoal(self, SpiderEntity.class, 1.0F, 16F));
        }
    }
    **/
}