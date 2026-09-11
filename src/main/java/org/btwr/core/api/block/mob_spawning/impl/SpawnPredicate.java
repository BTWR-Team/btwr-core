package org.btwr.core.api.block.mob_spawning.impl;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public interface SpawnPredicate {
    boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type);

    SpawnPredicateType<?> getType();

    static MapCodec<? extends SpawnPredicate> bootstrap() {
        return null;
    }
}
