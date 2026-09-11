package org.btwr.core.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.EnumSet;

public class ZombieBreakBarricadeHostileGoal extends ZombieBreakBarricadeGoal {
    private final ZombieEntity zombie;

    public ZombieBreakBarricadeHostileGoal(ZombieEntity zombie) {
        super(zombie);
        this.zombie = zombie;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        LivingEntity target = zombie.getTarget();

        if (target == null) return false;

        if(!zombie.horizontalCollision) {
            zombie.getMoveControl()
                    .moveTo(
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            0.25
                    );
            return false;
        }

        return findBlock(target);
    }

    private boolean findBlock(LivingEntity target) {
        Vec3d start = zombie.getEyePos();
        Vec3d end = target.getEyePos();

        BlockHitResult hit =
                zombie.getWorld()
                        .raycast(new RaycastContext(
                                start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, zombie
                                )
                        );

        if (hit.getType() != HitResult.Type.BLOCK) return false;

        blockPos = hit.getBlockPos();
        targetBlock = zombie.getWorld().getBlockState(blockPos);

        return canBreak(targetBlock, blockPos);
    }

    @Override
    public void tick() {
        super.tick();

        if(blockPos != null) {

            Vec3d center = blockPos.toCenterPos();

            zombie.getLookControl().lookAt(center);
            zombie.getMoveControl()
                    .moveTo(
                            center.x,
                            center.y,
                            center.z,
                            0.25
                    );
        }
    }
}