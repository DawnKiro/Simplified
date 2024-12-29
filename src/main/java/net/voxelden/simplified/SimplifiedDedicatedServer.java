package net.voxelden.simplified;

import net.fabricmc.api.DedicatedServerModInitializer;

public class SimplifiedDedicatedServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Simplified.active(true);
    }
}
