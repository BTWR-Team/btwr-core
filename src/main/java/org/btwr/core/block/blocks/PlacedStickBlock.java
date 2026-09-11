package org.btwr.core.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class PlacedStickBlock extends Block {

    protected static final double SHAFT_WIDTH = 0.125D;
    protected static final double SHAFT_HALF_WIDTH = SHAFT_WIDTH / 2D;
    protected static final double SHAFT_HEIGHT = 0.75D;

    protected static final double SELECTION_WIDTH = SHAFT_WIDTH + (2D / 16D);
    protected static final double SELECTION_HALF_WIDTH = SELECTION_WIDTH / 2D;
    protected static final double SELECTION_HEIGHT = SHAFT_HEIGHT + (1D / 16D);

    private static final Map<Direction, VoxelShape> OUTLINE_NORMAL = new EnumMap<>(Direction.class);
    private static final Map<Direction, VoxelShape> OUTLINE_SUPPORTING = new EnumMap<>(Direction.class);

    static {
        for (Direction d : Direction.values()) {
            OUTLINE_NORMAL.put(d, VoxelShapes.cuboid(buildBox(d, SELECTION_HALF_WIDTH, SELECTION_HEIGHT, false)));
            OUTLINE_SUPPORTING.put(d, VoxelShapes.cuboid(buildBox(d, SELECTION_HALF_WIDTH, SELECTION_HEIGHT, true)));
        }
    }

    private static Box buildBox(Direction facing, double halfWidth, double height, boolean supporting) {
        double lo = 0.5 - halfWidth;
        double hi = 0.5 + halfWidth;
        double axisLow, axisHigh;
        if (supporting) {
            axisLow = 0.0;
            axisHigh = 1.0;
        } else if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
            axisLow = 1.0 - height;
            axisHigh = 1.0;
        } else {
            axisLow = 0.0;
            axisHigh = height;
        }

        return switch (facing.getAxis()) {
            case X -> new Box(axisLow, lo, lo, axisHigh, hi, hi);
            case Y -> new Box(lo, axisLow, lo, hi, axisHigh, hi);
            case Z -> new Box(lo, lo, axisLow, hi, hi, axisHigh);
        };
    }

    public PlacedStickBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(Properties.FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING);
    }

    // ------------- Shape ------------- //

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(Properties.FACING);
        boolean supporting = isSupportingOtherBlock(world, pos, state);
        return (supporting ? OUTLINE_SUPPORTING : OUTLINE_NORMAL).get(facing);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction facing = ctx.canReplaceExisting()
                ? Direction.DOWN
                : ctx.getSide().getOpposite();

        BlockState state = getDefaultState().with(Properties.FACING, facing);
        return canPlaceAt(state, ctx.getWorld(), ctx.getBlockPos()) ? state : null;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(Properties.FACING);
        BlockPos anchorPos = pos.offset(facing);
        BlockState anchorState = world.getBlockState(anchorPos);
        Direction sideFacingUs = facing.getOpposite();

        return Block.sideCoversSmallSquare(world, anchorPos, sideFacingUs) && canStickInBlockType(anchorState);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        Direction facing = state.get(Properties.FACING);
        BlockPos anchorPos = pos.offset(facing);
        BlockState anchorState = world.getBlockState(anchorPos);

        world.playSound(
                null,
                pos,
                Blocks.OAK_LOG.getDefaultState().getSoundGroup().getPlaceSound(),
                SoundCategory.BLOCKS,
                0.5F,
                0.8F
        );

        // if (!world.isClient) {
        //     anchorState.getBlock().onSteppedOn(world, anchorPos, anchorState, placer);
        // }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient && !canPlaceAt(state, world, pos)) {
            Block.dropStack(world, pos, new ItemStack(Items.STICK));
            world.removeBlock(pos, false);
        }
    }

    public boolean canStickInBlockType(BlockState state) {
        return state.isIn(BlockTags.SHOVEL_MINEABLE);
    }

    public boolean isSupportingOtherBlock(BlockView world, BlockPos pos, BlockState state) {
        if (state.get(Properties.FACING) != Direction.DOWN) return false;

        BlockPos abovePos = pos.up();
        BlockState aboveState = world.getBlockState(abovePos);

        return !aboveState.isAir() && aboveState.isSideSolidFullSquare(world, abovePos, Direction.DOWN);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.STICK);
    }

    @Override
    public boolean btwr$hasSmallCenterHardPointToFacing(WorldAccess world, BlockPos pos, Direction facing, boolean ignoreTransparency) {
        BlockState state = world.getBlockState(pos);
        return facing == Direction.UP && state.get(Properties.FACING) == Direction.DOWN;
    }

    @Override
    public boolean btwr$canGroundCoverRestOnBlock(World world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP);
    }

}