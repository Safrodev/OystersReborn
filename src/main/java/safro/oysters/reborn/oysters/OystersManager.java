package safro.oysters.reborn.oysters;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OystersManager {

    public static Map<Identifier, BlockEntityType<BlockEntity>> oysterEntityTypeMap = new HashMap<>();
    public static BlockEntityType<OysterEntity> OYSTER_BLOCK_ENTITY;

    public static void init() {

        OYSTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "oystersreborn:oyster_block_entity", FabricBlockEntityTypeBuilder.create(OysterEntity::new,
                OysterBreed.FLAWLESS.getOysterBlock(),
                OysterBreed.COAL.getOysterBlock(),
                OysterBreed.BLEMISHED.getOysterBlock(),
                OysterBreed.BONE_MEAL.getOysterBlock(),
                OysterBreed.CLEAN.getOysterBlock(),
                OysterBreed.DIAMOND.getOysterBlock(),
                OysterBreed.DIRT.getOysterBlock(),
                OysterBreed.EMERALD.getOysterBlock(),
                OysterBreed.ENDER_PEARL.getOysterBlock(),
                OysterBreed.GLOWSTONE.getOysterBlock(),
                OysterBreed.GOLD.getOysterBlock(),
                OysterBreed.IRON.getOysterBlock(),
                OysterBreed.LAPIS.getOysterBlock(),
                OysterBreed.QUARTZ.getOysterBlock(),
                OysterBreed.REDSTONE.getOysterBlock(),
                OysterBreed.SAND.getOysterBlock(),
                OysterBreed.STONE.getOysterBlock(),
                OysterBreed.STRING.getOysterBlock(),
                OysterBreed.WOOD.getOysterBlock()
        ).build(null));

        buildOysterEntityTypeMap();
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    oysterBreed.getOysterBlock().setOysterBreed(oysterBreed);

                    ContainerProviderRegistry.INSTANCE.registerFactory(oysterBreed.getContainerIdentifier(), (syncid, identifier, player, buf) -> {
                        return new OysterContainer(syncid, player.getInventory(), (OysterEntity) player.world.getBlockEntity(buf.readBlockPos()));
                    });

                    Registry.register(Registry.BLOCK, oysterBreed.getIdentifier(), oysterBreed.getOysterBlock());
                    Registry.register(Registry.ITEM,
                            oysterBreed.getIdentifier(),
                            oysterBreed.getOysterBlockItem());
                });
    }
    private static void buildOysterEntityTypeMap() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> oysterEntityTypeMap.put(oysterBreed.getIdentifier(), null));
    }
}
