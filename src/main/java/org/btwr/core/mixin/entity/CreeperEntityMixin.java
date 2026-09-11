package org.btwr.core.mixin.entity;

import org.btwr.core.config.ModConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    @Unique private byte btwr$patienceCounter = 100;
    @Unique private boolean btwr$determinedToExplode = false;

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // Makes creeper explosion get calculated from the eyes instead of its feet
    @ModifyArg(method = "explode", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), index = 2)
    private double injected(double y) {
        boolean shouldModify = ModConfig.changedCreeperExplosionPos.get();
        return shouldModify ? this.getEyeY() : y;
    }

    //@Inject(method = "initGoals", at = @At("TAIL"))
    private void btwr$addWallBreachTargetGoal(CallbackInfo ci) {
        CreeperEntity self = (CreeperEntity)(Object)this;
        World world = self.getWorld();

        ///if (self.getWorld() != null && world.btwr$difficulty().get(ModDifficulties.CAN_CREEPERS_BREACH_WALLS)) {
            //this.targetSelector.add(3, new ActiveTargetGoal<>(self, PlayerEntity.class, false));
        //}
    }

    //@Inject(method = "tick", at = @At("HEAD"))
    private void btwr$updateWallBreachPatience(CallbackInfo ci) {
        CreeperEntity self = (CreeperEntity)(Object)this;
        World world = self.getWorld();

        if (world.isClient) return;

        //if (!world.btwr$difficulty().get(ModDifficulties.CAN_CREEPERS_BREACH_WALLS)) return;

        if (self.getTarget() == null) {
            if (self.getRandom().nextInt(20) == 0) {
                btwr$patienceCounter = (byte) Math.min(btwr$patienceCounter + 1, 100);
            }
        } else if (self.squaredDistanceTo(self.getTarget()) < 36.0D
                && !self.canSee(self.getTarget())
                && self.getNavigation().isIdle()
        ) {
            btwr$patienceCounter = (byte) Math.min(btwr$patienceCounter - 1, 100);
        }

        if (btwr$patienceCounter == 0) {
            btwr$determinedToExplode = true;
        }
    }
}