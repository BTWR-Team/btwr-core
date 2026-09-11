package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

import java.util.List;

public record AllOf(List<SpawnPredicate> predicates) implements SpawnPredicate {
    public static final MapCodec<AllOf> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            SpawnPredicateTypes.CODEC.listOf().fieldOf("predicates").forGetter(AllOf::predicates)
    ).apply(i, AllOf::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        for (var entry : predicates) {
            if (!entry.test(state, world, pos, type)) return false;
        }

        return true;
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.ALL_OF;
    }
}