package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

public record MatchesEntityTypeTag(TagKey<EntityType<?>> tag) implements SpawnPredicate {
    public static final MapCodec<MatchesEntityTypeTag> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Identifier.CODEC
                    .xmap(id -> TagKey.of(RegistryKeys.ENTITY_TYPE, id), TagKey::id)
                    .fieldOf("tag")
                    .forGetter(MatchesEntityTypeTag::tag)
    ).apply(i, MatchesEntityTypeTag::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type.isIn(tag);
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.MATCHES_ENTITY_TYPE_TAG;
    }
}
