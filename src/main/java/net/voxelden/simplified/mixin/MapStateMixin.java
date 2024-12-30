package net.voxelden.simplified.mixin;

import net.minecraft.item.map.MapState;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MapState.class)
public class MapStateMixin {
    @ModifyArgs(method = "of(DDBZZLnet/minecraft/registry/RegistryKey;)Lnet/minecraft/item/map/MapState;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/map/MapState;<init>(IIBZZZLnet/minecraft/registry/RegistryKey;)V"))
    private static void centerLargestMaps(Args args) {
        if (Simplified.threadActive(false) && (Byte) args.get(2) >= 4) {
            args.set(0, 0);
            args.set(1, 0);
        }
    }
}
