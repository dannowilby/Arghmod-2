package wilby.argh.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import wilby.argh.common.gui.container.ContainerBox;
import wilby.argh.common.tileentity.TileEntityBox;

public class ArghGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiId()) {
			System.err.println("Invalid ID: expected " + getGuiId() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityBox) {
			TileEntityBox tileEntityInventoryBasic = (TileEntityBox) tileEntity;
			return new ContainerBox(player.inventory, tileEntityInventoryBasic);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiId()) {
			System.err.println("Invalid ID: expected " + getGuiId() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityBox) {
			TileEntityBox tileEntityInventoryBasic = (TileEntityBox) tileEntity;
			return new GuiBox(player.inventory, tileEntityInventoryBasic);
		}
		return null;
	}
	
	public static int getGuiId()
	{
		return 0;
	}
	
}
