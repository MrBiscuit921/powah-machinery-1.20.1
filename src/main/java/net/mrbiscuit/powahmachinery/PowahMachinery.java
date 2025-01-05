package net.mrbiscuit.powahmachinery;

import net.fabricmc.api.ModInitializer;

import net.mrbiscuit.powahmachinery.block.ModBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowahMachinery implements ModInitializer {
	public static final String MOD_ID = "powahmachinery";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModBlocks.registerModBlocks();
	}
}