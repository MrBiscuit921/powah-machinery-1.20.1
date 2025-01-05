package net.mrbiscuit.powahmachinery.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mrbiscuit.powahmachinery.PowahMachinery;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrbiscuit.powahmachinery.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PowahMachinery.MOD_ID, "powahmachinery"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.powahmachinery"))
                    .icon(() -> new ItemStack(ModBlocks.Powered_Furnace)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.Powered_Furnace);


                    }).build());


    public static void registerItemGroups() {
        PowahMachinery.LOGGER.info("Registering Item Groups for " + PowahMachinery.MOD_ID);
    }
}