package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin {
    @WrapOperation(method = "createEnderDragonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"))
    private static DefaultAttributeContainer.Builder changeEnderDragonHealth(DefaultAttributeContainer.Builder instance, RegistryEntry<EntityAttribute> attribute, double baseValue, Operation<DefaultAttributeContainer.Builder> original) {
        return (Simplified.threadActive(false)) ? instance.add(EntityAttributes.MAX_HEALTH, 320.0) : original.call(instance, attribute, baseValue);
    }

    @Inject(method = "destroyBlocks", at = @At("HEAD"), cancellable = true)
    private void dontGriefBlocks(ServerWorld world, Box box, CallbackInfoReturnable<Boolean> cir) {
        if (Simplified.threadActive(false)) cir.setReturnValue(false);
    }
}
