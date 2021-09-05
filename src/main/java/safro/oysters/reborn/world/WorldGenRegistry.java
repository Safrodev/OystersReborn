package safro.oysters.reborn.world;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import safro.oysters.reborn.OystersReborn;

public class WorldGenRegistry {

    public static final Feature<DefaultFeatureConfig> OYSTER_FEATURE = register(
            "oyster_feature", new OysterFeature(DefaultFeatureConfig.CODEC));

    // I did this in case more features get added
    public static <T extends FeatureConfig> Feature<T> register(String id, Feature<T> t) {
        Registry.register(Registry.FEATURE, new Identifier(OystersReborn.MODID, id), t);
        return t;
    }

    public static final class WorldGenFeatures {
        public static final ConfiguredFeature<?, ?> CONFIGURED_OYSTER_FEATURE = WorldGenRegistry.OYSTER_FEATURE.configure(DefaultFeatureConfig.DEFAULT);
        public static final RegistryKey<ConfiguredFeature<?,?>> OYSTER_FEATURE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(OystersReborn.MODID, "oyster_feature"));

        public static void register() {
            register("oyster_feature", WorldGenFeatures.CONFIGURED_OYSTER_FEATURE.uniformRange(YOffset.getBottom(), YOffset.getTop()).spreadHorizontally().repeat(100));
        }

        private static <FC extends FeatureConfig > void register (String name, ConfiguredFeature < FC, ?>feature){
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(OystersReborn.MODID, name), feature);
        }
    }
}
