package net.mrbiscuit.powahmachinery.block.furnace;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbiscuit.powahmachinery.block.FurnaceBlockEntity;
import net.mrbiscuit.powahmachinery.screen.FurnaceScreenHandler;
import org.jetbrains.annotations.Nullable;

public class PoweredFurnaceBlock extends AbstractFurnaceBlock {
    public PoweredFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                    (syncId, playerInventory, playerEntity) -> new FurnaceScreenHandler(syncId, playerInventory, new SimpleInventory(2), (FurnaceBlockEntity) world.getBlockEntity(pos)),
                    Text.translatable("container.powahmachinery.furnace")
            ));
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FurnaceBlockEntity(pos, state);
    }
}
