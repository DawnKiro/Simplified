package net.voxelden.simplified.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.voxelden.simplified.Simplified;

public class EnableModPacket implements CustomPayload {
    public static final EnableModPacket INSTANCE = new EnableModPacket();
    public static final CustomPayload.Id<EnableModPacket> ID = new CustomPayload.Id<>(Simplified.id("enable_mod"));
    public static final PacketCodec<RegistryByteBuf, EnableModPacket> CODEC = PacketCodec.unit(INSTANCE);

    private EnableModPacket() { }

    @Override
    public Id<EnableModPacket> getId() {
        return ID;
    }
}
