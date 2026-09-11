package org.btwr.core.entity.ai;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.LivingEntity;

public class ZombieSecondaryTargetPredicate {

    private final ZombieEntity zombie;

    public ZombieSecondaryTargetPredicate(ZombieEntity zombie) {
        this.zombie = zombie;
    }

    public boolean test(LivingEntity entity) {
        return BTWRZombieTargets.isValidSecondaryTarget(zombie, entity);
    }
}