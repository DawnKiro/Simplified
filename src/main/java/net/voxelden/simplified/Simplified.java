package net.voxelden.simplified;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.voxelden.simplified.worldgen.Worldgen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simplified implements ModInitializer {
    public static final String MOD_ID = "simplified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Simplifying your game...");

        Worldgen.register();
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
