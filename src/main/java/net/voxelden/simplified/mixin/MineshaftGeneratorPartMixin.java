package net.voxelden.simplified.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.structure.MineshaftGenerator;
import net.voxelden.simplified.util.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MineshaftGenerator.MineshaftPart.class)
public class MineshaftGeneratorPartMixin {
    @Redirect(method = "cannotGenerate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isLiquid()Z"))
    private boolean alsoIsBedrock(BlockState instance) {
        return instance.isIn(Tags.MINESHAFT_CANNOT_GENERATE);
    }
}
