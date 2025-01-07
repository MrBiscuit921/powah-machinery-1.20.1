package net.mrbiscuit.powahmachinery;

import net.fabricmc.api.ModInitializer;

import net.mrbiscuit.powahmachinery.block.ModBlocks;
import net.mrbiscuit.powahmachinery.item.ModItemGroups;
import net.mrbiscuit.powahmachinery.registry.ModBlockEntities;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowahMachinery implements ModInitializer {
	public static final String MOD_ID = "powahmachinery";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItemGroups.registerItemGroups();

		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
	}
}