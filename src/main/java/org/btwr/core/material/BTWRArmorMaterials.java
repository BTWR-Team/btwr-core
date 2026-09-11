package org.btwr.core.material;

import org.btwr.core.BTWRMod;
import org.btwr.core.item.ModItems;
import org.btwr.core.tag.BTWRTags;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class BTWRArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> LEATHER_TANNED = register("leather_tanned",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 7);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.fromTag(BTWRTags.Items.TANNED_LEATHERS),
                    List.of(new ArmorMaterial.Layer(Identifier.of(BTWRMod.MOD_ID, "leather_tanned"))), 0, 0)
    );

    public static final RegistryEntry<ArmorMaterial> ENDER_SPECTACLES = register("ender_spectacles",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 0);
            }), 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(ModItems.OCULAR_OF_ENDER),
                    List.of(new ArmorMaterial.Layer(Identifier.of(BTWRMod.MOD_ID, "ender_spectacles"))), 0, 0)
    );

    public static RegistryEntry<ArmorMaterial> register(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(BTWRMod.MOD_ID, name), material.get());
    }

}