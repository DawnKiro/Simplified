package net.voxelden.simplified.mixin;

import net.caffeinemc.mods.sodium.client.render.frapi.render.AbstractBlockRenderContext;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AbstractBlockRenderContext.class, remap = false)
public interface AbstractBlockRenderContextAccessor {
    @Accessor
    BlockState getState();

    @Accessor
    LevelSlice getSlice();

    @Accessor
    BlockPos getPos();
}
