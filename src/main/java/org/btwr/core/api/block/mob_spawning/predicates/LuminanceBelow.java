package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

public record LuminanceBelow(int value) implements SpawnPredicate {
    public static final MapCodec<LuminanceBelow> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.INT
                    .fieldOf("value")
                    .forGetter(LuminanceBelow::value)
    ).apply(i, LuminanceBelow::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return state.getLuminance() < value;
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.LUMINANCE_BELOW;
    }
}