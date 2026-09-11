package org.btwr.core.mixin.world;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import org.btwr.core.world.WorldAdded;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAdded {
    @Shadow public abstract @Nullable MinecraftServer getServer();

    /**
    @Override
    public BTWRDifficulty btwr$difficulty() {
        return btwr$difficultyData().getDifficulty();
    }

    @Override
    public BTWRDifficultySaveData btwr$difficultyData() {
        MinecraftServer server = this.getServer();
        if (server == null) {
            throw new IllegalStateException("btwr$difficultyData() called on a client-side World");
        }
        return BTWRDifficultySaveData.get(server);
    }
    **/
}