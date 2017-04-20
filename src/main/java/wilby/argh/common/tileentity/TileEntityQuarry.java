package wilby.argh.common.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityQuarry extends TileEntity implements ITickable, IEnergyReceiver {
	int y = 1;
	int currentX = 5, minX = 5, maxX = -5, currentZ = 5, minZ = 5, maxZ = -5;
	
	int mineEnergy = 2048;
	protected EnergyStorage storage = new EnergyStorage(128000);
	
	boolean mining = false;
	boolean mined = false;
	
	@Override
	public void update() {
		if(storage.getEnergyStored() > mineEnergy && world.getWorldTime() % Math.ceil(((double)5/getModifier())) == 0 && !(world.isBlockIndirectlyGettingPowered(pos) > 0) && !mined)
		{
			
			dig(5, 5, false);
			mining = true;
		}
		if(storage.getEnergyStored() <= 0 || world.isBlockIndirectlyGettingPowered(pos) > 0 || mined)
		{
			mining = false;
		}
		if(world.getWorldTime() % 20 == 0)
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
	}
	
	public double getModifier()
	{
		if(this.getEnergyStored(EnumFacing.DOWN) != 0)
		{
			return (double)((double)this.getEnergyStored(EnumFacing.DOWN)/(double)this.getMaxEnergyStored(EnumFacing.DOWN));
		}
		else
		{
			return .01d;
		}
	}

	BlockPos a;
	
	public boolean isMining()
	{
		return mining;
	}
	
	public int[] getNextBlock()
	{
		return new int[]{currentX, y, currentZ};
	}
	
	public void dig(int rangeX, int rangeY, boolean simulate) {

			if (currentX <= minX && currentX >= maxX) {

				if (currentZ <= minZ && currentZ >= maxZ) {
					a = new BlockPos(pos.getX() - currentX, pos.getY() - y, pos.getZ() - currentZ);
					if(a.getY() <= 1)
					{
						mined = true;
					}
					Block b = world.getBlockState(a).getBlock();
					if (b != Blocks.BEDROCK && !simulate) {
						world.setBlockToAir(a);
						addToInventory(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), b);
					}
					if(!simulate)
						storage.modifyEnergyStored(-mineEnergy);
					currentZ--;
					return;
				}

				currentX--;
				storage.modifyEnergyStored(-mineEnergy);
				currentZ = minZ;

				return;
			}
			currentX = minX;
			y++;
		
	}

	public Block processBlock(Block b)
	{
		if (b != null || b != Blocks.AIR) {
			if (b == Blocks.STONE) {
				b = Blocks.COBBLESTONE;
			}
			if (b == Blocks.GRASS_PATH || b == Blocks.GRASS) {
				b = Blocks.DIRT;
			} 
			if (b == Blocks.WEB || b == Blocks.TALLGRASS || b == Blocks.DOUBLE_PLANT || b == Blocks.CHORUS_FLOWER || b == Blocks.YELLOW_FLOWER || b == Blocks.RED_FLOWER)
			{
				b = Blocks.AIR;
			}
		}
		return b;
	}
	
	public void addToInventory(BlockPos inventory, Block b) {
			
			b = processBlock(b);
		
			if (world.getTileEntity(inventory) instanceof TileEntityBox) {
				TileEntityBox tec = (TileEntityBox) world.getTileEntity(inventory);

				for (int i = 0; i < tec.getSizeInventory(); i++) {
					if (ItemStack.areItemStacksEqual(new ItemStack(tec.getStackInSlot(i).getItem()),
							new ItemStack(Item.getItemFromBlock(b))) && tec.getStackInSlot(i).getCount() < 64) {
						tec.setInventorySlotContents(i, new ItemStack(b, tec.getStackInSlot(i).getCount() + 1));
						return;
					}
					if (tec.getStackInSlot(i).isEmpty() || tec.getStackInSlot(i) == null) {
						tec.setInventorySlotContents(i, new ItemStack(b));
						return;
					}
				}
			}
			if (world.getTileEntity(inventory) instanceof TileEntityChest) {
				TileEntityChest tec = (TileEntityChest) world.getTileEntity(inventory);

				for (int i = 0; i < tec.getSizeInventory(); i++) {
					if (ItemStack.areItemStacksEqual(new ItemStack(tec.getStackInSlot(i).getItem()),
							new ItemStack(Item.getItemFromBlock(b))) && tec.getStackInSlot(i).getCount() < 64) {
						tec.setInventorySlotContents(i, new ItemStack(b, tec.getStackInSlot(i).getCount() + 1));
						return;
					}
					if (tec.getStackInSlot(i).isEmpty() || tec.getStackInSlot(i) == null) {
						tec.setInventorySlotContents(i, new ItemStack(b));
						return;
					}
				}
			}
			EntityItem ei = new EntityItem(world, pos.getX(), pos.getY() + 2, pos.getZ(),
					new ItemStack(Item.getItemFromBlock(b)));
			world.spawnEntity(ei);
			return;
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setIntArray("values", new int[] { currentX, y, currentZ });
		storage.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int[] g = compound.getIntArray("values");
		if(g == null)
		{
			g = new int[]{0,0,0};
		}
		currentX = g[0];
		y = g[1];
		currentZ = g[2];
		storage.readFromNBT(compound);
	}

	@Override
	public int getEnergyStored(EnumFacing from) 
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) 
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) 
	{
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		
		return storage.receiveEnergy(maxReceive, simulate);
	}

}
