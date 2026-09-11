package org.btwr.core.entity.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.btwr.core.mixin.entity.EndermanEntityInvoker;

public final class EndermanMoveUtil {
    private EndermanMoveUtil() {}

    public static boolean tryMovePlayer(EndermanEntity enderman, LivingEntity target) {
        boolean selfTeleported = ((EndermanEntityInvoker)enderman).btw$teleportRandomly();

        if (selfTeleported && enderman.getY() >= target.getY() - 8.0) {
            for (int i = 0; i < 4; i++) {
                if (teleportEntityNearSelf(enderman, target)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean teleportEntityNearSelf(EndermanEntity enderman, LivingEntity target) {
        Random random = enderman.getWorld().random;
        double r = random.nextDouble() - 0.5;
        double f = random.nextInt(2) - 0.5;
        double x = enderman.getX() + (r - f) * 10.0;
        double y = enderman.getY() + random.nextInt(6) - 3;
        double z = enderman.getZ() + (r + f) * 10.0;
        return teleportEntityTo(enderman, target, x, y, z);
    }

    private static boolean teleportEntityTo(EndermanEntity enderman, LivingEntity target, double x, double y, double z) {
        World world = target.getWorld();
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while (mutable.getY() > world.getBottomY() && !world.getBlockState(mutable).blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = world.getBlockState(mutable);
        boolean blocksMovement = blockState.blocksMovement();
        boolean isWater = blockState.getFluidState().isIn(FluidTags.WATER);

        if (blocksMovement && !isWater) {
            Vec3d oldPos = target.getPos();
            boolean teleported = target.teleport(x, y, z, true);
            if (teleported) {
                world.emitGameEvent(GameEvent.TELEPORT, oldPos, GameEvent.Emitter.of(target));
                if (!enderman.isSilent()) {
                    world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT, enderman.getSoundCategory(), 1.0F, 1.0F);
                    world.playSound(null, enderman.getX(), enderman.getY(), enderman.getZ(),
                        SoundEvents.ENTITY_ENDERMAN_TELEPORT, enderman.getSoundCategory(), 1.0F, 1.0F);
                }
            }
            return teleported;
        }
        return false;
    }
}