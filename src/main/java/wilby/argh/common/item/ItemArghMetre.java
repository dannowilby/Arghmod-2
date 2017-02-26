package wilby.argh.common.item;

import java.util.List;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wilby.argh.api.item.ArghItem;
import wilby.argh.common.transport.TileEntityExportNode;
import wilby.argh.common.transport.TileEntityImportNode;

public class ItemArghMetre extends ArghItem 
{

	BlockPos link = null;
	
	public ItemArghMetre(String name) 
	{
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.AQUA + "Right click on a machine to find out how much power it has");
			if(link != null)
				tooltip.add(link.getX() + " " + link.getY() + " " + link.getZ());
		}
		else
			tooltip.add(TextFormatting.DARK_GRAY + "Hold Shift for more information");
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!player.isSneaking())
		{
			if(IEnergyHandler.class.isInstance(world.getTileEntity(pos)))
			{
				
				IEnergyHandler te = (IEnergyHandler)(world.getTileEntity(pos));
				float p = ((float)te.getEnergyStored(null)/(float)te.getMaxEnergyStored(null))*(float)100;
				System.out.println(te.getEnergyStored(null));
				if(!world.isRemote)
				{
					player.sendMessage(new TextComponentString(p + "% Full"));
					player.sendMessage(new TextComponentString(te.getEnergyStored(null) + "/" + te.getMaxEnergyStored(null)));
				}
				return EnumActionResult.SUCCESS;
			}
			if(IEnergyStorage.class.isInstance(world.getTileEntity(pos)))
			{
				
				IEnergyStorage te = (IEnergyStorage)(world.getTileEntity(pos));
				float p = ((float)te.getEnergyStored()/(float)te.getMaxEnergyStored())*(float)100;
				System.out.println(te.getEnergyStored());
				if(!world.isRemote)
				{
					player.sendMessage(new TextComponentString(p + "% Full"));
					player.sendMessage(new TextComponentString(te.getEnergyStored() + "/" + te.getMaxEnergyStored()));
				}
				return EnumActionResult.SUCCESS;
			}
		}
		if(player.isSneaking())
		{
			if(world.getTileEntity(pos) instanceof TileEntityImportNode)
			{
				link = pos;
				TileEntityImportNode te = (TileEntityImportNode) world.getTileEntity(pos);
				System.out.println(te.getEnergyStored());
				return EnumActionResult.SUCCESS;
			}
			if(world.getTileEntity(pos) instanceof TileEntityExportNode && link != null)
			{
				TileEntityExportNode te = (TileEntityExportNode)world.getTileEntity(pos);
				te.setNode(link);
				return EnumActionResult.SUCCESS;
			}
			else
			{
				System.out.println(link);
			}
		}
		return EnumActionResult.FAIL;
		
	}

}
