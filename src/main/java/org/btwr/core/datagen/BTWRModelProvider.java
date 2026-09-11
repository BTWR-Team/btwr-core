package org.btwr.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.block.blocks.BlightBlock;
import org.btwr.core.block.blocks.BlightRootsBlock;
import org.btwr.core.item.ModItems;
import org.btwr.shared_library.util.utils.IdUtils;

import static net.minecraft.data.client.BlockStateModelGenerator.createSlabBlockState;

public class BTWRModelProvider extends FabricModelProvider {

    public BTWRModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        this.registerBlight(generator);
        this.registerBlightRoots(generator);
        generator.registerSimpleCubeAll(ModBlocks.FLINT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.DIAMOND_INGOT_BLOCK);
        generator.registerSimpleCubeAll(ModBlocks.SPIDER_EYE_BLOCK);
        this.registerSimpleSlab(generator, ModBlocks.SPIDER_EYE_BLOCK, ModBlocks.SPIDER_EYE_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ModItems.GROUP_BTWR, Models.GENERATED);
        generator.register(ModItems.DIAMOND_INGOT, Models.GENERATED);
        generator.register(ModItems.DIAMOND_PLATE, Models.GENERATED);
        generator.register(ModItems.LEATHER_CUT, Models.GENERATED);
        generator.register(ModItems.LEATHER_SCOURED, Models.GENERATED);
        generator.register(ModItems.LEATHER_SCOURED_CUT, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED_CUT, Models.GENERATED);
        generator.register(ModItems.CLUB_WOOD, Models.HANDHELD);
        generator.register(ModItems.CLUB_BONE, Models.HANDHELD);
        generator.register(ModItems.DIAMOND_SHEARS, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED_HELMET, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED_CHESTPLATE, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED_LEGGINGS, Models.GENERATED);
        generator.register(ModItems.LEATHER_TANNED_BOOTS, Models.GENERATED);
        generator.register(ModItems.BEAST_LIVER_RAW, Models.GENERATED);
        generator.register(ModItems.BEAST_LIVER_COOKED, Models.GENERATED);
        generator.register(ModItems.OCULAR_OF_ENDER, Models.GENERATED);
        generator.register(ModItems.ENDER_SPECTACLES, Models.GENERATED);
    }

    private void registerBlight(BlockStateModelGenerator gen) {
        BlockStateVariantMap.SingleProperty<Integer> variantMap = BlockStateVariantMap.create(BlightBlock.LEVEL);
        for (int i = 0; i <= 3; i++) {
            TextureMap textureMap = new TextureMap()
                    .put(TextureKey.BOTTOM, TextureMap.getSubId(ModBlocks.BLIGHT, "_bottom_" + i))
                    .inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
                    .put(TextureKey.TOP, TextureMap.getSubId(ModBlocks.BLIGHT, "_top_" + i))
                    .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.BLIGHT, "_side_" + i));
            Identifier modelId = Models.CUBE_BOTTOM_TOP.upload(ModBlocks.BLIGHT, "_" + i, textureMap, gen.modelCollector);
            variantMap.register(i, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
        }
        gen.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BLIGHT).coordinate(variantMap));
        gen.registerParentedItemModel(ModBlocks.BLIGHT, IdUtils.ofBTWR("block/blight_0"));
    }

    private void registerBlightRoots(BlockStateModelGenerator gen) {
        BlockStateVariantMap.SingleProperty<Integer> variantMap = BlockStateVariantMap.create(BlightRootsBlock.LEVEL);

        for (int i = 0; i <= 1; i++) {
            Identifier rootsTex = TextureMap.getSubId(ModBlocks.BLIGHT_ROOTS, "_" + i);
            TextureMap textureMap = new TextureMap()
                    .put(TextureKey.BOTTOM, TextureMap.getSubId(ModBlocks.BLIGHT, "_bottom_0"))
                    .inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
                    .put(TextureKey.TOP, rootsTex)
                    .put(TextureKey.SIDE, rootsTex);

            Identifier modelId = Models.CUBE_BOTTOM_TOP.upload(ModBlocks.BLIGHT_ROOTS, "_" + i, textureMap, gen.modelCollector);
            variantMap.register(i, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
        }

        gen.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.BLIGHT_ROOTS).coordinate(variantMap)
        );
        gen.registerParentedItemModel(ModBlocks.BLIGHT_ROOTS, IdUtils.ofBTWR("block/blight_roots_0"));
    }

    private void registerSimpleSlab(BlockStateModelGenerator gen, Block mainBlock, Block slabBlock) {
        Identifier identifier = ModelIds.getBlockModelId(mainBlock);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(mainBlock);
        Identifier identifier2 = Models.SLAB.upload(slabBlock, texturedModel.getTextures(), gen.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(slabBlock, texturedModel.getTextures(), gen.modelCollector);
        gen.blockStateCollector.accept(createSlabBlockState(slabBlock, identifier2, identifier3, identifier));
    }

}