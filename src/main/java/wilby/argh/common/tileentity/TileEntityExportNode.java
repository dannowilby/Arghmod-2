package wilby.argh.common.tileentity;

import java.util.ArrayList;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import wilby.api.EnumNodeType;

public class TileEntityExportNode extends TileEntity implements ITickable, IEnergyReceiver, IEnergyProvider
{
	
	public EnergyStorage storage = new EnergyStorage(24000);
	public EnumNodeType type = EnumNodeType.INVALID;
	BlockPos node = new BlockPos(0,0,0);
	
	@Override
	public void update()
	{
		if(type.equals(EnumNodeType.ENERGY))
		{
				if(world.getTileEntity(node) instanceof TileEntityImportNode)
				{
					TileEntityImportNode te = (TileEntityImportNode) world.getTileEntity(node);
					if(((double)te.getEnergyStored(null)/(double)te.getMaxEnergyStored(null)) < (double)1.0)
						this.extractEnergy(null, te.receiveEnergy(null, storage.getEnergyStored(), false), false);
					
				}
		
		}
		if(world.getWorldTime() % 20 == 0)
		{
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
		}
	}
	
	public void setNodeType(EnumNodeType type)
	{
		this.type = type;
	}
	
	public void setNode(BlockPos bp)
	{
			System.out.println("Setting Link");
			node = bp;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new SPacketUpdateTileEntity(this.pos, 3, nbttagcompound);
	}
	@Override
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound nbt = super.writeToNBT(new NBTTagCompound());
		writeToNBT(nbt);
		return nbt;
	}
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		type = type.getTypeFromId(nbt.getInteger("type"));
		int[] a = nbt.getIntArray("bp");
		node = new BlockPos(a[0], a[1], a[2]);
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("type", type.getId());
		
		nbt.setIntArray("bp", new int[]{node.getX(), node.getY(), node.getZ()});
		
		return nbt;
	}
	
	@Override
	public int receiveEnergy(EnumFacing x, int maxReceive, boolean simulate)
	{
			return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(EnumFacing x, int maxExtract, boolean simulate)
	{
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing x)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing x)
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) 
	{
		return true;
	}
	
}
