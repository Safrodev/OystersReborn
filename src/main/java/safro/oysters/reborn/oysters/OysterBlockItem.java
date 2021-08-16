package safro.oysters.reborn.oysters;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class OysterBlockItem extends BlockItem {

    private OysterBreed.OysterTier tier;

    public OysterBlockItem(Block block, Settings settings, OysterBreed.OysterTier tier) {
        super(block, settings);
        this.tier = tier;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack itemStack, World world, List<Text> list, TooltipContext tooltipContext) {
        if(tier != null) {
            list.add(new TranslatableText("oystersreborn.tooltip." + tier.toString().toLowerCase()).formatted(Formatting.AQUA));
        }
    }
}
