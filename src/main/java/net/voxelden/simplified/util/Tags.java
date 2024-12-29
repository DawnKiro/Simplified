package net.voxelden.simplified.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.voxelden.simplified.Simplified;

public class Tags {
    public static final TagKey<Block> USES_SMART_CULLING = TagKey.of(RegistryKeys.BLOCK, Simplified.id("uses_smart_culling"));
    public static final TagKey<Block> DIRT_PATH_SURVIVES = TagKey.of(RegistryKeys.BLOCK, Simplified.id("dirt_path_survives"));
    public static final TagKey<Block> MINESHAFT_CANNOT_GENERATE = TagKey.of(RegistryKeys.BLOCK, Simplified.id("mineshaft_cannot_generate"));

    public static boolean dirtPathSurvives(BlockState blockState, WorldView world, BlockPos blockPos) {
        return blockState.isIn(Tags.DIRT_PATH_SURVIVES) || !Block.isFaceFullSquare(blockState.getSidesShape(world, blockPos), Direction.DOWN);
    }
}
