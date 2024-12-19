package net.voxelden.simplified.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.MineshaftGenerator;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MineshaftGenerator.MineshaftPart.class)
public class MineshaftGeneratorPartMixin {
    @Unique
    private static final TagKey<Block> MINESHAFT_CANNOT_GENERATE = TagKey.of(RegistryKeys.BLOCK, Simplified.id("mineshaft_cannot_generate"));

    @Redirect(method = "cannotGenerate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isLiquid()Z"))
    private boolean alsoIsBedrock(BlockState instance) {
        return instance.isIn(MINESHAFT_CANNOT_GENERATE);
    }
}
