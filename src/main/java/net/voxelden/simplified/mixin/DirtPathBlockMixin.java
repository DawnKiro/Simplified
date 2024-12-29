package net.voxelden.simplified.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.DirtPathBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.voxelden.simplified.Simplified;
import net.voxelden.simplified.util.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DirtPathBlock.class)
public class DirtPathBlockMixin {
    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    private void beBetterOMFG(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(world.isClient())) {
            BlockPos blockPos = pos.up();
            cir.setReturnValue(Tags.dirtPathSurvives(world.getBlockState(blockPos), world, blockPos));
        }
    }
}
