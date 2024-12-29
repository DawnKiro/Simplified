package net.voxelden.simplified.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Unique
    private VillagerEntity getThis() {
        return (VillagerEntity) (Object) this;
    }

    @Inject(method = "canBreed", at = @At("HEAD"), cancellable = true)
    private void noVillagerBreeding0(CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(getThis().isClient())) cir.setReturnValue(false);
    }

    @Inject(method = "isReadyToBreed", at = @At("HEAD"), cancellable = true)
    private void noVillagerBreeding1(CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(getThis().isClient())) cir.setReturnValue(false);
    }

    @Inject(method = "wantsToStartBreeding", at = @At("HEAD"), cancellable = true)
    private void noVillagerBreeding2(CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(getThis().isClient())) cir.setReturnValue(false);
    }

    @Inject(method = "fillRecipes", at = @At("HEAD"), cancellable = true)
    private void noRecipesForYou(CallbackInfo ci) {
        if (Simplified.threadActive(getThis().isClient())) ci.cancel();
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void dontTalkToStrangers(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (Simplified.threadActive(getThis().isClient())) cir.setReturnValue(ActionResult.PASS);
    }
}
