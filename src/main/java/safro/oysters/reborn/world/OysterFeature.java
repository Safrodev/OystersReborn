package safro.oysters.reborn.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import safro.oysters.reborn.oysters.OysterBreed;

import java.util.Random;

public class OysterFeature extends Feature<DefaultFeatureConfig> {

    public OysterFeature(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        Random random = context.getRandom();
        if (random.nextFloat() <= 0.1) {
            StructureWorldAccess serverWorldAccess = context.getWorld();
            BlockPos pos = context.getOrigin();
            BlockState blockState = OysterBreed.BLEMISHED.getOysterBlock().getDefaultState();
            int placed = 0;
            int int_3 = random.nextInt(8) - random.nextInt(8);
            int int_4 = random.nextInt(8) - random.nextInt(8);
            int int_5 = serverWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, pos.getX() + int_3, pos.getZ() + int_4);
            BlockPos blockPos_2 = new BlockPos(pos.getX() + int_3, int_5, pos.getZ() + int_4);
            if (serverWorldAccess.getBlockState(blockPos_2).getBlock() == Blocks.WATER && blockState.canPlaceAt(serverWorldAccess, blockPos_2)) {
                serverWorldAccess.setBlockState(blockPos_2, blockState, 2);
                placed += 2;
            }
            return placed > 0;
        }
        return false;
    }
}
