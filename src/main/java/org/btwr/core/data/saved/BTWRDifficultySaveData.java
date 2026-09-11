package org.btwr.core.data.saved;


/**
public class BTWRDifficultySaveData extends ModSaveData {
    private Identifier difficultyId;
    private boolean locked;

    public static BTWRDifficultySaveData get(MinecraftServer server) {
        // This could be either the overworld or another dimension.
        ServerWorld world = server.getWorld(ServerWorld.OVERWORLD);

        if (world == null) return new BTWRDifficultySaveData(); // Return a new instance if the world is null.

        // The first time the following 'getOrCreate' function is called, it creates a new 'Data'
        // instance and stores it inside the 'PersistentStateManager'.
        // Subsequent calls to 'getOrCreate' returns the saved 'Data' NBT on disk to the Codec in our type,
        // using the Codec to decode the NBT into our saved data.
        return world.getPersistentStateManager().getOrCreate(TYPE, "btwr_difficulty");
    }

    public BTWRDifficulty getDifficulty() {
        return DifficultyRegistry.find(difficultyId).orElse(ModDifficulties.STANDARD);
    }

    public void setDifficulty(BTWRDifficulty difficulty) {
        this.difficultyId = difficulty.info().id();
        markDirty();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
        markDirty();
    }

    @Override
    public void initialize(MinecraftServer server) {
        if (difficultyId == null) {
            setDifficulty(BTWRWorldCreationData.getSelected());
            setLocked(BTWRWorldCreationData.isLocked());
            BTWRWorldCreationData.reset();
        }
    }

    @Override
    public void sync(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        DifficultyInfo info = new DifficultyInfo(difficultyId, BTWRWorldCreationData.isLocked());
        ServerPlayNetworking.send(handler.player, new SyncBTWRDifficultyS2CPacket(info));
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putString("Difficulty", difficultyId.toString());
        nbt.putBoolean("Locked", locked);
        return nbt;
    }

    public static BTWRDifficultySaveData fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        BTWRDifficultySaveData state = new BTWRDifficultySaveData();
        Identifier difficultyId = Identifier.tryParse(nbt.getString("Difficulty"));

        state.difficultyId = difficultyId != null ? difficultyId : ModDifficulties.STANDARD.info().id();
        state.locked = nbt.getBoolean("Locked");

        return state;
    }

    public static final PersistentState.Type<BTWRDifficultySaveData> TYPE = new PersistentState.Type<>(
            BTWRDifficultySaveData::new,
            BTWRDifficultySaveData::fromNbt,
            null
    );

}
 **/