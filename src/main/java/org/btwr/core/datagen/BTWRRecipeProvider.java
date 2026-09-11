package org.btwr.core.datagen;

import org.btwr.core.BTWRMod;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.item.ModItems;
import org.btwr.core.tag.BTWRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.btwr.shared_library.recipe.ExtendedShapelessRecipe;

import java.util.concurrent.CompletableFuture;

public class BTWRRecipeProvider extends FabricRecipeProvider {

    public BTWRRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // Tools
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLUB_BONE).input('#', Items.BONE).pattern("#").pattern("#").criterion("has_bone", RecipeProvider.conditionsFromItem(Items.BONE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLUB_WOOD).input('#', Items.STICK).pattern("#").pattern("#").criterion("has_stick", RecipeProvider.conditionsFromItem(Items.STICK)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_SWORD).input('D', ModItems.DIAMOND_INGOT).input('S', Items.STICK).pattern("D").pattern("D").pattern("S").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_sword_from_ingots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.DIAMOND_PICKAXE).input('D', ModItems.DIAMOND_INGOT).input('S', Items.STICK).pattern("DDD").pattern(" S ").pattern(" S ").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_pickaxe_from_ingots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.DIAMOND_SHOVEL).input('D', ModItems.DIAMOND_INGOT).input('S', Items.STICK).pattern("D").pattern("S").pattern("S").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_shovel_from_ingots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.DIAMOND_AXE).input('D', ModItems.DIAMOND_INGOT).input('S', Items.STICK).pattern("DD ").pattern("DS ").pattern(" S ").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_axe_from_ingots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.DIAMOND_HOE).input('D', ModItems.DIAMOND_INGOT).input('S', Items.STICK).pattern("DD ").pattern(" S ").pattern(" S ").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_hoe_from_ingots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIAMOND_SHEARS).input('#', ModItems.DIAMOND_INGOT).pattern(" #").pattern("# ").criterion("has_diamond_ingot", RecipeProvider.conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);

        // Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.LEATHER_HELMET).input('X', ModItems.LEATHER_CUT).pattern("XXX").pattern("X X").criterion("has_leather_cut", conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.LEATHER_CHESTPLATE).input('X', ModItems.LEATHER_CUT).pattern("X X").pattern("XXX").pattern("XXX").criterion("has_leather_cut", conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.LEATHER_LEGGINGS).input('X', ModItems.LEATHER_CUT).pattern("XXX").pattern("X X").pattern("X X").criterion("has_leather_cut", conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.LEATHER_BOOTS).input('X', ModItems.LEATHER_CUT).pattern("X X").pattern("X X").criterion("has_leather_cut", conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_PLATE,4).input('X', ModItems.DIAMOND_INGOT).input('L', ModItems.LEATHER_TANNED).input('W', ItemTags.WOOL).pattern("LXL").pattern(" W ").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_PLATE,4).input('X', ModItems.DIAMOND_INGOT).input('L', ModItems.LEATHER_TANNED_CUT).input('W', ItemTags.WOOL).pattern("LXL").pattern(" W ").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter, Identifier.of(BTWRMod.MOD_ID, "diamond_plate_from_tanned_leather_cut"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_HELMET).input('X', ModItems.DIAMOND_INGOT).pattern("XXX").pattern("X X").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_CHESTPLATE).input('X', ModItems.DIAMOND_INGOT).input('P', ModItems.DIAMOND_PLATE).pattern("P P").pattern("XXX").pattern("XXX").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_LEGGINGS).input('X', ModItems.DIAMOND_INGOT).input('P', ModItems.DIAMOND_PLATE).pattern("XXX").pattern("P P").pattern("P P").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.DIAMOND_BOOTS).input('X', ModItems.DIAMOND_INGOT).pattern("X X").pattern("X X").criterion("has_diamond_ingot", conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);

        // Misc
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEATHER_HORSE_ARMOR).input('#', ModItems.LEATHER_CUT).pattern("# #").pattern("###").pattern("# #").criterion("has_leather_cut", RecipeProvider.conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ITEM_FRAME).input('#', ModItems.LEATHER_CUT).input('S', Items.STICK).pattern("SSS").pattern("S#S").pattern("SSS").criterion("has_leather_cut", RecipeProvider.conditionsFromItem(ModItems.LEATHER_CUT)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BOOK).input(ModItems.LEATHER_CUT).input(Items.PAPER).input(Items.PAPER).input(Items.PAPER).criterion("has_paper", conditionsFromItem(Items.PAPER)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LEATHER_TANNED_HELMET).input('X', BTWRTags.Items.TANNED_LEATHERS).pattern("XXX").pattern("X X").criterion("has_leather_tanned", conditionsFromItem(ModItems.LEATHER_TANNED)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LEATHER_TANNED_CHESTPLATE).input('X', BTWRTags.Items.TANNED_LEATHERS).pattern("X X").pattern("XXX").pattern("XXX").criterion("has_leather_tanned", conditionsFromItem(ModItems.LEATHER_TANNED)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LEATHER_TANNED_LEGGINGS).input('X', BTWRTags.Items.TANNED_LEATHERS).pattern("XXX").pattern("X X").pattern("X X").criterion("has_leather_tanned", conditionsFromItem(ModItems.LEATHER_TANNED)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LEATHER_TANNED_BOOTS).input('X', BTWRTags.Items.TANNED_LEATHERS).pattern("X X").pattern("X X").criterion("has_leather_tanned", conditionsFromItem(ModItems.LEATHER_TANNED)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OCULAR_OF_ENDER).input('E', Items.ENDER_EYE).input('N', Items.GOLD_NUGGET).pattern("NNN").pattern("NEN").pattern("NNN").criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDER_SPECTACLES).input('O', ModItems.OCULAR_OF_ENDER).input('L', ConventionalItemTags.LEATHERS).pattern("OLO").criterion(hasItem(ModItems.OCULAR_OF_ENDER), conditionsFromItem(ModItems.OCULAR_OF_ENDER)).offerTo(exporter);

        // Shears cutting recipes
        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.LEATHER_CUT,2).withToolDamage().input(Items.LEATHER).input(ConventionalItemTags.SHEAR_TOOLS).criterion("has_leather", conditionsFromItem(Items.LEATHER)).offerTo(exporter);
        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.LEATHER_SCOURED_CUT,2).withToolDamage().input(ModItems.LEATHER_SCOURED).input(ConventionalItemTags.SHEAR_TOOLS).criterion("has_leather_scoured", conditionsFromItem(ModItems.LEATHER_SCOURED)).offerTo(exporter);
        ExtendedShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, ModItems.LEATHER_TANNED_CUT,2).withToolDamage().input(ModItems.LEATHER_TANNED).input(ConventionalItemTags.SHEAR_TOOLS).criterion("has_leather_tanned", conditionsFromItem(ModItems.LEATHER_TANNED)).offerTo(exporter);

        // Other recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_INGOT, 2).input(Items.IRON_INGOT).input(Items.DIAMOND).input(Items.ROTTEN_FLESH).input(Items.RED_MUSHROOM).criterion("has_diamond", RecipeProvider.conditionsFromItem(Items.DIAMOND)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LEATHER_SCOURED).input(Items.LEATHER).input(Items.WATER_BUCKET).criterion("has_water_bucket", RecipeProvider.conditionsFromItem(Items.WATER_BUCKET)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LEATHER_TANNED).input(ModItems.LEATHER_SCOURED).input(ItemTags.LOGS).criterion("has_leather_scoured", RecipeProvider.conditionsFromItem(ModItems.LEATHER_SCOURED)).offerTo(exporter);

        // Blocks
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLINT_BLOCK).input('F', Items.FLINT).pattern("FFF").pattern("FFF").pattern("FFF").criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DIAMOND_INGOT_BLOCK).input('I', ModItems.DIAMOND_INGOT).pattern("III").pattern("III").pattern("III").criterion(hasItem(ModItems.DIAMOND_INGOT), conditionsFromItem(ModItems.DIAMOND_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SPIDER_EYE_BLOCK).input('E', Items.SPIDER_EYE).pattern("EEE").pattern("EEE").pattern("EEE").criterion(hasItem(Items.SPIDER_EYE), conditionsFromItem(Items.SPIDER_EYE)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SPIDER_EYE_SLAB).input('E', ModBlocks.SPIDER_EYE_BLOCK).pattern("EEE").criterion(hasItem(ModBlocks.SPIDER_EYE_BLOCK), conditionsFromItem(ModBlocks.SPIDER_EYE_BLOCK)).offerTo(exporter);
    }



}