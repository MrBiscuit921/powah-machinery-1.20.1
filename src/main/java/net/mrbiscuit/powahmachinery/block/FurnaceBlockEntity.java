package net.mrbiscuit.powahmachinery.block;


import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.util.math.random.Random;
import net.mrbiscuit.powahmachinery.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class FurnaceBlockEntity extends BlockEntity {

    private final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10000, 100, 100);
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY); // Slot 0: Input, Slot 1: Output

    public FurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWERED_FURNACE_ENTITY, pos, state);
    }

    public SimpleEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public void scheduledTick(BlockState state, World world, BlockPos pos, Random random) {
        world.getBlockEntity(pos, ModBlockEntities.POWERED_FURNACE_ENTITY).ifPresent(furnaceBlockEntity -> {
            FurnaceBlockEntity.tick(world, pos, state, furnaceBlockEntity);
        });
    }

    public static void tick(World world, BlockPos pos, BlockState state, FurnaceBlockEntity blockEntity) {
        if (world == null || world.isClient) return;

        if (blockEntity.energyStorage.getAmount() > 50 && !blockEntity.inventory.get(0).isEmpty()) {
            ItemStack input = blockEntity.inventory.get(0);
            ItemStack output = blockEntity.inventory.get(1);

            // Check if input is smeltable
            ItemStack smeltingResult = getSmeltingResult(input);
            if (!smeltingResult.isEmpty()) {
                // Consume energy
                try (var transaction = Transaction.openOuter()) {
                    blockEntity.energyStorage.extract(50, transaction);
                    transaction.commit();
                }

                // Process the smelting
                input.decrement(1); // Decrease input
                if (output.isEmpty()) {
                    blockEntity.inventory.set(1, smeltingResult.copy());
                } else if (output.getItem() == smeltingResult.getItem()) {
                    output.increment(1); // Increase output
                }
            }
        }
    }

    private static ItemStack getSmeltingResult(ItemStack input) {
        // Replace with actual smelting logic (e.g., FurnaceRecipes or similar)
        return ItemStack.EMPTY; // Placeholder
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        energyStorage.amount = nbt.getLong("Energy");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putLong("Energy", energyStorage.amount);
    }


}
