package org.btwr.core.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.btwr.core.config.ModConfig;
import org.btwr.core.entity.ai.ZombieBreakBarricadeGoal;
import org.btwr.core.entity.ai.ZombieBreakBarricadeHostileGoal;
import org.btwr.core.entity.ai.ZombieSecondaryTargetPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // Makes baby zombies not able to spawn.
    @Inject(method = "shouldBeBaby", at = @At("HEAD"), cancellable = true)
    private static void setShouldBeBaby(Random random, CallbackInfoReturnable<Boolean> cir) {
        if (!ModConfig.spawnBabyZombies.get()) {
            cir.setReturnValue(false);
        }
    }

    //@Inject(method = "<init>", at = @At("TAIL"))
    private void addZombieGoals(EntityType<? extends ZombieEntity> type, World world, CallbackInfo ci) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        ZombieEntity self = (ZombieEntity)(Object)this;

        MobNavigation nav = (MobNavigation) self.getNavigation();
        nav.setCanPathThroughDoors(true);

        this.setSecondaryTargetPredicate(self);

        //boolean hostileMode = serverWorld != null && serverWorld.btwr$difficulty().get(ModDifficulties.CAN_ZOMBIES_BREAK_BLOCKS);
        //btwr$setAdvancedAI(self, hostileMode);
    }

    @Unique
    private void setSecondaryTargetPredicate(ZombieEntity zombie) {
        ZombieSecondaryTargetPredicate filter = new ZombieSecondaryTargetPredicate(zombie);
        targetSelector.add(
                2,
                new ActiveTargetGoal<>(
                        zombie,
                        LivingEntity.class,
                        0,
                        false,
                        false,
                        filter::test
                )
        );
    }

    @Unique
    private void btwr$setAdvancedAI(ZombieEntity zombie, boolean advanced) {
        MobNavigation navigation = (MobNavigation) zombie.getNavigation();
        navigation.setCanPathThroughDoors(false);
        if (advanced) {
            goalSelector.add(1, new ZombieBreakBarricadeHostileGoal(zombie));
            navigation.setCanPathThroughDoors(true);
            targetSelector.add(
                    2,
                    new ActiveTargetGoal<>(
                            zombie,
                            PlayerEntity.class,
                            false
                    )
            );
        } else {
            goalSelector.add(1, new ZombieBreakBarricadeGoal(zombie));
            targetSelector.add(
                    2,
                    new ActiveTargetGoal<>(
                            zombie,
                            PlayerEntity.class,
                            true
                    )
            );
        }
        targetSelector.add(
                2,
                new ActiveTargetGoal<>(
                        zombie,
                        MobEntity.class,
                        false,
                        entity ->
                                entity instanceof AnimalEntity
                )
        );
    }

}