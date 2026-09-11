package org.btwr.core.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

public class ZombieBreakBarricadeGoal extends Goal {
    protected final ZombieEntity zombie;
    protected BlockPos blockPos;
    protected BlockState targetBlock;
    protected int breakingTime;
    protected int lastBreakProgress = -1;

    public ZombieBreakBarricadeGoal(ZombieEntity zombie) {
        this.zombie = zombie;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!zombie.horizontalCollision) {
            return false;
        }

        if (!zombie.getNavigation().isFollowingPath()) {
            return false;
        }

        BlockPos pos = zombie.getBlockPos();
        BlockPos[] checks = {pos.up(), pos, pos.up(2)};

        for (BlockPos check : checks) {
            BlockState state = zombie.getWorld().getBlockState(check);

            if (canBreak(state, check)) {

                blockPos = check;
                targetBlock = state;

                return true;
            }
        }

        return false;
    }

    @Override
    public void start() {

        breakingTime = 0;
        lastBreakProgress = -1;
    }


    @Override
    public boolean shouldContinue() {
        if (blockPos == null) {
            return false;
        }

        if (breakingTime > 240) {
            return false;
        }

        if (!zombie.getWorld()
                .getBlockState(blockPos)
                .equals(targetBlock)
        ) {
            return false;
        }

        return zombie.squaredDistanceTo(
                blockPos.getX(), blockPos.getY(), blockPos.getZ()
        ) < 4;
    }


    @Override
    public void stop() {
        zombie.getWorld()
                .setBlockBreakingInfo(
                        zombie.getId(), blockPos, -1
                );
    }


    @Override
    public void tick() {
        World world = zombie.getWorld();

        breakingTime++;

        int progress = (int)((breakingTime / 240F) * 10F);

        if(progress != lastBreakProgress) {
            world.setBlockBreakingInfo(
                    zombie.getId(), blockPos, progress
            );

            lastBreakProgress = progress;
        }

        if(breakingTime >= 240) {
            world.breakBlock(
                    blockPos, true, zombie
            );

            zombie.takeKnockback(
                    0.33F, zombie.getRotationVector().x, zombie.getRotationVector().z
            );
        }
    }

    protected boolean canBreak(BlockState state, BlockPos pos) {
        return !state.isAir()
                && state.getHardness(
                zombie.getWorld(),
                    pos
                ) >= 0;
    }
}