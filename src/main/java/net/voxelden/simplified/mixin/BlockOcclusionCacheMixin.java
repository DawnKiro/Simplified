package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.voxelden.simplified.client.SimplifiedClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockOcclusionCache.class, remap = false)
public class BlockOcclusionCacheMixin {
    @Inject(method = "shouldDrawSide", at = @At("RETURN"), cancellable = true)
    private void innerSmartBlocksCull(BlockState selfBlockState, BlockView view, BlockPos selfPos, Direction facing, CallbackInfoReturnable<Boolean> cir, @Local BlockPos.Mutable blockPos, @Local(ordinal = 1) BlockState blockState) {
        if (SimplifiedClient.isInnerSmartCullBlock(view, blockPos.toImmutable(), blockState)) cir.setReturnValue(false);
    }
}
