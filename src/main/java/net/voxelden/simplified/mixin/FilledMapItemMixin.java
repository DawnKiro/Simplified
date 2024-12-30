package net.voxelden.simplified.mixin;

import net.minecraft.item.FilledMapItem;
import net.voxelden.simplified.Simplified;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FilledMapItem.class)
public class FilledMapItemMixin {
    @ModifyVariable(method = "updateColors", at = @At(value = "STORE"), ordinal = 5)
    private int fillMoreMap(int value) {
        return Simplified.threadActive(false) ? (int) (value * 1.2) : value;
    }
}
