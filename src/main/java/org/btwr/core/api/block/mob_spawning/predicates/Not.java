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

public record Not(SpawnPredicate predicate) implements SpawnPredicate {
    public static final MapCodec<Not> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            SpawnPredicateTypes.CODEC
                    .fieldOf("predicate")
                    .forGetter(Not::predicate)
    ).apply(i, Not::new));

    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return !predicate.test(state, world, pos, type);
    }

    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.NOT;
    }
}