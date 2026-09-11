package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

public record RequiresCollision() implements SpawnPredicate {
    public static final MapCodec<RequiresCollision> CODEC = MapCodec.unit(RequiresCollision::new);

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return !state.getCollisionShape(world, pos).isEmpty();
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.REQUIRES_COLLISION;
    }
}