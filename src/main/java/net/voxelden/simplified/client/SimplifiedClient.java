package net.voxelden.simplified.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.irisshaders.iris.Iris;
import net.voxelden.simplified.Simplified;
import net.voxelden.simplified.util.Files;

public class SimplifiedClient implements ClientModInitializer {
    private static final String SHADERPACK_NAME = "simplified-builtin.zip";

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            try {
                Files.copyFromAssets(client, Simplified.id("shaderpack/" + SHADERPACK_NAME), Iris.getShaderpacksDirectory().resolve(SHADERPACK_NAME));
                Iris.getIrisConfig().setShaderPackName(SHADERPACK_NAME);
                Iris.getIrisConfig().setShadersEnabled(true);
            } catch (Exception e) {
                Simplified.LOGGER.info(e.toString());
            }
        });
    }
}
