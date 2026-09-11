package org.btwr.core.api.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.core.BTWRMod;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for controlling which blocks blight can spread to, and under what conditions.
 *
 * <p>Other mods can register their own blocks via {@link #register(Block, SpreadCondition)}
 * to participate in the blight spread system.
 */
public class BlightSpreadRegistry {

    /**
     * A condition that determines whether blight can spread to a given block.
     *
     * <p>Implementations receive full world context so conditions can check
     * neighbouring blocks, biome, light level, or any other world state.
     */
    @FunctionalInterface
    public interface SpreadCondition {
        /**
         * Returns {@code true} if blight is allowed to spread to the block at {@code pos}.
         *
         * @param world      the world the spread is occurring in
         * @param pos        the position of the block being spread to
         * @param state      the current state of the block at {@code pos}
         * @param blightLevel the level of the adjacent blight block that is spreading (0–3)
         * @return {@code true} if blight may convert this block
         */
        boolean canSpread(World world, BlockPos pos, BlockState state, int blightLevel);

        /** This block can always be blighted, regardless of level **/
        static SpreadCondition always() {
            return (w, p, s, level) -> true;
        }

        /** This block can only be blighted when blight has reached {@code minLevel} or higher. **/
        static SpreadCondition atLevel(int minLevel) {
            return (w, p, s, level) -> level >= minLevel;
        }
    }

    /**
     * Maps each registered block to its spread condition.
     */
    private static final Map<Block, SpreadCondition> CONDITIONS = new HashMap<>();

    /**
     * Registers a {@link SpreadCondition} for the given block.
     **
     * @param block the block to register a condition for
     * @param condition the condition that controls whether blight may spread to this block
     */
    public static void register(Block block, SpreadCondition condition) {
        if (!CONDITIONS.containsKey(block)) {
            CONDITIONS.put(block, condition);
        } else {
            BTWRMod.LOGGER.error("{} already has registered spread conditions for blight", block);
        }
    }

    /** Unregisters a {@link SpreadCondition} for the given block if it exists. **/
    public static void unregister(Block block) {
        if (CONDITIONS.containsKey(block)) {
            CONDITIONS.remove(block);
        } else {
            BTWRMod.LOGGER.error("{} doesn't have a registered blight spread condition to remove.", block);
        }
    }

    /**
     * Registers the built-in spread conditions for vanilla blocks.
     *
     * <p>Called automatically during BTWR initialisation. Vanilla behaviour:
     * <ul>
     *   <li>Grass, dirt, and podzol — spreadable at any blight level</li>
     *   <li>Farmland — requires blight level 1 or higher</li>
     *   <li>Mycelium and moss — require blight level 2 or higher</li>
     * </ul>
     */
    public static void registerDefaults() {
        register(Blocks.GRASS_BLOCK, SpreadCondition.always());
        register(Blocks.DIRT, SpreadCondition.always());
        register(Blocks.PODZOL, SpreadCondition.always());
        register(Blocks.FARMLAND, SpreadCondition.atLevel(1));
        register(Blocks.MYCELIUM, SpreadCondition.atLevel(2));
        register(Blocks.MOSS_BLOCK, SpreadCondition.atLevel(2));
    }

    /**
     * Returns whether blight can spread to the block at the given position.
     *
     * <p>Looks up the condition registered for {@code state}'s block and delegates to it.
     * Returns {@code false} if no condition has been registered for the block.
     *
     * @param world      the world the spread is occurring in
     * @param pos        the position of the candidate block
     * @param state      the state of the candidate block
     * @param blightLevel the level of the spreading blight block (0–3)
     * @return {@code true} if blight may convert this block
     */
    public static boolean canBlightSpreadTo(World world, BlockPos pos, BlockState state, int blightLevel) {
        SpreadCondition condition = CONDITIONS.get(state.getBlock());
        if (condition != null) {
            return condition.canSpread(world, pos, state, blightLevel);
        }
        return false;
    }
}