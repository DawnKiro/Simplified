package net.voxelden.simplified.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.voxelden.simplified.Simplified;
import net.voxelden.simplified.util.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    private boolean beNormal(BlockState instance, Operation<Boolean> original, @Local World world, @Local BlockPos pos) {
        return (Simplified.threadActive(world.isClient())) ? Tags.dirtPathSurvives(instance, world, pos.up()) : original.call(instance);
    }
}
