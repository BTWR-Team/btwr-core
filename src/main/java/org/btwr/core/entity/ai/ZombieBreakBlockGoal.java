package org.btwr.core.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

public class ZombieBreakBlockGoal extends Goal {

    private final ZombieEntity zombie;

    private BlockPos targetBlock;
    private int breakTime;

    public ZombieBreakBlockGoal(ZombieEntity zombie) {
        this.zombie = zombie;

        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!zombie.isAlive()) {
            return false;
        }

        BlockPos pos = findBreakableBlock();

        if (pos == null) {
            return false;
        }

        targetBlock = pos;
        return true;
    }


    @Override
    public void start() {
        breakTime = 0;

        zombie.getNavigation().startMovingTo(
                targetBlock.getX(),
                targetBlock.getY(),
                targetBlock.getZ(),
                1.0
        );
    }


    @Override
    public boolean shouldContinue() {
        return targetBlock != null
                && zombie.squaredDistanceTo(
                    targetBlock.getX(),
                    targetBlock.getY(),
                    targetBlock.getZ()
                ) < 4;
    }


    @Override
    public void tick() {
        if (targetBlock == null) return;

        zombie.getLookControl().lookAt(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ());

        if (++breakTime >= getBreakTime()) {

            World world = zombie.getWorld();

            if (!world.isClient) {
                world.breakBlock(targetBlock, true, zombie);
            }

            targetBlock = null;
        }
    }


    private BlockPos findBreakableBlock() {
        BlockPos origin = zombie.getBlockPos();

        for (BlockPos pos : BlockPos.iterateOutwards(
                origin, 4, 2, 4)
        ) {

            BlockState state = zombie.getWorld().getBlockState(pos);

            if (isBreakable(state)) {
                return pos;
            }
        }

        return null;
    }

    private boolean isBreakable(BlockState state) {
        return !state.isAir() && state.getHardness(zombie.getWorld(), BlockPos.ORIGIN) >= 0;
    }


    private int getBreakTime() {

        // 20 ticks = 1 second
        // replace with hardness calculation later

        return 60;
    }
}