package org.btwr.core.difficulty;

import com.mojang.serialization.Codec;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.btwr.core.BTWRMod;

import java.util.ArrayList;
import java.util.List;

/**
public class ModDifficulties {
    public static BTWRDifficulty CLASSIC;
    public static BTWRDifficulty RELAXED;
    public static BTWRDifficulty STANDARD;
    public static BTWRDifficulty HOSTILE;
    public static BTWRDifficulty HOSTILE_LOCKED;

    public static List<BTWRDifficulty> getHidden() {
        ArrayList<BTWRDifficulty> list = new ArrayList<>();
        list.add(HOSTILE_LOCKED);
        return list;
    }

    public static void initialize() {
        BTWRMod.LOGGER.info("Registering mod difficulties for: {}", BTWRMod.MOD_ID);

        STANDARD = DifficultyRegistry.builder(withId("standard")).build();

        RELAXED = DifficultyRegistry.builder(withId("relaxed"))
                .setParam(SHOULD_GRASS_LOOSEN_WHEN_DIGGING, false)

                .setParam(SHOULD_NETHERCOAL_TORCHES_START_FIRES, false)
                .setParam(NO_TOOL_BLOCK_HARDNESS_MULTIPLIER, 0.75F)

                .setParam(CAN_ZOMBIE_VILLAGERS_BREAK_BLOCKS, false)
                .setParam(SHOULD_BURNING_ANIMALS_DROP_COOKED_MEAT, true)
                .setParam(ANIMAL_KICK_STRENGTH_MULTIPLIER, 0.5F)
                .setParam(SHOULD_INCORRECT_MILKING_STRATLE_COWS, false)
                .setParam(SHOULD_ANIMALS_STARVE_TO_DEATH, false)
                .setParam(SHOULD_PLACING_BREAKING_BLOCK_STRATLE_ANIMALS, false)

                .setParam(ARE_JUNGLE_SPIDERS_NEUTRAL, true)
                .setParam(JUNGLE_SPIDER_FOOD_POISONING_DURATION_MULTIPLIER, 0.3333F)
                .setParam(SHOULD_SQUIDS_ATTACK_DRY_PLAYERS, false)
                .setParam(SHOULD_GHAST_FIREBALL_ANGER_PIGMAN, false)

                .setParam(HUNGER_INTENSIVE_ACTION_COST_MULTIPLIER, 0.5f)
                .setParam(DEATH_COUNT_FOR_ITEM_DESPAWN, -1)
                .setParam(HEALTH_REGEN_DELAY_MULTIPLIER, 0.6F)
                .setParam(STATUS_EFFECT_OFFSET, 0)
                .setParam(STATUS_EFFECT_GAP, 2)
                .setParam(CAN_PLACE_BLOCKS_IN_AIR, true)

                .setParam(SHOULD_WEEDS_KILL_PLANTS, false)
                .setParam(SHOULD_LIGHTNING_START_FIRES, false)
                .setParam(SHOULD_HARDCORE_SPAWN_RADIUS_INCREASE_WITH_PROGRESS, false)
                .build();

        HOSTILE = DifficultyRegistry.builder(withId("hostile"))
                .setParam(CAN_ZOMBIES_BREAK_BLOCKS, true)
                .setParam(ZOMBIE_FOLLOW_DISTANCE_MULTIPLIER, 2.5F)
                .setParam(CAN_CREEPERS_BREACH_WALLS, true)
                .setParam(CREEPER_FOLLOW_DISTANCE_MULTIPLIER, 2.5f)
                .setParam(CAN_ENDERMAN_MOVE_PLAYERS, true)
                .setParam(SHOULD_SKELETONS_SEEK_SPIDER_MOUNTS, true)
                .setParam(SHOULD_WITHER_SKELETONS_SPAWN_UNDERGROUND, true)

                .setParam(SHOULD_NETHER_HAVE_GLOOM, true)
                .setParam(ABANDONED_STRUCTURES_RANGE_MULTIPLIER, 0.6666F)
                .setParam(SHOULD_STRUCTURES_BE_ABANDONED, true)
                .setParam(SHOULD_CAMPFIRES_BE_TRAMPLED, true)
                .setParam(VERTICAL_TARGET_DISTANCE, 15.0D)
                .setParam(SHOULD_BURNING_ENTITIES_SET_FIRES, true)
                .setParam(SKELETON_TARGET_DISTANCE_MULTIPLIER, 3.5F)
                .setParam(ARE_SKELETONS_PYROMANIACS, true)
                .build();

        CLASSIC = DifficultyRegistry.builder(withId("classic"))
                .inherit(RELAXED)

                .setParam(CAN_CRAFT_TORCHES_FROM_COAL, true)
                .setParam(CAN_MAKE_EASY_STONE_TOOLS, true)
                .setParam(SHOULD_REDNECK_FISHING_BURN_FISH, false)

                .setParam(HUNGER_INTENSIVE_ACTION_COST_MULTIPLIER, 0.2F)
                .setParam(NO_TOOL_BLOCK_HARDNESS_MULTIPLIER, 0.5F)
                .setParam(SHOULD_ORES_DROP_PILES_WHEN_CHISELED, false)
                .setParam(SHOULD_OVENS_DROP_THEMSELVES, true)
                .setParam(DOES_STONE_PICK_BREAK_STONE, true)
                .setParam(DOES_STONE_SHOVEL_DROP_PILES, false)

                .setParam(STONE_TOOL_SPEED_MULTIPLIER, 2F)
                .setParam(PROGRESSIVE_CRAFTING_ADDITIONAL_PROGRESS_PER_TICK, 1)

                .setParam(SHOULD_LARGE_ANIMALS_KICK, false)

                .setParam(HEALTH_REGEN_DELAY_MULTIPLIER, 0.4F)
                .setParam(STATUS_EFFECT_OFFSET, 2)
                .setParam(STATUS_EFFECT_GAP, 10)

                .setParam(SHOULD_WEEDS_GROW, false)
                .setParam(SHOULD_FARMLAND_REQUIRE_RE_RETILLING, false)
                .setParam(SHOULD_TALL_GRASS_DROP_WHEAT_SEEDS, true)
                .setParam(SHOULD_HEMP_REQUIRE_SHEARS, false)

                .setParam(SHOULD_PLAYERS_HARDCORE_SPAWN, false)
                .setParam(SHOULD_CRUDE_CLAY_BRICKS_BE_TRAMPLED, false)

                .setParam(CAN_DIFFICULTY_BE_CHANGED, false)
                .build();

        HOSTILE_LOCKED = DifficultyRegistry.builder(withId("hostile_locked"))
                .inherit(HOSTILE)
                .setParam(IS_RESTRICTED, true)
                .build();

        DifficultyRegistry.register(STANDARD);
        DifficultyRegistry.register(RELAXED);
        DifficultyRegistry.register(HOSTILE);
        DifficultyRegistry.register(CLASSIC);
        DifficultyRegistry.register(HOSTILE_LOCKED);
    }

    //------ Crafting Parameters ------//

    public static final DifficultyParam<Boolean> CAN_CRAFT_TORCHES_FROM_COAL = registerParam(
            "can_craft_torches_from_coal", Codec.BOOL, false
    );
    public static final DifficultyParam<Boolean> CAN_MAKE_EASY_STONE_TOOLS = registerParam(
            "can_make_easy_stone_tools", Codec.BOOL, false
    );

    //------ Block Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_NETHERCOAL_TORCHES_START_FIRES = registerParam(
            "should_nethercoal_torches_start_fires", Codec.BOOL, true
    );
    public static final DifficultyParam<Float> NO_TOOL_BLOCK_HARDNESS_MULTIPLIER = registerParamWithModCondition(
            "no_tool_block_hardness_multiplier", Codec.FLOAT, 1F, "tough_environment"
    );
    public static final DifficultyParam<Boolean> SHOULD_ORES_DROP_PILES_WHEN_CHISELED = registerParamWithModCondition(
            "should_ores_drop_piles_when_chiseled", Codec.BOOL, true, "tough_environment"
    );
    public static final DifficultyParam<Boolean> SHOULD_GRASS_LOOSEN_WHEN_DIGGING = registerParamWithModCondition(
            "should_grass_loosen_when_digging", Codec.BOOL, true, "tough_environment"
    );
    public static final DifficultyParam<Boolean> SHOULD_OVENS_DROP_THEMSELVES = registerParamWithModCondition(
            "should_ovens_drop_themselves", Codec.BOOL, false, "self_sustainable"
    );
    public static final DifficultyParam<Boolean> DOES_STONE_PICK_BREAK_STONE = registerParamWithModCondition(
            "does_stone_pick_break_stone", Codec.BOOL, false, "tough_environment"
    );
    public static final DifficultyParam<Boolean> DOES_STONE_SHOVEL_DROP_PILES = registerParamWithModCondition(
            "does_stone_shovel_drop_piles", Codec.BOOL, true, "tough_environment"
    );

    //------ Item Parameters ------//

    public static final DifficultyParam<Float> STONE_TOOL_SPEED_MULTIPLIER = registerParamWithModCondition(
            "stone_tool_speed_multiplier", Codec.FLOAT, 1F, "tough_environment"
    );
    public static final DifficultyParam<Integer> PROGRESSIVE_CRAFTING_ADDITIONAL_PROGRESS_PER_TICK = registerParamWithModCondition(
            "progressive_crafting_additional_progress_per_tick", Codec.INT, 0, "self_sustainable"
    );
    public static final DifficultyParam<Boolean> SHOULD_REDNECK_FISHING_BURN_FISH = registerParamWithModCondition(
            "should_redneck_fishing_burn_fish", Codec.BOOL, true, "bwt"
    );

    //------ Animal Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_BURNING_ANIMALS_DROP_COOKED_MEAT = registerParamWithModCondition(
            "should_burning_animals_drop_cooked_meat", Codec.BOOL, false, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_LARGE_ANIMALS_KICK = registerParamWithModCondition(
            "should_large_animals_kick", Codec.BOOL, true, "animageddon"
    );
    public static final DifficultyParam<Float> ANIMAL_KICK_STRENGTH_MULTIPLIER = registerParamWithModCondition(
            "animal_kick_strength_multiplier", Codec.FLOAT, 1F, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_INCORRECT_MILKING_STRATLE_COWS = registerParamWithModCondition(
            "should_incorrect_milking_stratle_cows", Codec.BOOL, true, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_ANIMALS_STARVE_TO_DEATH = registerParamWithModCondition(
            "should_animals_starve_to_death", Codec.BOOL, true, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_PLACING_BREAKING_BLOCK_STRATLE_ANIMALS = registerParamWithModCondition(
            "should_placing_breaking_block_stratle_animals", Codec.BOOL, true, "animageddon"
    );

    //------ Mob Parameters ------//

    public static final DifficultyParam<Boolean> ARE_JUNGLE_SPIDERS_NEUTRAL = registerParamWithModCondition(
            "are_jungle_spiders_neutral", Codec.BOOL, false, "animageddon"
    );
    public static final DifficultyParam<Float> JUNGLE_SPIDER_FOOD_POISONING_DURATION_MULTIPLIER = registerParamWithModCondition(
            "are_jungle_spiders_neutral", Codec.FLOAT, 1F, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_SQUIDS_ATTACK_DRY_PLAYERS = registerParamWithModCondition(
            "should_squids_attack_dry_players", Codec.BOOL, true, "animageddon"
    );
    public static final DifficultyParam<Boolean> SHOULD_GHAST_FIREBALL_ANGER_PIGMAN = registerParam(
            "should_ghast_fireball_anger_pigmen", Codec.BOOL, true
    );
    public static final DifficultyParam<Boolean> CAN_ZOMBIES_BREAK_BLOCKS = registerParam(
            "can_zombies_break_blocks", Codec.BOOL, false
    );
    public static final DifficultyParam<Float> ZOMBIE_FOLLOW_DISTANCE_MULTIPLIER = registerParam(
            "zombie_follow_distance_multiplier", Codec.FLOAT, 1F
    );
    public static final DifficultyParam<Boolean> CAN_ZOMBIE_VILLAGERS_BREAK_BLOCKS = registerParam(
            "can_zombies_villagers_break_blocks", Codec.BOOL, true
    );
    public static final DifficultyParam<Boolean> CAN_CREEPERS_BREACH_WALLS = registerParam(
            "can_creepers_breach_walls", Codec.BOOL, false
    );
    public static final DifficultyParam<Float> CREEPER_FOLLOW_DISTANCE_MULTIPLIER = registerParam(
            "creeper_follow_distance_multiplier", Codec.FLOAT, 1F
    );
    public static final DifficultyParam<Boolean> CAN_ENDERMAN_MOVE_PLAYERS = registerParam(
            "can_enderman_move_players", Codec.BOOL, false
    );
    public static final DifficultyParam<Boolean> SHOULD_SKELETONS_SEEK_SPIDER_MOUNTS = registerParam(
            "should_skeletons_seek_spider_mounts", Codec.BOOL, false
    );
    public static final DifficultyParam<Boolean> SHOULD_WITHER_SKELETONS_SPAWN_UNDERGROUND = registerParam(
            "should_wither_skeletons_spawn_underground", Codec.BOOL, false
    );
    public static final DifficultyParam<Float> SKELETON_TARGET_DISTANCE_MULTIPLIER = registerParam(
            "skeleton_target_distance_multiplier", Codec.FLOAT, 1F
    );
    public static final DifficultyParam<Double> VERTICAL_TARGET_DISTANCE = registerParam(
            "vertical_target_distance", Codec.DOUBLE, 4D
    );
    public static final DifficultyParam<Boolean> ARE_SKELETONS_PYROMANIACS = registerParam(
            "are_skeletons_pyromaniacs", Codec.BOOL, false
    );

    //------ Entity Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_BURNING_ENTITIES_SET_FIRES = registerParam(
            "should_burning_entities_set_fires", Codec.BOOL, false
    );

    //------ Player Parameters ------//

    public static final DifficultyParam<Float> HUNGER_INTENSIVE_ACTION_COST_MULTIPLIER = registerParamWithModCondition(
            "hunger_intensive_action_cost_multiplier", Codec.FLOAT, 1F, "self_sustainable"
    );
    public static final DifficultyParam<Integer> DEATH_COUNT_FOR_ITEM_DESPAWN = registerParam(
            "death_count_for_item_despawn", Codec.INT, 1
    );
    public static final DifficultyParam<Float> HEALTH_REGEN_DELAY_MULTIPLIER = registerParamWithModCondition(
            "health_regen_delay_multiplier", Codec.FLOAT, 1F, "self_sustainable"
    );
    public static final DifficultyParam<Boolean> CAN_PLACE_BLOCKS_IN_AIR = registerParamWithModCondition(
            "can_place_blocks_in_air", Codec.BOOL, false, "no_nerdpoling"
    );
    public static final DifficultyParam<Integer> STATUS_EFFECT_OFFSET = registerParam(
            "status_effect_offset", Codec.INT, 0
    );
    public static final DifficultyParam<Integer> STATUS_EFFECT_GAP = registerParam(
            "status_effect_gap", Codec.INT, 1
    );

    //------ Crop Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_WEEDS_GROW = registerParamWithModCondition(
            "should_weeds_grow", Codec.BOOL, true, "vegehenna"
    );
    public static final DifficultyParam<Boolean> SHOULD_WEEDS_KILL_PLANTS = registerParamWithModCondition(
            "should_weeds_kill_plants", Codec.BOOL, true, "vegehenna"
    );
    public static final DifficultyParam<Boolean> SHOULD_FARMLAND_REQUIRE_RE_RETILLING = registerParamWithModCondition(
            "should_farmland_require_re_retilling", Codec.BOOL, true, "vegehenna"
    );
    public static final DifficultyParam<Boolean> SHOULD_TALL_GRASS_DROP_WHEAT_SEEDS = registerParamWithModCondition(
            "should_tall_grass_drop_wheat_seeds", Codec.BOOL, false, "vegehenna"
    );
    public static final DifficultyParam<Boolean> SHOULD_HEMP_REQUIRE_SHEARS = registerParamWithModCondition(
            "should_hemp_require_shears", Codec.BOOL, true, "bwt"
    );

    //------ World Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_LIGHTNING_START_FIRES = registerParam(
            "should_lightning_start_fires", Codec.BOOL, true
    );
    public static final DifficultyParam<Boolean> SHOULD_NETHER_HAVE_GLOOM = registerParamWithModCondition(
            "should_nether_have_gloom", Codec.BOOL, false, "in_the_gloom"
    );
    public static final DifficultyParam<Boolean> SHOULD_STRUCTURES_BE_ABANDONED = registerParamWithModCondition(
            "should_structures_be_abandoned", Codec.BOOL, false, "hardcore_abandonment"
    );
    public static final DifficultyParam<Float> ABANDONED_STRUCTURES_RANGE_MULTIPLIER = registerParamWithModCondition(
            "abandoned_structures_range_multiplier", Codec.FLOAT, 1F, "hardcore_abandonment"
    );
    public static final DifficultyParam<Integer> VILLAGE_SECONDARY_CROP_CHANCE = registerParamWithModCondition(
            "village_secondary_crop_chance", Codec.INT, 3, "hardcore_abandonment"
    );
    public static final DifficultyParam<Boolean> SHOULD_CRUDE_CLAY_BRICKS_BE_TRAMPLED = registerParamWithModCondition(
            "should_crude_clay_bricks_be_trampled", Codec.BOOL, true, "self_sustainable"
    );
    public static final DifficultyParam<Boolean> SHOULD_CAMPFIRES_BE_TRAMPLED = registerParamWithModCondition(
            "should_campfires_be_trampled", Codec.BOOL, false, "self_sustainable"
    );

    //------ Hardcore Spawn Parameters ------//

    public static final DifficultyParam<Boolean> SHOULD_PLAYERS_HARDCORE_SPAWN = registerParamWithModCondition(
            "should_players_hardcore_spawn", Codec.BOOL, true, "hardcore_spawn"
    );
    public static final DifficultyParam<Boolean> SHOULD_HARDCORE_SPAWN_RADIUS_INCREASE_WITH_PROGRESS = registerParamWithModCondition(
            "should_hardcore_spawn_radius_increase_with_progress", Codec.BOOL, true, "hardcore_spawn"
    );
    public static final DifficultyParam<Boolean> CAN_PLAYERS_SPAWN_TOGETHER = registerParamWithModCondition(
            "can_players_spawn_together", Codec.BOOL, true, "hardcore_spawn"
    );

    //------ Misc Difficulty Parameters ------//

    public static final DifficultyParam<Boolean> CAN_DIFFICULTY_BE_CHANGED = registerParam(
            "can_difficulty_be_changed", Codec.BOOL, true
    );
    public static final DifficultyParam<Boolean> IS_RESTRICTED = registerParam(
            "is_restricted", Codec.BOOL, false
    );

    private static <T> DifficultyParam<T> registerParamWithModCondition(String name, Codec<T> codec, T defaultValue, String modName) {
        DifficultyParam<T> value = registerParam(name, codec, defaultValue);

        if (!FabricLoader.getInstance().isModLoaded(modName)) {
            BTWRMod.LOGGER.error("[{}] Can't register this difficulty parameter because it's required mod {} is not present.", BTWRMod.MOD_NAME, name);
            return null;
        }

        BTWRDifficulty.Builder.getParameterIds().put(value, value.id());

        return value;
    }

    private static <T> DifficultyParam<T> registerParam(String name, Codec<T> codec, T defaultValue) {
        DifficultyParam<T> value = new DifficultyParam<>(
                Identifier.of(BTWRMod.MOD_ID, name),
                codec,
                defaultValue
        );

        BTWRDifficulty.Builder.getParameterIds().put(value, value.id());

        return value;
    }

    private static Identifier withId(String name) {
        return Identifier.of(BTWRMod.MOD_ID, name);
    }
}
 **/
