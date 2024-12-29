package net.voxelden.simplified.mixin;

import net.irisshaders.iris.gl.uniform.UniformHolder;
import net.irisshaders.iris.shaderpack.properties.PackDirectives;
import net.irisshaders.iris.uniforms.CommonUniforms;
import net.irisshaders.iris.uniforms.FrameUpdateNotifier;
import net.minecraft.client.MinecraftClient;
import net.voxelden.simplified.client.CustomShaderUniforms;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommonUniforms.class)
public class CommonUniformsMixin {
    @Shadow @Final private static MinecraftClient client;

    @Inject(method = "generalCommonUniforms", at = @At("TAIL"), remap = false)
    private static void addCustomShaderUniforms(UniformHolder uniforms, FrameUpdateNotifier updateNotifier, PackDirectives directives, CallbackInfo ci) {
        CustomShaderUniforms.addCustomShaderUniforms(client, uniforms, updateNotifier);
    }
}
