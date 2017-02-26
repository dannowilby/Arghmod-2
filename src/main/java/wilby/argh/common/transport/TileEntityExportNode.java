package wilby.argh.common.transport;

import java.util.ArrayList;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

public class TileEntityExportNode extends TileEntity implements ITickable, IEnergyStorage
{
	
	public EnergyStorage storage = new EnergyStorage(24000);
	public EnumNodeType type = EnumNodeType.INVALID;
	ArrayList<BlockPos> importNodes = new ArrayList<BlockPos>();
	
	@Override
	public void update()
	{
		if(type.equals(EnumNodeType.ENERGY))
		{
			importNodes.forEach((node) -> {
				if(world.getTileEntity(node) instanceof TileEntityImportNode)
				{
					TileEntityImportNode te = (TileEntityImportNode) world.getTileEntity(node);
					int i = this.extractEnergy(te.receiveEnergy(storage.getMaxExtract(), false), false);
					
				}
			});
		}
		if(world.getWorldTime() % 20 == 0)
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
	}
	
	public void setNodeType(EnumNodeType type)
	{
		this.type = type;
	}
	
	public double getDistance(BlockPos bp1, BlockPos bp2)
	{
		double d = Math.sqrt(((Math.abs(bp2.getX()-bp1.getX()))^2) + ((Math.abs(bp2.getY()-bp1.getY()))^2) + ((Math.abs(bp2.getZ()-bp1.getZ()))^2));
		return Math.abs(d);
	}
	
	public void setNode(BlockPos bp)
	{
		if(getDistance(pos, bp) <= 10)
		{
			System.out.println("Setting Link");
			importNodes.add(bp);
		}
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
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < importNodes.size(); i++)
		{
			BlockPos p = importNodes.get(i);
			
			if(p != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setIntArray("bp" + i, new int[]{p.getX(),p.getY(),p.getZ()});
				list.appendTag(tag);
			}
			
		}
		
		nbt.setTag("nodes", list);
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("type", type.getId());
		
		NBTTagList list = nbt.getTagList("bp", Constants.NBT.TAG_COMPOUND);
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			 NBTTagCompound tag = list.getCompoundTagAt(i);
			 int[] a = tag.getIntArray("bp" + i);
			 importNodes.add(new BlockPos(a[0], a[1], a[2]));
		}
		
		return nbt;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored()
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored()
	{
		return storage.getMaxEnergyStored();
	}
	
}
