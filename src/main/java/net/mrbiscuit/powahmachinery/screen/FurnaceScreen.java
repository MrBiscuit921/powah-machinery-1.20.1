package net.mrbiscuit.powahmachinery.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FurnaceScreen extends HandledScreen<FurnaceScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("powahmachinery", "powered_furnace_gui");

    public FurnaceScreen(FurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // Set the shader texture using RenderSystem
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        // Draw the background texture
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Draw energy bar
        int energyBarHeight = (int) ((handler.getEnergy() / (float) handler.getMaxEnergy()) * 50); // 50 px bar height
        context.drawTexture(TEXTURE, x + 8, y + 18 + (50 - energyBarHeight), 176, 0, 16, energyBarHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);  // Pass DrawContext here
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);  // Pass DrawContext here as well
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        // Draw title centered with shadow
        context.drawTextWithShadow(textRenderer, title, (backgroundWidth - textRenderer.getWidth(title)) / 2, 6, 4210752);

        // Draw player inventory title with shadow
        context.drawTextWithShadow(textRenderer, playerInventoryTitle, 8, backgroundHeight - 94, 4210752);
    }
}
