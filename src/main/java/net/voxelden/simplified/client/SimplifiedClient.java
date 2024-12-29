package net.voxelden.simplified.client;

import net.fabricmc.api.ClientModInitializer;
import net.irisshaders.iris.Iris;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.voxelden.simplified.Simplified;
import net.voxelden.simplified.networking.ClientNetworking;
import net.voxelden.simplified.util.Files;
import net.voxelden.simplified.util.QuickIris;
import net.voxelden.simplified.util.Tags;

import java.nio.file.Path;

public class SimplifiedClient implements ClientModInitializer {
    private static final String SHADERPACK_NAME = "builtin.zip";
    private static boolean active = false;

    public static void active(MinecraftClient client, boolean active) {
        if (SimplifiedClient.active != active) {
            SimplifiedClient.active = active;
            Path filePath = Iris.getShaderpacksDirectory().resolve(SHADERPACK_NAME);
            if (active) {
                Simplified.LOGGER.info("RECEIVED PACKET, INSTALLING SHADER");

                Files.copyFromAssets(client, Simplified.id("shaderpacks/" + SHADERPACK_NAME), filePath);
                QuickIris.setShader(SHADERPACK_NAME, true);
            }
        }
    }

    public static boolean active() {
        return active;
    }

    public static boolean isInnerSmartCullBlock(BlockView view, BlockPos blockPos, BlockState blockState) {
        if (!blockState.isIn(Tags.USES_SMART_CULLING)) return false;
        for (Direction direction : Direction.values()) {
            BlockPos pos = blockPos.offset(direction);
            BlockState state = view.getBlockState(pos);
            if (!(state.isIn(Tags.USES_SMART_CULLING) || state.isSolidBlock(view, pos))) return false;
        }
        return true;
    }

    @Override
    public void onInitializeClient() {
        CustomShaderUniforms.register();
        ClientNetworking.register();
    }
}
