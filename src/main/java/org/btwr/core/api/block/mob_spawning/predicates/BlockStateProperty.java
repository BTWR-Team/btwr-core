package org.btwr.core.api.block.mob_spawning.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;

import java.util.Map;

public record BlockStateProperty(Map<String, String> properties) implements SpawnPredicate {
    public static final Codec<Map<String, String>> PROPRETIES_CODEC =
            Codec.unboundedMap(Codec.STRING, Codec.STRING);

    public static final MapCodec<BlockStateProperty> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            PROPRETIES_CODEC
                    .fieldOf("properties")
                    .forGetter(BlockStateProperty::properties)
    ).apply(i, BlockStateProperty::new));

    @Override
    public boolean test(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        for (Map.Entry<String, String> entry : properties().entrySet()) {
            if (!matchesProperty(state, entry.getKey(), entry.getValue())) {
                return false;
            }
        }

        return true;
    }

    private static boolean matchesProperty(BlockState state, String propertyName, String expected) {
        Property<?> property = state.getBlock().getStateManager().getProperty(propertyName);

        // Property doesn't exist on the block, so the rule doesn't apply/fails
        if (property == null) {
            return false;
        }

        String actual = getValuesAsString(state, property);

        if (expected.contains("..")) {
            return matchesRange(actual, expected);
        }

        return actual.equals(expected);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String getValuesAsString(BlockState state, Property<?> property) {
        Comparable value = state.get((Property) property);
        return ((Property) property).name(value);
    }

    private static boolean matchesRange(String actual, String rangeExpr) {
        String[] parts = rangeExpr.split("\\.\\.", 2);
        try {
            int actualInt = Integer.parseInt(actual);
            int min = parts[0].isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(parts[0]);
            int max = parts[1].isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(parts[1]);

            return actualInt >= min && actualInt <= max;
        } catch (NumberFormatException e) {
            // Not an int-backed property - range syntax doesn't apply
            return false;
        }
    }

    @Override
    public SpawnPredicateType<?> getType() {
        return SpawnPredicateTypes.BLOCK_STATE_PROPERTY;
    }
}