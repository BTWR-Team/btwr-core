package org.btwr.core.mixin.client;

import com.mojang.serialization.Lifecycle;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void btwr$resetCreationData(CallbackInfo ci) {
        //BTWRWorldCreationData.reset();
    }

    @Inject(method = "startServer", at = @At("TAIL"))
    private void btwr$setInitialDifficulty(
            LevelProperties.SpecialProperty specialProperty,
            CombinedDynamicRegistries<ServerDynamicRegistryType> combinedDynamicRegistries,
            Lifecycle lifecycle,
            CallbackInfo ci
    ) {
        /**
        BTWRDifficulty difficulty = BTWRWorldCreationData.getSelected();

        if (difficulty == null) return;

        MinecraftClient client = MinecraftClient.getInstance();
        MinecraftServer server = client.getServer();

        if (server == null) {
            BTWRMod.LOGGER.error("startServer finished but the integrated server is null - selected difficulty was not applied");
            return;
        }

        server.execute(() -> BTWRDifficultySaveData.get(server).setDifficulty(difficulty));

        ClientBTWRDifficultyCache.set(difficulty);

        BTWRWorldCreationData.reset();


        BTWRDifficulty difficulty = BTWRWorldCreationData.getSelected();

        if (difficulty != null) {
            ClientBTWRDifficultyCache.set(difficulty);
        }
         **/
    }

}