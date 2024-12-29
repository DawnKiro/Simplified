package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.voxelden.simplified.client.SimplifiedClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BlockRenderer.class, remap = false)
public class BlockRendererMixin {
    @WrapOperation(method = "processQuad", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/renderer/v1/material/RenderMaterial;blendMode()Lnet/fabricmc/fabric/api/renderer/v1/material/BlendMode;"))
    private BlendMode makeSolidMeshes(RenderMaterial instance, Operation<BlendMode> original) {
        AbstractBlockRenderContextAccessor context = (AbstractBlockRenderContextAccessor) this;
        return SimplifiedClient.isInnerSmartCullBlock(context.getSlice(), context.getPos(), context.getState()) ? BlendMode.SOLID : original.call(instance);
    }
}
