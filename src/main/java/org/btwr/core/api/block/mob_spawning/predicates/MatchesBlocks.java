package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

import java.util.List;

public record MatchesBlocks(List<Identifier> blocks) implements SpawnPredicate {
    public static final MapCodec<MatchesBlocks> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Identifier.CODEC
                    .listOf()
                    .fieldOf("blocks")
                    .forGetter(MatchesBlocks::blocks)
    ).apply(i, MatchesBlocks::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        Block block = state.getBlock();
        Identifier blockId = Registries.BLOCK.getId(block);
        for (Identifier id : blocks) {
            if (id.equals(blockId)) return true;
        }

        return false;
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.MATCHES_BLOCKS;
    }
}