package org.btwr.core.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.world.World;
import org.btwr.core.config.ModConfig;
import org.btwr.core.data.ModDataAttachments;
import org.btwr.core.data.attached.ItemBuoyancyData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {

    @Unique private final ItemBuoyancyData buoyancyData = this.getAttachedOrCreate(ModDataAttachments.ITEM_BUOYANCY);

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyWaterBuoyancy", at = @At("HEAD"), cancellable = true)
    private void beforeApplyWaterBuoyancy(CallbackInfo ci) {
        if (ModConfig.hardcoreBuoy.get()) {
            ItemEntity self = (ItemEntity)(Object) this;
            if (buoyancyData != null) {
                buoyancyData.tick(self);
            }
            ci.cancel();
        }
    }

}