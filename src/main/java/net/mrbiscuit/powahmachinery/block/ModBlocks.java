package net.mrbiscuit.powahmachinery.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.item.BlockItem;
import net.mrbiscuit.powahmachinery.PowahMachinery;
import net.mrbiscuit.powahmachinery.block.furnace.PoweredFurnaceBlock;

public class ModBlocks {
    public static final Block Powered_Furnace = registerBlock("powered_furnace", new PoweredFurnaceBlock(FabricBlockSettings.copyOf(Blocks.FURNACE)));

    public static void registerModBlocks() {
        PowahMachinery.LOGGER.info("Registering ModBlocks for " + PowahMachinery.MOD_ID);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(PowahMachinery.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(PowahMachinery.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

}
