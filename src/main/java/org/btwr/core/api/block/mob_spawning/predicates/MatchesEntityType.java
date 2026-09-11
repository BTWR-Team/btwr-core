package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public record MatchesEntityType(List<Identifier> entities) implements SpawnPredicate {
    public static final MapCodec<MatchesEntityType> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Identifier.CODEC
                    .listOf()
                    .fieldOf("entities")
                    .forGetter(MatchesEntityType::entities)
    ).apply(i, MatchesEntityType::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return entities.contains(Registries.ENTITY_TYPE.getId(type));
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.MATCHES_ENTITY_TYPE;
    }
}
