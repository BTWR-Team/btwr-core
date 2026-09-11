package org.btwr.core.data.attached;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.btwr.shared_library.api.data.UpdateRequiringData;

public class CreeperExplodeData extends UpdateRequiringData<HostileEntity> {

    public static Codec<CreeperExplodeData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("is_determined_to_explode").forGetter(CreeperExplodeData::getIsDeterminedToExplode)
            ).apply(instance, CreeperExplodeData::new)
    );

    public static PacketCodec<ByteBuf, CreeperExplodeData> PACKET_CODEC = PacketCodecs.codec(CODEC);

    private boolean isDeterminedToExplode;

    public CreeperExplodeData(boolean isDeterminedToExplode) {
        this.isDeterminedToExplode = isDeterminedToExplode;
    }

    public boolean getIsDeterminedToExplode() {
        return isDeterminedToExplode;
    }

    public void setDeterminedToExplode(boolean value) {
        isDeterminedToExplode = value;
    }

}