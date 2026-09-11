package org.btwr.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetCustomDataLootFunction;
import net.minecraft.loot.function.SetNameLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.block.blocks.BlightBlock;

import java.util.concurrent.CompletableFuture;

public class BTWRLootTableProvider extends FabricBlockLootTableProvider {

    public static final LootCondition.Builder WITH_CONVENTIONAL_SHEARS = MatchToolLootCondition.builder(
            ItemPredicate.Builder.create().tag(ConventionalItemTags.SHEAR_TOOLS)
    );

    public static final LootCondition.Builder WITH_PICKAXE = MatchToolLootCondition.builder(
            ItemPredicate.Builder.create().tag(ItemTags.PICKAXES)
    );

    public BTWRLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.generateVanillaTables();
        this.generateModTables();
    }

    private void generateVanillaTables() {
        addDrop(Blocks.VINE, BTWRLootTableProvider::dropsWithShears);
    }

    private void generateModTables() {
        addDrop(ModBlocks.BLIGHT, this::blightDrops);
        addDrop(ModBlocks.FLINT_BLOCK, this::flintBlockDrops);
        addDrop(ModBlocks.DIAMOND_INGOT_BLOCK);
        addDrop(ModBlocks.SPIDER_EYE_BLOCK);
        addDrop(ModBlocks.SPIDER_EYE_SLAB, this::slabDrops);
        addDrop(ModBlocks.PLACED_STICK, Items.STICK);
    }

    private LootTable.Builder blightDrops(Block block) {
        NbtCompound matureNbt = new NbtCompound();
        matureNbt.putInt("level", 3);

        return LootTable.builder().pool(
                LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(AlternativeEntry.builder(
                                // Level 3 — drop with custom data
                                ItemEntry.builder(ModBlocks.BLIGHT)
                                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                                .properties(StatePredicate.Builder.create()
                                                        .exactMatch(BlightBlock.LEVEL, 3)))
                                        .apply(SetCustomDataLootFunction.builder(matureNbt))
                                        .apply(SetNameLootFunction.builder(
                                                Text.translatable("block.btwr.blight.mature")
                                                        .styled(style -> style.withItalic(false)),
                                                SetNameLootFunction.Target.CUSTOM_NAME)),
                                // All other levels — drop plain blight
                                ItemEntry.builder(ModBlocks.BLIGHT)
                        ))
        );
    }

    private LootTable.Builder flintBlockDrops(Block block) {
        return LootTable.builder().pool(
                LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(AlternativeEntry.builder(
                                // Drop fully with pickaxe
                                ItemEntry.builder(ModBlocks.FLINT_BLOCK).conditionally(WITH_PICKAXE),
                                // Otherwise drop flint
                                ItemEntry.builder(Items.FLINT)
                                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(9)))
                        ))
        );
    }

    public static LootTable.Builder dropsWithShears(ItemConvertible drop) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITH_CONVENTIONAL_SHEARS).with(ItemEntry.builder(drop)));
    }

    @Override
    public String getName() {
        return "BTWR Block Loot Tables";
    }

}