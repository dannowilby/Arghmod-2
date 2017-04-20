package wilby.argh.common.tileentity;

import java.util.ArrayList;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.common.util.ArghUtil;

public class TileEntitySolarPanel extends TileEntity implements IEnergyProvider, ITickable {

	protected EnergyStorage storage = new EnergyStorage(32000);
	protected int energyPerTick = 80;
	protected ArrayList<BlockPos> linkedMachines = new ArrayList<BlockPos>();
	
	@Override
	public int getEnergyStored(EnumFacing from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
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
	
	public boolean canSeeSky(BlockPos p)
	{
		
		for(int i = 1; i < (256-p.getY());i++)
		{
			if(!world.isAirBlock((new BlockPos(p.getX(), p.getY()+i, p.getZ()))))
			{
				return false;
			}
		}
		
		return true;
	}
	boolean sky;
	@Override
	public void update() 
	{
		if(this.world.getWorldTime() % 5 == 0)
		{
			sky = canSeeSky(pos);
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
		}
			
		checkForMachines();
		for(int l = 0; l < linkedMachines.size(); l++)
		{
		
			if(this.getEnergyStored(null) > 128)
			{
				if(IEnergyReceiver.class.isInstance(world.getTileEntity(linkedMachines.get(l))))
				{
					IEnergyReceiver ier = (IEnergyReceiver) world.getTileEntity(linkedMachines.get(l));
					EnumFacing a = ArghUtil.nrgRelative(linkedMachines.get(l), pos);
					if(((double)ier.getEnergyStored(a)/(double)ier.getMaxEnergyStored(a)) < (double)1.0 && ier.canConnectEnergy(a))
						ier.receiveEnergy(a, this.extractEnergy(null, 128, false), false);
				}
				if(IEnergyStorage.class.isInstance(world.getTileEntity(linkedMachines.get(l))))
				{
					IEnergyStorage ier = (IEnergyStorage) world.getTileEntity(linkedMachines.get(l));
					if(((double)ier.getEnergyStored()/(double)ier.getMaxEnergyStored()) < (double)1.0)
						ier.receiveEnergy(this.extractEnergy(null, 128, false), false);
				}
			}
		}
		
		if(world.isDaytime() && sky && !world.isRaining() && !world.isThundering())
			storage.modifyEnergyStored(80);
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
			if(IEnergyStorage.class.isInstance(world.getTileEntity(b)))
				linkedMachines.add(b);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		storage.readFromNBT(compound);
	}

	public double getSunAngle() 
	{
		return 0.0D;
	}
	
}
