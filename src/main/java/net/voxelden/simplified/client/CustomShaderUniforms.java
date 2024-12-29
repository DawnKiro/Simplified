package net.voxelden.simplified.client;

import net.irisshaders.iris.gl.uniform.UniformHolder;
import net.irisshaders.iris.helpers.StringPair;
import net.irisshaders.iris.uniforms.FrameUpdateNotifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;

import java.util.ArrayList;
import java.util.List;

import static net.irisshaders.iris.gl.uniform.UniformUpdateFrequency.PER_TICK;

public class CustomShaderUniforms {
    private static final List<StringPair> DEFINES = new ArrayList<>();

    public static void register() {
        define("CLOUDS_OFF", CloudRenderMode.OFF.getId());
        define("CLOUDS_FAST", CloudRenderMode.FAST.getId());
        define("CLOUDS_FANCY", CloudRenderMode.FANCY.getId());
    }

    public static void addCustomShaderUniforms(MinecraftClient client, UniformHolder holder, FrameUpdateNotifier updateNotifier) {
        holder.uniform1i(PER_TICK, "cloudQuality", () -> client.options.getCloudRenderMode().getValue().getId());
    }

    private static <T> void define(String name, T value) {
        DEFINES.add(new StringPair(name, String.valueOf(value)));
    }

    public static List<StringPair> getDefines() {
        return DEFINES;
    }
}
