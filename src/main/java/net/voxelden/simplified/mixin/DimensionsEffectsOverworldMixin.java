package net.voxelden.simplified.mixin;

import net.minecraft.client.render.DimensionEffects;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionEffects.Overworld.class)
public class DimensionsEffectsOverworldMixin {
    @Inject(method = "isSunRisingOrSetting", at = @At("HEAD"), cancellable = true)
    private void nuhUh(float skyAngle, CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(true)) cir.setReturnValue(false);
    }
}
