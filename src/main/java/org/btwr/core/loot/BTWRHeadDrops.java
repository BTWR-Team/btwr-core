package org.btwr.core.loot;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import org.btwr.shared_library.api.registry.HeadDropRegistry;

public class BTWRHeadDrops {
    public static void initialize() {
        HeadDropRegistry.registerDrop(EntityType.SKELETON, Items.SKELETON_SKULL);
        HeadDropRegistry.registerDrop(EntityType.WITHER_SKELETON, Items.WITHER_SKELETON_SKULL);
        HeadDropRegistry.registerDrop(EntityType.PLAYER, Items.PLAYER_HEAD);
        HeadDropRegistry.registerDrop(EntityType.ZOMBIE, Items.ZOMBIE_HEAD);
        HeadDropRegistry.registerDrop(EntityType.CREEPER, Items.CREEPER_HEAD);
        HeadDropRegistry.registerDrop(EntityType.PIGLIN, Items.PIGLIN_HEAD);
    }
}
