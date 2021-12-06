package safro.oysters.reborn.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import safro.oysters.reborn.OystersReborn;

import java.util.List;

public class WorldGenRegistry {

    public static final Feature<DefaultFeatureConfig> OYSTER_FEATURE = register(
            "oyster_feature", new OysterFeature(DefaultFeatureConfig.CODEC));

    // I did this in case more features get added
    public static <T extends FeatureConfig> Feature<T> register(String id, Feature<T> t) {
        Registry.register(Registry.FEATURE, new Identifier(OystersReborn.MODID, id), t);
        return t;
    }

    public static final class WorldGenFeatures {

        public static List<PlacementModifier> modifiersCount(int count) {
            return List.of(SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, CountPlacementModifier.of(count), BiomePlacementModifier.of());
        }

        public static final ConfiguredFeature<?, ?> CONFIGURED_OYSTER_FEATURE = WorldGenRegistry.OYSTER_FEATURE.configure(DefaultFeatureConfig.DEFAULT);

        public static void register() {
            RegistryKey<PlacedFeature> OYSTER_KEY = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                    new Identifier(OystersReborn.MODID, "oyster_feature"));
            Registry.register(BuiltinRegistries.PLACED_FEATURE, OYSTER_KEY.getValue(), WorldGenFeatures.CONFIGURED_OYSTER_FEATURE.withPlacement(modifiersCount(32)));
            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, OYSTER_KEY);
        }
    }
}
