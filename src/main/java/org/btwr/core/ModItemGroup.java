package org.btwr.core;

import org.btwr.core.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP,
                Identifier.of(BTWRMod.MOD_ID, "group_btwr"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemgroup.group_btwr"))
                        .icon(() -> new ItemStack(ModItems.GROUP_BTWR))
                        .entries((displayContext, entries) -> {

                            // Uncategorized
                            entries.add(ModItems.DIAMOND_INGOT);
                            entries.add(ModItems.DIAMOND_PLATE);
                            entries.add(ModItems.LEATHER_SCOURED);
                            entries.add(ModItems.LEATHER_TANNED);
                            entries.add(ModItems.LEATHER_CUT);
                            entries.add(ModItems.LEATHER_SCOURED_CUT);
                            entries.add(ModItems.LEATHER_TANNED_CUT);
                            entries.add(ModItems.OCULAR_OF_ENDER);
                            entries.add(ModItems.ENDER_SPECTACLES);

                            // Tools
                            entries.add(ModItems.DIAMOND_SHEARS);
                            entries.add(ModItems.CLUB_BONE);
                            entries.add(ModItems.CLUB_WOOD);

                            // Armor
                            entries.add(ModItems.LEATHER_TANNED_HELMET);
                            entries.add(ModItems.LEATHER_TANNED_CHESTPLATE);
                            entries.add(ModItems.LEATHER_TANNED_LEGGINGS);
                            entries.add(ModItems.LEATHER_TANNED_BOOTS);

                            // Food
                            entries.add(ModItems.BEAST_LIVER_RAW);
                            entries.add(ModItems.BEAST_LIVER_COOKED);
                        }).build());
    }

}