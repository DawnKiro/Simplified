package net.voxelden.simplified.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.dynamic.CodecHolder;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.jetbrains.annotations.NotNull;

public record DistanceDFType(@NotNull DistanceType distanceType, int originX, int originY, int originZ, double scaleX, double scaleY, double scaleZ) implements DensityFunction {
    public static final MapCodec<DistanceDFType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DistanceType.CODEC.optionalFieldOf("distance_type", DistanceType.EUCLIDEAN).forGetter(DistanceDFType::distanceType),
            Codec.INT.optionalFieldOf("origin_x", 0).forGetter(DistanceDFType::originX),
            Codec.INT.optionalFieldOf("origin_y", 0).forGetter(DistanceDFType::originY),
            Codec.INT.optionalFieldOf("origin_z", 0).forGetter(DistanceDFType::originZ),
            Codec.DOUBLE.optionalFieldOf("scale_x", 1d).forGetter(DistanceDFType::scaleX),
            Codec.DOUBLE.optionalFieldOf("scale_y", 1d).forGetter(DistanceDFType::scaleY),
            Codec.DOUBLE.optionalFieldOf("scale_z", 1d).forGetter(DistanceDFType::scaleZ)
    ).apply(instance, DistanceDFType::new));
    public static final CodecHolder<DistanceDFType> CODEC_HOLDER = CodecHolder.of(CODEC);

    @Override
    public double sample(DensityFunction.NoisePos pos) {
        double dx = Math.abs(originX - pos.blockX()) * scaleX;
        double dy = Math.abs(originY - pos.blockY()) * scaleY;
        double dz = Math.abs(originZ - pos.blockZ()) * scaleZ;

        return switch (distanceType) {
            case EUCLIDEAN -> Math.sqrt(dx * dx + dy * dy + dz * dz);
            case MANHATTAN -> dx + dy + dz;
            case CHEBYSHEV -> Math.max(Math.max(dx, dy), dz);
        };
    }

    @Override
    public void fill(double[] densities, DensityFunction.EachApplier applier) {
        applier.fill(densities, this);
    }

    @Override
    public DensityFunction apply(DensityFunction.DensityFunctionVisitor visitor) {
        return visitor.apply(new DistanceDFType(distanceType, originX, originY, originZ, scaleX, scaleY, scaleZ));
    }

    @Override
    public double minValue() {
        return 0;
    }

    @Override
    public double maxValue() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public CodecHolder<? extends DensityFunction> getCodecHolder() {
        return CODEC_HOLDER;
    }

    public enum DistanceType implements StringIdentifiable {
        EUCLIDEAN("euclidean"),
        MANHATTAN("manhattan"),
        CHEBYSHEV("chebyshev");

        public static final Codec<DistanceType> CODEC = StringIdentifiable.createCodec(DistanceType::values);
        private final String name;

        DistanceType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
