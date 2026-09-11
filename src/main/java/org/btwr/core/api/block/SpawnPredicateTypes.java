package org.btwr.core.api.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.btwr.core.BTWRMod;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicate;
import org.btwr.core.api.block.mob_spawning.impl.SpawnPredicateType;
import org.btwr.core.api.block.mob_spawning.predicates.*;
import org.btwr.core.block.SpawnRuleRegistries;

public final class SpawnPredicateTypes {
    private SpawnPredicateTypes() {}

    public static final RegistryKey<Registry<SpawnPredicateType<?>>> REGISTRY_KEY =
            RegistryKey.ofRegistry(Identifier.of(BTWRMod.MOD_ID, "spawn_predicate_type"));

    public static final Registry<SpawnPredicateType<?>> REGISTRY =
            FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();

    public static final Codec<SpawnPredicate> CODEC =
            REGISTRY.getCodec().dispatch(SpawnPredicate::getType, SpawnPredicateType::codec);

    public static <P extends SpawnPredicate> SpawnPredicateType<P> register(String id, MapCodec<P> codec) {
        return Registry.register(REGISTRY, Identifier.of(BTWRMod.MOD_ID, id), new SpawnPredicateType<>(codec));
    }

    /** Built-ins registered eagerly so class-load order triggers this. **/
    public static final SpawnPredicateType<AllOf> ALL_OF = register("all_of", AllOf.CODEC);
    public static final SpawnPredicateType<AnyOf> ANY_OF = register("any_of", AnyOf.CODEC);
    public static final SpawnPredicateType<Not> NOT = register("not", Not.CODEC);
    public static final SpawnPredicateType<MatchesTag> MATCHES_TAG = register("matches_tag", MatchesTag.CODEC);
    public static final SpawnPredicateType<MatchesBlocks> MATCHES_BLOCKS = register("matches_blocks", MatchesBlocks.CODEC);
    public static final SpawnPredicateType<BlockStateProperty> BLOCK_STATE_PROPERTY = register("block_state_property", BlockStateProperty.CODEC);
    public static final SpawnPredicateType<LuminanceBelow> LUMINANCE_BELOW = register("luminance_below", LuminanceBelow.CODEC);
    public static final SpawnPredicateType<RequiresNoFluid> REQUIRES_NO_FLUID = register("requires_no_fluid", RequiresNoFluid.CODEC);
    public static final SpawnPredicateType<MatchesEntityType> MATCHES_ENTITY_TYPE = register("matches_entity_type", MatchesEntityType.CODEC);
    public static final SpawnPredicateType<MatchesEntityTypeTag> MATCHES_ENTITY_TYPE_TAG = register("matches_entity_type_tag", MatchesEntityTypeTag.CODEC);
    public static final SpawnPredicateType<RequiresCollision> REQUIRES_COLLISION = register("requires_collision", RequiresCollision.CODEC);

    public static void initialize() {
        DynamicRegistries.register(SpawnRuleRegistries.MOB_SPAWN_RULE, SpawnPredicateTypes.CODEC);
    }
}