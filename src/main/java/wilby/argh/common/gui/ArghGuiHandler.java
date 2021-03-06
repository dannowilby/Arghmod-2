package wilby.argh.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import wilby.argh.common.gui.container.ContainerBox;
import wilby.argh.common.gui.container.ContainerItemEnergiser;
import wilby.argh.common.tileentity.TileEntityBox;
import wilby.argh.common.tileentity.TileEntityItemEnergiser;
import wilby.manual.GuiManual;

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
		if (tileEntity instanceof TileEntityItemEnergiser) {
			TileEntityItemEnergiser tileEntityInventoryBasic = (TileEntityItemEnergiser) tileEntity;
			return new ContainerItemEnergiser(player.inventory, tileEntityInventoryBasic);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiId()) {
			if(ID != 69)
			{
				System.err.println("Invalid ID: expected " + getGuiId() + ", received " + ID);
			}
		}
		
		if(ID == 69)
		{
			return new GuiManual(player, player.getHeldItemMainhand());
		}
		
		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityBox) {
			TileEntityBox tileEntityInventoryBasic = (TileEntityBox) tileEntity;
			return new GuiBox(player.inventory, tileEntityInventoryBasic);
		}
		if (tileEntity instanceof TileEntityItemEnergiser) {
			TileEntityItemEnergiser tileEntityInventoryBasic = (TileEntityItemEnergiser) tileEntity;
			return new GuiItemEnergiser(player.inventory, tileEntityInventoryBasic);
		}
		return null;
	}
	
	public static int getGuiId()
	{
		return 0;
	}
	
}
