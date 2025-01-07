package net.mrbiscuit.powahmachinery;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.mrbiscuit.powahmachinery.screen.FurnaceScreen;
import net.mrbiscuit.powahmachinery.screen.ModScreens;

public class PowahMachineryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreens.FURNACE_SCREEN_HANDLER, FurnaceScreen::new);
    }
}
