package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
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

public record MatchesTag(TagKey<Block> tag) implements SpawnPredicate {
    public static final MapCodec<MatchesTag> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Identifier.CODEC
                    .xmap(id -> TagKey.of(RegistryKeys.BLOCK, id), TagKey::id)
                    .fieldOf("tag")
                    .forGetter(MatchesTag::tag)
    ).apply(i, MatchesTag::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return state.isIn(tag);
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.MATCHES_TAG;
    }
}
