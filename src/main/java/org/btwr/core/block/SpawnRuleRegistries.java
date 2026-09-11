package org.btwr.core.block;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.btwr.core.BTWRMod;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;

public final class SpawnRuleRegistries {
    private SpawnRuleRegistries() {}

    public static final RegistryKey<Registry<SpawnPredicate>> MOB_SPAWN_RULE =
            RegistryKey.ofRegistry(Identifier.of(BTWRMod.MOD_ID, "mob_spawn_rule"));

    public static final TagKey<SpawnPredicate> ACTIVE =
            TagKey.of(MOB_SPAWN_RULE, Identifier.of(BTWRMod.MOD_ID, "active"));

    public static TagKey<SpawnPredicate> tag(String path) {
        return TagKey.of(MOB_SPAWN_RULE, Identifier.of(BTWRMod.MOD_ID, path));
    }
}