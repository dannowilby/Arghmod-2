package wilby.argh.common.tileentity;

import java.util.ArrayList;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import wilby.api.EnumNodeType;
import wilby.argh.common.util.ArghUtil;

public class TileEntityImportNode extends TileEntity implements ITickable, IEnergyProvider, IEnergyReceiver
{	
	
	public EnergyStorage storage = new EnergyStorage(24000);
	public EnumNodeType type = EnumNodeType.INVALID;
	protected ArrayList<BlockPos> linkedMachines = new ArrayList<BlockPos>();
	
	@Override
	public void update()
	{
		if(type.equals(EnumNodeType.ENERGY))
		{
			checkForMachines();
			for(int l = 0; l < linkedMachines.size(); l++)
			{
					if(IEnergyReceiver.class.isInstance(world.getTileEntity(linkedMachines.get(l))))
					{
						IEnergyReceiver ier = (IEnergyReceiver) world.getTileEntity(linkedMachines.get(l));
						EnumFacing a = ArghUtil.nrgRelative(linkedMachines.get(l), pos);
						if(((double)ier.getEnergyStored(null)/(double)ier.getMaxEnergyStored(null)) < (double)1.0 && ier.canConnectEnergy(a))
							ier.receiveEnergy(a, this.extractEnergy(null, storage.getEnergyStored(), false), false);
					}
				
			}
		}
		if(world.getWorldTime() % 20 == 0)
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
	}
	
	private void checkForMachines() 
	{
		linkedMachines.clear();
		BlockPos[] list = new BlockPos[]{
		new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()),
		new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()),
		new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()),
		new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()),
		new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1),
		new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)
		};
		
		for(BlockPos b : list)
		{
			if(IEnergyReceiver.class.isInstance(world.getTileEntity(b)))
				linkedMachines.add(b);
			
		}
	}
	
	public void setNodeType(EnumNodeType type)
	{
		this.type = type;
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
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		type = type.getTypeFromId(nbt.getInteger("type"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("type", type.getId());
		return nbt;
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
	public boolean canConnectEnergy(EnumFacing from) 
	{
		return true;
	}

}
