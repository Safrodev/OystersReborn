package safro.oysters.reborn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.util.math.BlockPos;
import safro.oysters.reborn.blocks.OysterBasketContainer;
import safro.oysters.reborn.blocks.OysterBasketEntity;
import safro.oysters.reborn.blocks.OysterBasketGui;
import safro.oysters.reborn.blocks.OysterBlockManager;
import safro.oysters.reborn.oysters.OysterBreed;
import safro.oysters.reborn.oysters.OysterContainer;
import safro.oysters.reborn.oysters.OysterEntity;
import safro.oysters.reborn.oysters.OysterGui;

import java.util.Arrays;

public class OystersRebornClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    ScreenProviderRegistry.INSTANCE.registerFactory(oysterBreed.getContainerIdentifier(), ((syncid, identifier, player, buf) -> {
                        BlockPos pos = buf.readBlockPos();
                        OysterEntity oysterEntity = (OysterEntity) player.world.getBlockEntity(pos);
                        return new OysterGui(oysterEntity,
                                new OysterContainer(syncid, player.getInventory(), oysterEntity),
                                oysterBreed.getName(), "block.oystersreborn."+ oysterBreed.getName());
                    }));
                });
        ScreenProviderRegistry.INSTANCE.registerFactory(OysterBlockManager.OYSTER_BASKET_CONTAINER_IDENTIFIER, ((syncid, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            OysterBasketEntity oysterBasketEntity = (OysterBasketEntity) player.world.getBlockEntity(pos);
            return new OysterBasketGui(oysterBasketEntity,
                    new OysterBasketContainer(syncid, player.getInventory(), oysterBasketEntity),
                    "block.oystersreborn.oyster_basket", "block.oystersreborn.oyster_basket");
        }));

    }
}
