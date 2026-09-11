package org.btwr.core;

import org.btwr.core.api.block.SpawnPredicateTypes;
import org.btwr.core.api.world.BlightSpreadRegistry;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.config.ModConfig;
import org.btwr.core.data.ModDataAttachments;
import org.btwr.core.event.ModEvents;
import org.btwr.core.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.btwr.core.loot.BTWRHeadDrops;
import org.btwr.core.networking.ModNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BTWRMod implements ModInitializer {
    public static final String MOD_ID = "btwr";
    public static final String MOD_NAME = "BTWR: Core";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing BTWR: Core.");

        //ModDifficulties.initialize();
        ModConfig.initialize();
        ModBlocks.initialize();
        ModItems.initialize();
        ModDataAttachments.initialize();
        ModItemGroup.initialize();
        ModEvents.initialize();
        ModNetworking.initialize();

        SpawnPredicateTypes.initialize();

        // Register default conditions for blight being able to spread
        BlightSpreadRegistry.registerDefaults();

        // Register default head drops when mobs are killed
        BTWRHeadDrops.initialize();
    }

}