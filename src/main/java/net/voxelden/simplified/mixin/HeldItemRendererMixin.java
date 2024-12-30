package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.voxelden.simplified.Simplified;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Shadow @Final private static RenderLayer MAP_BACKGROUND;

    @Unique
    private float scaleMap(int intPos) {
        return intPos == -7 ? -16 : intPos == 135 ? 144 : intPos;
    }

    @Unique
    private int getPrettyYaw(float yaw) {
        return (Math.round(yaw) % 360 + 360) % 360;
    }

    @Unique
    private Text getMapCoordsText(@Nullable Entity cameraEntity) {
        return cameraEntity == null ? Text.empty() : Text.literal(cameraEntity.getBlockX() + ", " + cameraEntity.getBlockY() + ", " + cameraEntity.getBlockZ() + " (" + getPrettyYaw(cameraEntity.getHeadYaw()) + ")");
    }

    @WrapOperation(method = "renderFirstPersonMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"))
    private VertexConsumer changeMapBackgroundScale(VertexConsumerProvider instance, RenderLayer renderLayer, Operation<VertexConsumer> original) {
        return original.call(instance, Simplified.threadActive(true) ? MAP_BACKGROUND : renderLayer);
    }

    @WrapOperation(method = "renderFirstPersonMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;vertex(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/client/render/VertexConsumer;"))
    private VertexConsumer changeMapBackgroundScale(VertexConsumer instance, Matrix4f matrix, float x, float y, float z, Operation<VertexConsumer> original) {
        if (Simplified.threadActive(true)) {
            x = scaleMap((int) x);
            y = scaleMap((int) y);
        }
        return original.call(instance, matrix, x, y, z);
    }

    @Inject(method = "renderFirstPersonMap", at = @At("TAIL"))
    private void changeMapBackgroundScale(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemStack stack, CallbackInfo ci) {
        if (Simplified.threadActive(true)) {
            matrices.push();
            matrices.translate(0f, 0f, -0.025f);
            matrices.scale(0.75f, 0.75f, 1f);
            matrices.translate(2f, -10f, -0.1f);
            MinecraftClient.getInstance().textRenderer.draw(getMapCoordsText(MinecraftClient.getInstance().getCameraEntity()), 0f, 0f, 0, false, matrices.peek().getPositionMatrix(), vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light, false);
            matrices.pop();
        }
    }
}
