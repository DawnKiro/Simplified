package net.voxelden.simplified;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.voxelden.simplified.client.SimplifiedClient;
import net.voxelden.simplified.networking.Networking;
import net.voxelden.simplified.worldgen.Worldgen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simplified implements ModInitializer {
    public static final String MOD_ID = "simplified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static boolean active = FabricLoader.getInstance().isDevelopmentEnvironment();

    @Override
    public void onInitialize() {
        LOGGER.info("Simplifying your game...");

        Worldgen.register();
        Networking.register();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }

    public static boolean active() {
        return active;
    }

    public static void active(boolean active) {
        Simplified.active = active;
    }

    public static boolean threadActive(boolean isClient) {
        return isClient ? SimplifiedClient.active() : active();
    }
}
