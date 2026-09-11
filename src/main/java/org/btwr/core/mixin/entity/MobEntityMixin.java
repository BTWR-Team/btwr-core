package org.btwr.core.mixin.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

    //@Inject(method = "initialize", at = @At("TAIL"))
    private void afterInitialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        // Try turning skeletons underground into withered ones
        MobEntity self = (MobEntity)(Object)this;

        if (!(self instanceof SkeletonEntity skeleton)) {
            return;
        }

        if (world.toServerWorld().getRegistryKey() != World.OVERWORLD) {
            return;
        }

        if (spawnReason != SpawnReason.NATURAL) {
            return;
        }

        ServerWorld serverWorld = world.toServerWorld();
        //if (!serverWorld.btwr$difficulty().get(ModDifficulties.SHOULD_WITHER_SKELETONS_SPAWN_UNDERGROUND)) {
            //return;
        //}

        /** nether accessed check here
        if () {

        }
         **/

        if (skeleton.getY() >= 50.0) {
            return;
        }

        var witherSkeleton = skeleton.convertTo(EntityType.WITHER_SKELETON, false);
        if (witherSkeleton != null) {
            witherSkeleton.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
        }
    }
}
