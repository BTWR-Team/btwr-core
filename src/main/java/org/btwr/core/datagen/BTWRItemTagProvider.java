package org.btwr.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.util.Identifier;
import org.btwr.core.item.ModItems;
import org.btwr.core.tag.BTWRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;

import java.util.concurrent.CompletableFuture;

public class BTWRItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BTWRItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addToVanilla();
        addToModTags();
        addToConventionalTags();
    }

    private void addToVanilla() {
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.LEATHER_TANNED_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.LEATHER_TANNED_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.LEATHER_TANNED_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.LEATHER_TANNED_BOOTS);
    }

    private void addToModTags() {
        getOrCreateTagBuilder(BTWRTags.Items.CLAY_ITEMS)
                .add(Items.CLAY_BALL);
                //.add(BTWR_Items.BRICK_UNFIRED);

        getOrCreateTagBuilder(BTWRTags.Items.NORMAL_LEATHERS)
                .add(Items.LEATHER)
                .add(ModItems.LEATHER_CUT);

        getOrCreateTagBuilder(BTWRTags.Items.SCOURED_LEATHERS)
                .add(ModItems.LEATHER_SCOURED)
                .add(ModItems.LEATHER_SCOURED_CUT);

        getOrCreateTagBuilder(BTWRTags.Items.TANNED_LEATHERS)
                .add(ModItems.LEATHER_TANNED)
                .add(ModItems.LEATHER_TANNED_CUT);

        getOrCreateTagBuilder(BTWRTags.Items.CUT_LEATHERS)
                .add(ModItems.LEATHER_CUT)
                .add(ModItems.LEATHER_SCOURED_CUT)
                .add(ModItems.LEATHER_TANNED_CUT);

        getOrCreateTagBuilder(BTWRTags.Items.BUOYANT_ITEMS)
                .addOptionalTag(Identifier.of("self_sustainable", "primitive_firestarters"))
                .forceAddTag(ConventionalItemTags.SEEDS)
                .forceAddTag(ConventionalItemTags.TOOLS)
                .addOptional(Identifier.of("bwt", "hemp"))
                .addOptional(Identifier.of("bwt", "gear"))
                .addOptional(Identifier.of("bwt", "flour"))
                .addOptional(Identifier.of("bwt", "hemp_fiber"))
                .addOptional(Identifier.of("bwt", "scoured_leather"))
                .addOptional(Identifier.of("bwt", "sail"))
                .addOptional(Identifier.of("bwt", "fabric"))
                .addOptional(Identifier.of("bwt", "tanned_leather"))
                .addOptional(Identifier.of("bwt", "strap"))
                .addOptional(Identifier.of("bwt", "belt"))
                .addOptional(Identifier.of("bwt", "wood_blade"))
                .addOptional(Identifier.of("bwt", "tallow"))
                .addOptional(Identifier.of("bwt", "haft"))
                .addOptional(Identifier.of("bwt", "padding"))
                .addOptional(Identifier.of("bwt", "unfired_urn"))
                .addOptional(Identifier.of("bwt", "saw_dust"))
                .addOptional(Identifier.of("bwt", "soul_dust"))

                // Uncomment the stake whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt", "stake"))

                .addOptional(Identifier.of("bwt_hct",  "fuse"))

                // Uncomment the witch wart whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt",  "witch_wart"))

                .addTag(BTWRTags.Items.CUT_LEATHERS)

                .addOptional(Identifier.of("vegehenna", "pastry_uncooked_cake"))
                .addOptional(Identifier.of("vegehenna", "pastry_uncooked_cookies"))
                .addOptional(Identifier.of("vegehenna", "pastry_uncooked_pumpkin_pie"))
                .addOptional(Identifier.of("vegehenna", "bread_dough"))
                .addOptional(Identifier.of("vegehenna", "sugar_cane_roots"))
                .addOptional(Identifier.of("vegehenna", "sugar_cane_roots"))
                .addOptional(Identifier.of("nomads_rest", "bedrolls"))
                .addOptional(Identifier.of("animageddon", "tangled_web"))
                .forceAddTag(ItemTags.BEDS)

                // Uncomment the ancient prophecy whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt",  "ancient_prophecy"))

                // Uncomment the arcane scroll/arcane tome whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt",  "arcane_scroll"))

                // Uncomment the gimp suit whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt",  "gimp_suit"))

                .add(Items.LEATHER_HELMET)
                .add(Items.LEATHER_CHESTPLATE)
                .add(Items.LEATHER_LEGGINGS)
                .add(Items.LEATHER_BOOTS)

                // Uncomment the padded armor whenever we add it to any of the mods
                //.add(Items.PADDED_HELMET)
                //.add(Items.PADDED_CHESTPLATE)
                //.add(Items.PADDED_LEGGINGS)
                //.add(Items.PADDED_BOOTS)

                .add(ModItems.LEATHER_TANNED_HELMET)
                .add(ModItems.LEATHER_TANNED_CHESTPLATE)
                .add(ModItems.LEATHER_TANNED_LEGGINGS)
                .add(ModItems.LEATHER_TANNED_BOOTS)

                .addOptional(Identifier.of("self_sustainable",  "wool_helmet"))
                .addOptional(Identifier.of("self_sustainable",  "wool_chestplate"))
                .addOptional(Identifier.of("self_sustainable",  "wool_leggings"))
                .addOptional(Identifier.of("self_sustainable",  "wool_boots"))

                .add(Items.ARROW)
                .add(Items.SPECTRAL_ARROW)
                .add(Items.TIPPED_ARROW)

                .addOptionalTag(Identifier.of("sturdy_trees", "bark_items"))

                // Uncomment the bone carving item whenever we add it to any of the mods
                //.add(Identifier.of("animageddon", "bone_carving"))

                .add(Items.BONE)

                // Uncomment the breeding harness item whenever we add it to any of the mods
                //.add(Identifier.of("animageddon", "breeding_harness"))

                .forceAddTag(ItemTags.CANDLES)

                .addOptional(Identifier.of("bwt",  "canvas"))

                .addOptional(Identifier.of("tough_environment",  "chisel_wood"))
                .add(ModItems.CLUB_WOOD)
                .add(ModItems.CLUB_BONE)

                .forceAddTag(ItemTags.WOODEN_DOORS)

                .addOptional(Identifier.of("bwt", "dung"))
                .addOptional(Identifier.of("bwt", "dynamite"))

                .addOptional(Identifier.of("self_sustainable", "knitting"))
                .addOptional(Identifier.of("self_sustainable", "knitting_needles"))

                // Uncomment the mysterious gland item whenever we add it to any of the mods
                //.addOptional(Identifier.of("animageddon", "mysterious_gland"))

                .addOptional(Identifier.of("bwt", "nether_groth"))
                .addOptional(Identifier.of("bwt", "rope"))

                .add(Items.STICK)

                // Uncomment the sinew extracting item whenever we add it to any of the mods
                //.addOptional(Identifier.of("animageddon", "sinew_extracting"))

                .addOptional(Identifier.of("bwt", "soap"))

                .addOptional(Identifier.of("btwr_ds", "soul_flux"))
                .addOptional(Identifier.of("bwt", "soul_urn"))

                .addOptional(Identifier.of("vegehenna", "straw"))
                .addOptional(Identifier.of("sturdy_trees", "stump_remover"))

                // Uncomment the vertical windmill item whenever we add it to any of the mods
                //.addOptional(Identifier.of("bwt", "vertical_windmill"))

                .addOptional(Identifier.of("bwt", "water_wheel"))
                .addOptional(Identifier.of("animageddon", "web_untangling"))
                .add(Items.WHEAT)
                .addOptional(Identifier.of("self_sustainable", "wicker"))
                .addOptional(Identifier.of("self_sustainable", "wicker_weaving"))

                .addOptional(Identifier.of("bwt", "windmill"))


                // Maybe wool blocks shouldn't be here? Should they?
                //.forceAddTag(ItemTags.WOOL)
                .addOptionalTag(Identifier.of("self_sustainable", "wool_items"))
                .addOptionalTag(Identifier.of("self_sustainable", "wool_knit_items"))

                .addOptional(Identifier.of("bwt", "grate"))
                .addOptional(Identifier.of("bwt", "slats"))
                .addOptional(Identifier.of("bwt", "wicker"))

                .add(Items.BOWL)
                .add(Items.COBWEB)
                .add(Items.FEATHER)
                .add(Items.PAINTING)
                .add(Items.SADDLE)
                .add(Items.LEATHER)
                .add(Items.SUGAR_CANE)
                .add(Items.PAPER)
                .add(Items.SUGAR)
                .add(Items.CAKE)
                .add(Items.GLASS_BOTTLE)
                .add(Items.WRITABLE_BOOK)
                .add(Items.WRITTEN_BOOK)
                .add(Items.ITEM_FRAME)
                .add(Items.GLOW_ITEM_FRAME)
                .add(Items.FLOWER_POT)
                .forceAddTag(ItemTags.SKULLS)
                .forceAddTag(ItemTags.BOATS)
                .add(Items.BOOK)
                .add(Items.BOW)
                .add(Items.CARROT_ON_A_STICK)
                .add(Items.MAP)
                .add(Items.ENCHANTED_BOOK)
                .add(Items.FISHING_ROD)

                //.forceAddTag(ConventionalItemTags.FOODS)
                // Foods (Excluding the tag because it contains golden foods(which should sink)
                .add(Items.BAKED_POTATO)
                .add(Items.PUMPKIN_PIE)
                .add(Items.HONEY_BOTTLE)
                .add(Items.OMINOUS_BOTTLE)
                .add(Items.DRIED_KELP)
                // the fruit tag adds golden foods too
                //.forceAddTag(ConventionalItemTags.FRUIT_FOODS)
                .add(Items.APPLE)
                .add(Items.CHORUS_FRUIT)
                .add(Items.MELON_SLICE)
                //.forceAddTag(ConventionalItemTags.VEGETABLE_FOODS)
                .add(Items.CARROT)
                .add(Items.POTATO)
                .add(Items.BEETROOT)
                .forceAddTag(ConventionalItemTags.BERRY_FOODS)
                .forceAddTag(ConventionalItemTags.BREAD_FOODS)
                .forceAddTag(ConventionalItemTags.COOKIE_FOODS)
                .forceAddTag(ConventionalItemTags.DOUGH_FOODS)
                .forceAddTag(ConventionalItemTags.RAW_MEAT_FOODS)
                .forceAddTag(ConventionalItemTags.RAW_FISH_FOODS)
                .forceAddTag(ConventionalItemTags.COOKED_MEAT_FOODS)
                .forceAddTag(ConventionalItemTags.COOKED_FISH_FOODS)
                .forceAddTag(ConventionalItemTags.SOUP_FOODS)
                .forceAddTag(ConventionalItemTags.CANDY_FOODS)
                .forceAddTag(ConventionalItemTags.PIE_FOODS)
                .forceAddTag(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
                .forceAddTag(ConventionalItemTags.FOOD_POISONING_FOODS)

                .add(Items.FILLED_MAP)
                .forceAddTag(ItemTags.SIGNS)
                .forceAddTag(ItemTags.HANGING_SIGNS)
                .add(Items.SNOWBALL);

        getOrCreateTagBuilder(BTWRTags.Items.NEUTRAL_BUOYANT_ITEMS)
                .add(Items.SLIME_BALL)
                .add(Items.SPIDER_EYE)
                .add(Items.FERMENTED_SPIDER_EYE)
                .add(Items.MAGMA_CREAM)
                .add(Items.POTION);

        //getOrCreateTagBuilder(BTWRTags.Items.NON_BUOYANT_ITEMS)
                //.add(Items.GOLDEN_APPLE)
                //.add(Items.ENCHANTED_GOLDEN_APPLE)
                //.add(Items.GOLDEN_CARROT);
    }

    private void addToConventionalTags() {
        // Fabric Conventional Tags
        getOrCreateTagBuilder(ConventionalItemTags.SHEAR_TOOLS)
                .add(ModItems.DIAMOND_SHEARS);

        // BTWR Added Conventional Tags
        getOrCreateTagBuilder(BTWRConventionalTags.Items.DO_KNOCKBACK_ITEMS)
                .add(ModItems.CLUB_WOOD)
                .add(ModItems.CLUB_BONE);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.DIAMOND_TOOLS)
                .add(ModItems.DIAMOND_SHEARS);

        // Crafting sound tags
        getOrCreateTagBuilder(BTWRConventionalTags.Items.ON_CRAFT_WOODEN_TOOL_SOUND)
                .forceAddTag(BTWRConventionalTags.Items.WOODEN_TOOLS);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.ON_CRAFT_STONE_TOOL_SOUND)
                .forceAddTag(BTWRConventionalTags.Items.STONE_TOOLS);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.ON_CRAFT_METALLIC_TOOL_SOUND)
                .forceAddTag(BTWRConventionalTags.Items.IRON_TOOLS)
                .forceAddTag(BTWRConventionalTags.Items.GOLDEN_TOOLS)
                .forceAddTag(BTWRConventionalTags.Items.DIAMOND_TOOLS)
                .forceAddTag(BTWRConventionalTags.Items.NETHERITE_TOOLS);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.ON_CRAFT_SLIME_SOUND)
                .addTag(BTWRTags.Items.CLAY_ITEMS)
                .add(ModItems.DIAMOND_INGOT);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.ON_CRAFT_SHEARS_CUT_SOUND)
                .addTag(BTWRTags.Items.CUT_LEATHERS);
    }

}