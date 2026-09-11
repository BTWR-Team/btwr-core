package org.btwr.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;
import org.btwr.core.tag.BTWRTags;

import java.util.concurrent.CompletableFuture;

public class BTWREntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public BTWREntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addToVanilla();
        addToModTags();
        addToConventionalTags();
    }

    private void addToVanilla() {}

    private void addToModTags() {}

    private void addToConventionalTags() {}

}