package org.btwr.core.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;

public final class BTWRZombieTargets {

    public static boolean isValidSecondaryTarget(ZombieEntity zombie, LivingEntity target) {
        if(target instanceof PlayerEntity) {
            return false;
        }

        /**
        if(target instanceof AnimalEntity animal) {
            return true;
        }
         **/


        return false;
    }
}