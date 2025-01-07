package net.mrbiscuit.powahmachinery.integration.jade;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.mrbiscuit.powahmachinery.block.FurnaceBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum ComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(
            ITooltip tooltip,
            BlockAccessor accessor,
            IPluginConfig config
    ) {
        // Ensure the accessor target is a FurnaceBlockEntity
        if (accessor.getBlockEntity() instanceof FurnaceBlockEntity furnace) {
            long energy = furnace.getEnergyStorage().amount; // Fetch current energy
            long maxEnergy = furnace.getEnergyStorage().capacity; // Fetch max energy

            // Format and add the tooltip
            tooltip.add(Text.translatable("tooltip.powahmachinery.energy", energy, maxEnergy).formatted(Formatting.GREEN));
        }
    }

    @Override
    public Identifier getUid() {

        return new Identifier("powahmachinery", "jade_integration");
    }
}