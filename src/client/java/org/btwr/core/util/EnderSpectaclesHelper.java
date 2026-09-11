package org.btwr.core.util;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import org.btwr.core.item.ModItems;

public class EnderSpectaclesHelper {
    public static boolean isWearingEnderSpectacles(ClientPlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.ENDER_SPECTACLES);
    }
}