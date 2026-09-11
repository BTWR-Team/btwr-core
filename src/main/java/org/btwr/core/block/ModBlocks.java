package org.btwr.core.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.btwr.core.BTWRMod;
import org.btwr.core.block.blocks.BlightBlock;
import org.btwr.core.block.blocks.BlightRootsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.btwr.core.block.blocks.PlacedStickBlock;

public class ModBlocks {

    public static final Block BLIGHT = registerBlock("blight", new BlightBlock(
            AbstractBlock.Settings.create()
                    .strength(0.6F)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRAVEL)
            )
    );

    public static final Block BLIGHT_ROOTS = registerWithoutItem("blight_roots", new BlightRootsBlock(
            AbstractBlock.Settings.copy(ModBlocks.BLIGHT))
    );

    public static final Block FLINT_BLOCK = registerBlock("flint_block", new Block(
                    AbstractBlock.Settings.create()
                            .strength(2F)
                            .sounds(BlockSoundGroup.STONE)
    ));

    public static final Block DIAMOND_INGOT_BLOCK = registerBlock("diamond_ingot_block", new Block(
            AbstractBlock.Settings.create()
                    .strength(5F, 10F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()
    ));

    public static final Block SPIDER_EYE_BLOCK = registerBlock("spider_eye_block", new Block(
            AbstractBlock.Settings.create()
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.HONEY)
                    .requiresTool()
    ));

    public static final Block SPIDER_EYE_SLAB = registerBlock("spider_eye_slab", new SlabBlock(
            AbstractBlock.Settings.create()
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.HONEY)
                    .requiresTool()
    ));

    public static final Block PLACED_STICK = registerBlock("placed_stick", new PlacedStickBlock(
            AbstractBlock.Settings.create()
                    .noCollision()
                    .strength(0.0F, 0.0F)
                    .sounds(BlockSoundGroup.WOOD)
    ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(BTWRMod.MOD_ID, name), block);
    }

    private static Block registerWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(BTWRMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(BTWRMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void initialize() {
        BTWRMod.LOGGER.info("Registering mod blocks for {}", BTWRMod.MOD_ID);
        addToItemGroups();
    }

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            addBlightToItemGroups(content);
            content.addAfter(Blocks.BEDROCK, FLINT_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Blocks.DIAMOND_BLOCK, DIAMOND_INGOT_BLOCK);
            content.add(SPIDER_EYE_BLOCK);
            content.addAfter(SPIDER_EYE_BLOCK, SPIDER_EYE_SLAB);
        });
    }

    private static void addBlightToItemGroups(FabricItemGroupEntries content) {
        // Normal Blight
        content.addAfter(Blocks.GRASS_BLOCK, BLIGHT);

        // Mature Blight
        ItemStack matureBlight = new ItemStack(BLIGHT);
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("level", 3);
        matureBlight.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        MutableText name = Text.translatable("block.btwr.blight.mature").styled(style -> style.withItalic(false));
        matureBlight.set(DataComponentTypes.CUSTOM_NAME, name);
        content.addAfter(BLIGHT, matureBlight);
    }

}