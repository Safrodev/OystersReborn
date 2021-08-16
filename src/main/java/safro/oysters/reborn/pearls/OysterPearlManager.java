package safro.oysters.reborn.pearls;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.oysters.reborn.OystersReborn;
import safro.oysters.reborn.oysters.OysterBreed;

import java.util.Arrays;

public class OysterPearlManager {

    public static OysterPearl CLEAN_PEARL = new OysterPearl("clean_pearl");
    public static OysterPearl FLAWLESS_PEARL = new OysterPearl("flawless_pearl");

    public static void init() {
        Arrays.stream(OysterBreed.values())
                .forEach(oysterBreed -> {
                    if(oysterBreed == OysterBreed.CLEAN) {
                        Registry.register(Registry.ITEM,new Identifier(OystersReborn.MODID, CLEAN_PEARL.getIdentifier()), CLEAN_PEARL);
                    } else if(oysterBreed == OysterBreed.FLAWLESS) {
                        Registry.register(Registry.ITEM,new Identifier(OystersReborn.MODID, FLAWLESS_PEARL.getIdentifier()), FLAWLESS_PEARL);
                    } else {
                        Registry.register(Registry.ITEM,
                                new Identifier(OystersReborn.MODID, oysterBreed.getOysterPearl().getIdentifier()),
                                oysterBreed.getOysterPearl());
                    }
                });
    }
}
