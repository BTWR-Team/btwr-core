package org.btwr.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import org.btwr.core.block.ModBlocks;
import org.btwr.core.networking.BTWR_ClientNetworking;
import org.btwr.shared_library.util.utils.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BTWRModClient implements ClientModInitializer {
    public static final String MOD_ID = "btwr";
    public static final String MOD_NAME = "BTWR: Core";
    public static final Logger LOGGER = LoggerFactory.getLogger(BTWRMod.MOD_ID);

    @Override
    public void onInitializeClient() {
        //this.hideArbitraryFabricAPIMods();
        BTWR_ClientNetworking.register();

        // Custom model for blight based on it's level
        ModelPredicateProviderRegistry.register(
                ModBlocks.BLIGHT.asItem(),
                IdUtils.ofBTWR("blight_level"),
                (stack, world, entity, seed) -> {
                    NbtComponent data = stack.get(DataComponentTypes.CUSTOM_DATA);
                    if (data != null && data.contains("level")) {
                        return data.copyNbt().getInt("level") / 3f;
                    }
                    return 0f;
                }
        );
    }

    // This removes all arbitrary mods that are loaded by FAPI by default from showing up in the loaded mods text,
    // so it can display a more accurate number of the actual mods loaded
    private void hideArbitraryFabricAPIMods() {
        List<ModContainer> filteredMods = FabricLoader.getInstance().getAllMods().stream()
                .filter(mod -> !mod.getMetadata().getId().startsWith("fabric"))
                .toList();

        System.out.println("Filtered Mods Count: " + filteredMods.size());
        filteredMods.forEach(mod -> System.out.println("Loaded Mod: " + mod.getMetadata().getName()));
    }

}