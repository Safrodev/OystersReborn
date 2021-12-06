package safro.oysters.reborn;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import safro.oysters.reborn.blocks.OysterBlockManager;
import safro.oysters.reborn.items.OysterItemManager;
import safro.oysters.reborn.oysters.OysterBreed;
import safro.oysters.reborn.oysters.OystersManager;
import safro.oysters.reborn.pearls.OysterPearlManager;
import safro.oysters.reborn.util.OystersConfig;
import safro.oysters.reborn.world.WorldGenRegistry;

public class OystersReborn implements ModInitializer {

	public static String MODID = "oystersreborn";
	public static ItemGroup oysterGroup = FabricItemGroupBuilder
			.build(new Identifier(MODID, MODID), () -> new ItemStack(OysterBreed.BLEMISHED.getOysterBlockItem()));

	public static OystersConfig oystersConfig;

	@Override
	public void onInitialize() {

		oystersConfig = new OystersConfig();
		oystersConfig.loadConfigs();
		WorldGenRegistry.WorldGenFeatures.register();
		OystersManager.init();
		OysterPearlManager.init();
		OysterBlockManager.init();
		OysterItemManager.init();
	}
}
