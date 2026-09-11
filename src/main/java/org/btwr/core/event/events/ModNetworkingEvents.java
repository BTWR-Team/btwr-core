package org.btwr.core.event.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class ModNetworkingEvents {
    public static void initialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(ModNetworkingEvents::onServerStartedEvent);
        ServerPlayConnectionEvents.JOIN.register(ModNetworkingEvents::onPlayerJoinEvent);
    }

    private static void onServerStartedEvent(MinecraftServer server) {
        //initializeSaveData(server);
    }

    private static void onPlayerJoinEvent(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        //syncSaveData(handler, sender, server);
    }

    /**
    private static void initializeSaveData(MinecraftServer server) {
        // Difficulty SaveData
        BTWRDifficultySaveData saveData = BTWRDifficultySaveData.get(server);
        if (saveData != null) {
            saveData.initialize(server);
        }
    }


    private static void syncSaveData(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        // Difficulty SaveData
        BTWRDifficultySaveData saveData = BTWRDifficultySaveData.get(server);
        if (saveData != null) {
            saveData.sync(handler, sender, server);
        }
    }
     **/
}