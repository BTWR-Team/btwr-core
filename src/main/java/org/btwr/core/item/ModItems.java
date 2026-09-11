package org.btwr.core.item;

import org.btwr.core.BTWRMod;
import org.btwr.core.item.items.ClubItem;
import org.btwr.core.material.BTWRArmorMaterials;
import org.btwr.core.material.BTWRToolMaterials;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// This class registers all BTWR items.
public class ModItems {

    // The GROUP_BTWR is first, as it acts as an Item that is called in the BTWRItemGroup class.
    public static final Item GROUP_BTWR = registerItem( "group_btwr", new Item(new Item.Settings()));

    // Diamond variation items
    public static final Item DIAMOND_INGOT = registerItem( "diamond_ingot", new Item (new Item.Settings()));
    public static final Item DIAMOND_PLATE = registerItem("diamond_plate", new Item(new Item.Settings()));

    // Leathers
    public static final Item LEATHER_CUT = registerItem( "leather_cut", new Item (new Item.Settings()));
    public static final Item LEATHER_SCOURED = registerItem( "leather_scoured", new Item (new Item.Settings()));
    public static final Item LEATHER_SCOURED_CUT = registerItem( "leather_scoured_cut", new Item (new Item.Settings()));
    public static final Item LEATHER_TANNED = registerItem( "leather_tanned", new Item (new Item.Settings()));
    public static final Item LEATHER_TANNED_CUT = registerItem("leather_tanned_cut", new Item (new Item.Settings()));

    // Tools
    public static final Item CLUB_WOOD = registerItem("club_wood", new ClubItem(
            ToolMaterials.WOOD,
            new Item.Settings()
                    .attributeModifiers(
                            ClubItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -1.1f
                            )
                    )
            )
    );

    public static final Item CLUB_BONE = registerItem("club_bone", new ClubItem(
            BTWRToolMaterials.BONE,
            new Item.Settings()
                    .attributeModifiers(
                            ClubItem.createAttributeModifiers(BTWRToolMaterials.BONE, 2, -1.3f)
                    )
            )
    );

    public static final Item DIAMOND_SHEARS = registerItem( "diamond_shears",
            new ShearsItem(
                    new Item.Settings()
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, ShearsItem.createToolComponent())
            )
    );
    
    // Armor
    public static final Item LEATHER_TANNED_HELMET = registerItem("leather_tanned_helmet", new ArmorItem(
            BTWRArmorMaterials.LEATHER_TANNED,
            ArmorItem.Type.HELMET,
            new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(7))
    ));

    public static final Item LEATHER_TANNED_CHESTPLATE = registerItem("leather_tanned_chestplate", new ArmorItem(
            BTWRArmorMaterials.LEATHER_TANNED,
            ArmorItem.Type.CHESTPLATE,
            new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(7))
    ));

    public static final Item LEATHER_TANNED_LEGGINGS = registerItem("leather_tanned_leggings", new ArmorItem(
            BTWRArmorMaterials.LEATHER_TANNED,
            ArmorItem.Type.LEGGINGS,
            new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(7))
    ));

    public static final Item LEATHER_TANNED_BOOTS = registerItem("leather_tanned_boots", new ArmorItem(
            BTWRArmorMaterials.LEATHER_TANNED,
            ArmorItem.Type.BOOTS,
            new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(7))
    ));

    // Unique food
    public static final Item BEAST_LIVER_RAW = registerItem( "beast_liver_raw",
            new Item(new Item.Settings().food(ModFoodComponents.BEAST_LIVER_RAW))
    );

    public static final Item BEAST_LIVER_COOKED = registerItem( "beast_liver_cooked",
            new Item(new Item.Settings().food(ModFoodComponents.BEAST_LIVER_COOKED))
    );

    // Misc items
    public static final Item OCULAR_OF_ENDER = registerItem("ocular_of_ender", new Item(new Item.Settings()));

    public static final Item ENDER_SPECTACLES = registerItem("ender_spectacles",
            new ArmorItem(BTWRArmorMaterials.ENDER_SPECTACLES, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(12))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BTWRMod.MOD_ID, name), item);
    }

    public static void initialize() {
        BTWRMod.LOGGER.info("Registering mod items for {}", BTWRMod.MOD_NAME);
        registerFuels();
    }

    // Register fuel items here
    private static void registerFuels() {
        FuelRegistry.INSTANCE.add(ModItems.CLUB_WOOD, 100);
    }

}