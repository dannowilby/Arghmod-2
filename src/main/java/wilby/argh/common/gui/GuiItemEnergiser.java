package wilby.argh.common.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import wilby.argh.common.gui.container.ContainerItemEnergiser;
import wilby.argh.common.tileentity.TileEntityItemEnergiser;

public class GuiItemEnergiser extends GuiContainer{

	// This is the resource location for the background image for the GUI
		private static final ResourceLocation texture = new ResourceLocation("argh", "textures/gui/itemenerg.png");
		private TileEntityItemEnergiser tileEntityInventoryBasic;

		public GuiItemEnergiser(InventoryPlayer invPlayer, TileEntityItemEnergiser tile) {
			super(new ContainerItemEnergiser(invPlayer, tile));
			tileEntityInventoryBasic = tile;
			// Set the width and height of the gui.  Should match the size of the texture!
			xSize = 176;
			ySize = 132;
		}

		// draw the background for the GUI - rendered first
		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
			// Bind the image texture of our custom container
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			// Draw the image
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}

		// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
		// renders relative to the top left corner of the background
		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			final int LABEL_XPOS = 5;
			final int LABEL_YPOS = 5;
			fontRendererObj.drawString(tileEntityInventoryBasic.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
			fontRendererObj.drawString(tileEntityInventoryBasic.getEnergyStored(null) +"/" + tileEntityInventoryBasic.getMaxEnergyStored(null), 103, 25, Color.darkGray.getRGB());
		}

}
