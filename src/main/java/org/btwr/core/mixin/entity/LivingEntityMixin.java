package org.btwr.core.mixin.entity;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.btwr.core.config.ModConfig;
import org.btwr.core.util.HeadDropHandler;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "takeKnockback", at = @At("HEAD"), cancellable = true)
    private void modifyKnockback(double strength, double x, double z, CallbackInfo ci) {
        if (!ModConfig.knockbackRestrictions.get()) return;

        LivingEntity self = (LivingEntity)(Object)this;

        if (self.getAttacker() instanceof PlayerEntity player) {
            ItemStack weaponStack = player.getMainHandStack();

            // Sprinting allows knockback
            if (player.isSprinting()) return;

            // Only the items in the tag can knockback
            if (weaponStack.isIn(BTWRConventionalTags.Items.DO_KNOCKBACK_ITEMS)) return;

            // Otherwise cancel
            ci.cancel();
        }
    }

    @Inject(method = "drop", at = @At("TAIL"))
    private void checkForHeadDrop(ServerWorld world, DamageSource damageSource, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (!self.isBaby() && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            HeadDropHandler.checkForHeadDrop(self, damageSource);
        }
    }

    /**
    @ModifyVariable(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("HEAD"), argsOnly = true)
    private float halveCreeperExplosionDamage(float amount, DamageSource source) {
        LivingEntity self = (LivingEntity)(Object)this;
        World world = self.getWorld();

        if (world.isClient) return amount;

        boolean halvedEntities = self instanceof CreeperEntity || self instanceof ZombieEntity;
        boolean shouldApply = halvedEntities && source.isIn(DamageTypeTags.IS_EXPLOSION);

        if (shouldApply && world.btwr$difficulty().get(ModDifficulties.CAN_CREEPERS_BREACH_WALLS)) {
            return amount / 2.0F;
        }
        return amount;
    }
    **/

}