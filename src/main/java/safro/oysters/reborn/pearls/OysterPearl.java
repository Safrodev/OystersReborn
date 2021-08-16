package safro.oysters.reborn.pearls;

import net.minecraft.item.Item;
import safro.oysters.reborn.OystersReborn;


public class OysterPearl extends Item {

    private String identifier;

    public OysterPearl(String name) {
        super(new Item.Settings().group(OystersReborn.oysterGroup));
        this.identifier = name;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
