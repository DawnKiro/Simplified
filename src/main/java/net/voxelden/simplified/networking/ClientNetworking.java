package net.voxelden.simplified.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.voxelden.simplified.client.SimplifiedClient;

public class ClientNetworking {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(EnableModPacket.ID, (payload, context) -> SimplifiedClient.active(context.client(), true));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> SimplifiedClient.active(client, false));
    }
}
