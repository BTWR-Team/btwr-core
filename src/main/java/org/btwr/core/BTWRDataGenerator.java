package org.btwr.core;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.btwr.core.datagen.*;

public class BTWRDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BTWRRecipeProvider::new);
        pack.addProvider(BTWRBlockTagProvider::new);
        pack.addProvider(BTWRItemTagProvider::new);
        pack.addProvider(BTWREntityTypeTagProvider::new);
        pack.addProvider(BTWRLootTableProvider::new);
        pack.addProvider(BTWRModelProvider::new);
        pack.addProvider(BTWRLangProvider::new);
    }

}