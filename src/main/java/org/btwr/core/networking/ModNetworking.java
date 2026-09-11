package org.btwr.core.networking;

public class ModNetworking {
    public static void initialize() {
        registerDifficultyNetworking();
    }

    private static void registerDifficultyNetworking() {
        //PayloadTypeRegistry.playC2S().register(UpdateBTWRDifficultyC2SPacket.ID, UpdateBTWRDifficultyC2SPacket.PACKET_CODEC);
        //PayloadTypeRegistry.playS2C().register(SyncBTWRDifficultyS2CPacket.ID, SyncBTWRDifficultyS2CPacket.PACKET_CODEC);
        //ServerPlayNetworking.registerGlobalReceiver(UpdateBTWRDifficultyC2SPacket.ID, ModNetworking::difficultyPayloadHandler);
    }

    /**
    private static void difficultyPayloadHandler(UpdateBTWRDifficultyC2SPacket packet, ServerPlayNetworking.Context context) {
        context.server().execute(() -> {
            if (!context.player().hasPermissionLevel(2))
                return;

            //BTWRDifficulty difficulty = DifficultyRegistry.get(packet.info().id());

            //if (difficulty == null) return;

            MinecraftServer server = context.server();

            //BTWRDifficultySaveData.get(server).setDifficulty(difficulty);

            //SyncBTWRDifficultyS2CPacket sync = new SyncBTWRDifficultyS2CPacket(difficulty.info());

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                //ServerPlayNetworking.send(player, sync);
            }
        });
    }
     **/
}