package org.btwr.core.datagen;

import net.minecraft.registry.tag.BlockTags;
import org.btwr.core.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BTWRBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public BTWRBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addToModTags();
        addToVanillaTags();
        addToConventionalTags();
    }

    private void addToVanillaTags() {
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.BLIGHT)
                .add(ModBlocks.BLIGHT_ROOTS)
                .add(ModBlocks.SPIDER_EYE_BLOCK)
                .add(ModBlocks.SPIDER_EYE_SLAB);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.FLINT_BLOCK)
                .add(ModBlocks.DIAMOND_INGOT_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DIAMOND_INGOT_BLOCK);
    }

    private void addToModTags() {
    }

    private void addToConventionalTags() {
    }

}