package wilby.argh.common.tileentity;

import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityItemEnergiser extends TileEntity implements IEnergyReceiver, ITickable, IInventory
{
	

	@Override
	public void update()
	{
		
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInventoryStackLimit()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}

}
