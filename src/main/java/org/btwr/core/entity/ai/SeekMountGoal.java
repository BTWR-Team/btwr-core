package org.btwr.core.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class SeekMountGoal extends Goal {
    private final PathAwareEntity mob;
    private final Class<? extends Entity> mountClass;
    private final double speed;
    private final float maxTargetDistance;
    private final Predicate<Entity> mountPredicate;

    @Nullable
    private Entity mount;
    private double moveX;
    private double moveY;
    private double moveZ;

    public SeekMountGoal(PathAwareEntity mob, Class<? extends Entity> mountClass, double speed, float maxTargetDistance) {
        this.mob = mob;
        this.mountClass = mountClass;
        this.speed = speed;
        this.maxTargetDistance = maxTargetDistance;
        this.mountPredicate = candidate -> candidate.isAlive() && !candidate.hasPassengers();
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (this.mount == null) {
            Box box = this.mob.getBoundingBox().expand(this.maxTargetDistance, 4.0, this.maxTargetDistance);
            List<? extends Entity> candidates = this.mob.getWorld().getEntitiesByClass(this.mountClass, box, this.mountPredicate);
            this.mount = candidates.isEmpty() ? null : candidates.get(0);
        }

        if (this.mount == null) {
            return false;
        } else if (this.mount.squaredDistanceTo(this.mob) > (double) (this.maxTargetDistance * this.maxTargetDistance)) {
            return false;
        } else {
            Vec3d target = NoPenaltyTargeting.findTo(this.mob, 16, 7, this.mount.getPos(), Math.PI);
            if (target == null) {
                return false;
            } else {
                this.moveX = target.x;
                this.moveY = target.y;
                this.moveZ = target.z;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        if (this.mob.getNavigation().isIdle()) {
            return false;
        } else if (this.mount == null || !this.mount.isAlive()) {
            return false;
        } else if (this.mount.squaredDistanceTo(this.mob) > (double) (this.maxTargetDistance * this.maxTargetDistance)) {
            return false;
        } else if (this.mob.hasPassengers()) {
            return false;
        } else if (this.mob.squaredDistanceTo(this.mount) < 5.25 && !this.mob.hasPassengers()) {
            this.mob.startRiding(this.mount);
            return false;
        } else {
            return canStart();
        }
    }

    @Override
    public void stop() {
        this.mount = null;
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.moveX, this.moveY, this.moveZ, this.speed);
    }
}