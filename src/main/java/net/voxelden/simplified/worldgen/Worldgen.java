package net.voxelden.simplified.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.voxelden.simplified.Simplified;

public class Worldgen {
    public static final MapCodec<DistanceDFType> DISTANCE_DF_TYPE = Registry.register(Registries.DENSITY_FUNCTION_TYPE, Simplified.id("distance"), DistanceDFType.CODEC);

    public static void register() {
    }
}
