package org.btwr.core.event.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.core.block.ModBlocks;

public class ModBlockUseEvents {

    public static void initialize() {
        UseBlockCallback.EVENT.register(ModBlockUseEvents::tryPlacingStick);
    }

    private static ActionResult tryPlacingStick(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getStackInHand(hand);

        if (!handStack.isOf(Items.STICK)) return ActionResult.PASS;

        ItemPlacementContext ctx = new ItemPlacementContext(world, player, hand, handStack, hitResult);

        BlockPos placePos = ctx.getBlockPos();

        if (!ctx.canPlace()) return ActionResult.PASS;

        BlockState state = ModBlocks.PLACED_STICK.getPlacementState(ctx);

        if (state == null) return ActionResult.PASS;

        if (!world.isClient) {
            world.setBlockState(placePos, state, Block.NOTIFY_ALL);
            ModBlocks.PLACED_STICK.onPlaced(world, placePos, state, player, handStack);
            handStack.decrementUnlessCreative(1, player);
        }

        return ActionResult.SUCCESS;
    }
}
