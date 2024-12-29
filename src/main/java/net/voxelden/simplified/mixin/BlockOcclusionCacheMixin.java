package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.voxelden.simplified.client.SimplifiedClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BlockOcclusionCache.class, remap = false)
public class BlockOcclusionCacheMixin {
    @WrapOperation(method = "shouldDrawSide", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/pipeline/BlockOcclusionCache;isFullShape(Lnet/minecraft/util/shape/VoxelShape;)Z"))
    private boolean innerSmartBlocksCull(VoxelShape selfShape, Operation<Boolean> original, @Local(argsOnly = true) BlockView view, @Local BlockPos.Mutable blockPos, @Local(ordinal = 1) BlockState blockState) {
        return original.call(selfShape) || SimplifiedClient.isInnerSmartCullBlock(view, blockPos.toImmutable(), blockState);
    }
}
