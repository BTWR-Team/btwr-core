package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

public record RequiresNoFluid() implements SpawnPredicate {
    public static final MapCodec<RequiresNoFluid> CODEC = MapCodec.unit(RequiresNoFluid::new);

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.REQUIRES_NO_FLUID;
    }
}
