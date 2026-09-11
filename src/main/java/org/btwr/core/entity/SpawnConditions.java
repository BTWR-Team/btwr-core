package org.btwr.core.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.block.SpawnRuleRegistries;

public final class SpawnConditions {
    private SpawnConditions() {}

    public static boolean allowsSpawning(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        if (!(world instanceof World w)) return true;

        Registry<SpawnPredicate> rules = w.getRegistryManager().get(SpawnRuleRegistries.MOB_SPAWN_RULE);

        for (var entry : rules.iterateEntries(SpawnRuleRegistries.ACTIVE)) {
            if (!entry.value().test(state, world, pos, type)) return false; // any failed check blocks spawning
        }
        return true;
    }
}