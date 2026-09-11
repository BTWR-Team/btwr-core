package org.btwr.core.event.events;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.btwr.core.item.ModItems;

public class ModItemGroupEvents {
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItemGroupEvents::modifyToolsGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemGroupEvents::modifyIngredientsGroup);
    }

    private static void modifyToolsGroup(FabricItemGroupEntries entries) {
        entries.addAfter(Items.IRON_INGOT, ModItems.DIAMOND_INGOT);
    }

    private static void modifyIngredientsGroup(FabricItemGroupEntries entries) {
        // Add the diamond plate before BWT's armor plate if BWT is present, else just register it at the bottom
        if (FabricLoader.getInstance().isModLoaded("bwt")) {
            Item netheriteArmorPlate = Registries.ITEM.get(Identifier.of("bwt", "armor_plate"));
            entries.addBefore(netheriteArmorPlate, ModItems.DIAMOND_PLATE);
        } else {
            entries.add(ModItems.DIAMOND_PLATE);
        }
    }
}