package net.mrbiscuit.powahmachinery.registry;

import net.mrbiscuit.powahmachinery.PowahMachinery;
import net.mrbiscuit.powahmachinery.block.FurnaceBlockEntity;
import net.mrbiscuit.powahmachinery.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static BlockEntityType<FurnaceBlockEntity> POWERED_FURNACE_ENTITY;

    public static void registerBlockEntities() {
        POWERED_FURNACE_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(PowahMachinery.MOD_ID, "powered_furnace"),
                FabricBlockEntityTypeBuilder.create(FurnaceBlockEntity::new, ModBlocks.Powered_Furnace).build()
        );
    }
}
