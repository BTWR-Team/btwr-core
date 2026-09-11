package org.btwr.core.networking.packet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.btwr.core.BTWRMod;

/**
public record SyncBTWRDifficultyS2CPacket(DifficultyInfo info) implements CustomPayload {
    public static final Id<SyncBTWRDifficultyS2CPacket> ID = new Id<>(
            Identifier.of(BTWRMod.MOD_ID, "sync_difficulty")
    );

    public static final Codec<SyncBTWRDifficultyS2CPacket> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
                DifficultyInfo.CODEC.fieldOf("info").forGetter(SyncBTWRDifficultyS2CPacket::info)
        ).apply(instance, SyncBTWRDifficultyS2CPacket::new)
    );

    public static final PacketCodec<RegistryByteBuf, SyncBTWRDifficultyS2CPacket> PACKET_CODEC =
            PacketCodec.tuple(
                    DifficultyInfo.PACKET_CODEC,
                    SyncBTWRDifficultyS2CPacket::info,
                    SyncBTWRDifficultyS2CPacket::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
 **/