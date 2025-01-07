package net.mrbiscuit.powahmachinery.screen;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.mrbiscuit.powahmachinery.PowahMachinery;
import net.mrbiscuit.powahmachinery.block.FurnaceBlockEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class ModScreens {

    public static final ScreenHandlerType<FurnaceScreenHandler> FURNACE_SCREEN_HANDLER;

    static {
        // Create the screen handler type with both a factory and FeatureSet.NONE
        FURNACE_SCREEN_HANDLER = new ScreenHandlerType<>(
                (syncId, inv) -> {
                    // Use the actual furnace block entity for the correct position
                    FurnaceBlockEntity furnaceBlockEntity = (FurnaceBlockEntity) inv.player.getWorld().getBlockEntity(inv.player.getBlockPos());
                    return new FurnaceScreenHandler(syncId, inv, new SimpleInventory(2), furnaceBlockEntity);
                },
                FeatureSet.empty()
        );
        // Register the screen handler type
        Registry.register(Registries.SCREEN_HANDLER, PowahMachinery.id("furnace_screen"), FURNACE_SCREEN_HANDLER);
    }
}
