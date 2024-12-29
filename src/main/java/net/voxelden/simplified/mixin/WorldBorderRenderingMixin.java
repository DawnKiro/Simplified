package net.voxelden.simplified.mixin;

import net.minecraft.client.render.WorldBorderRendering;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.border.WorldBorder;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderRendering.class)
public class WorldBorderRenderingMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void canYouNotRenderAwfulWorldBorderThanks(WorldBorder border, Vec3d vec3d, double d, double e, CallbackInfo ci) {
        if (Simplified.threadActive(true)) ci.cancel();
    }
}
