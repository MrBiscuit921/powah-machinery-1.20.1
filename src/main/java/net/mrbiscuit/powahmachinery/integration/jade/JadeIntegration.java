package net.mrbiscuit.powahmachinery.integration.jade;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.util.Identifier;
import snownee.jade.api.*;

@WailaPlugin
public class JadeIntegration implements IWailaPlugin {

    public static final Identifier CUSTOM_FURNACE_ENERGY = new Identifier("powahmachinery", "custom_furnace_energy");


    @Override
    public void registerClient(IWailaClientRegistration registration) {
        //TODO register component providers, icon providers, callbacks, and config options here

        // Register the block component to display energy info for CustomFurnaceBlockEntity
        registration.registerBlockComponent(ComponentProvider.INSTANCE, AbstractFurnaceBlock.class);
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        //TODO register data providers
    }
}