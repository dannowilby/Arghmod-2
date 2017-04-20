package wilby.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiManual extends GuiScreen 
{
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	
	public GuiManual(EntityPlayer player, ItemStack book)
    {
		
    }
	
	@Override
	public void keyTyped(char typedChar, int keyCode)
	{
		if(keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
		{
			this.mc.player.closeScreen();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void updateScreen()
    {
        super.updateScreen();
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int i = (this.width - 192) / 2;
        this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
}
