package net.mrbiscuit.powahmachinery.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.mrbiscuit.powahmachinery.block.FurnaceBlockEntity;

public class FurnaceScreenHandler extends ScreenHandler {
    private final FurnaceBlockEntity furnaceBlockEntity;  // Store the instance of FurnaceBlockEntity

    public long getEnergy() {
        return furnaceBlockEntity.getEnergyStorage().getAmount();  // Return long
    }

    public long getMaxEnergy() {
        return furnaceBlockEntity.getEnergyStorage().getCapacity();  // Return long
    }

    // Updated constructor to ensure furnaceBlockEntity is passed correctly
    public FurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, FurnaceBlockEntity furnaceBlockEntity) {
        super(ModScreens.FURNACE_SCREEN_HANDLER, syncId);
        this.furnaceBlockEntity = furnaceBlockEntity;  // Ensure the furnaceBlockEntity is initialized

        // Input slot
        this.addSlot(new Slot(inventory, 0, 56, 35));

        // Output slot
        this.addSlot(new Slot(inventory, 1, 116, 35));

        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        if (furnaceBlockEntity == null) {
            return false;
        }
        return furnaceBlockEntity.getPos().isWithinDistance(player.getBlockPos(), 64);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (index < 2) {
                // Moving from furnace inventory to player inventory
                if (!this.insertItem(originalStack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Moving from player inventory to furnace inventory
                if (!this.insertItem(originalStack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
