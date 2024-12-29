package net.voxelden.simplified.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.voxelden.simplified.Simplified;

public class Networking {
    public static void register() {
        PayloadTypeRegistry.playS2C().register(EnableModPacket.ID, EnableModPacket.CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (Simplified.threadActive(false)) sender.sendPacket(EnableModPacket.INSTANCE);
        });
    }
}
