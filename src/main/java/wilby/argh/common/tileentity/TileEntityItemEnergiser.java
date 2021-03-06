package wilby.argh.common.tileentity;

import java.util.Arrays;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import wilby.argh.Reference;

public class TileEntityItemEnergiser extends TileEntity implements IEnergyReceiver, IEnergyProvider, ITickable, IInventory
{
	
	public EnergyStorage es = new EnergyStorage(64000);
	
	public TileEntityItemEnergiser() 
	{
		itemStacks = new ItemStack[NUMBER_OF_SLOTS];
		clear();
	}

	private final int NUMBER_OF_SLOTS = 2;
	private ItemStack[] itemStacks;
	
	@Override
	public void update()
	{
		if(this.world.getWorldTime() % 5 == 0)
			this.world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), world.getBlockState(pos), world.getBlockState(pos), 3);
		
		if(!world.isRemote)
		{
			if(this.getStackInSlot(1).isEmpty())
			{
				if(IEnergyContainerItem.class.isInstance(this.getStackInSlot(0).getItem()))
				{
					IEnergyContainerItem ieci = (IEnergyContainerItem) this.getStackInSlot(0).getItem();
					if(this.getEnergyStored(null) >= 1280)
					{
					int i = this.extractEnergy(null, 1280, false);
					int q = ieci.receiveEnergy(this.getStackInSlot(0), 1280, false);
					}
					if(ieci.getEnergyStored(this.getStackInSlot(0)) == ieci.getMaxEnergyStored(this.getStackInSlot(0)))
					{
						this.itemStacks[1] = this.getStackInSlot(0);
						this.itemStacks[0] = ItemStack.EMPTY;
					}
				}
			
			}
			if(this.getStackInSlot(0).getItem().equals(Items.DIAMOND) && (this.getStackInSlot(1).isEmpty() || this.getStackInSlot(1) == new ItemStack(Reference.ediamond)))
			{
				if(this.getEnergyStored(null) == this.getMaxEnergyStored(null))
				{
					int i = this.extractEnergy(null, this.getMaxEnergyStored(null), false);
					this.itemStacks[0].setCount(this.itemStacks[0].getCount()-1);
					this.itemStacks[1] = new ItemStack(Reference.ediamond, this.itemStacks[1].getCount()+1);
				}
			}
		}
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		return es.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		return es.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
	{
		return es.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	// returns true if all of the slots in the inventory are empty
	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : itemStacks) {
			if (!itemstack.isEmpty()) { // isEmpty()
				return false;
			}
		}

		return true;
	}

	// Gets the stack in the given slot
	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return itemStacks[slotIndex];
	}

	/**
	 * Removes some of the units from itemstack in the given slot, and returns
	 * as a separate itemstack
	 * 
	 * @param slotIndex
	 *            the slot number to remove the items from
	 * @param count
	 *            the number of units to remove
	 * @return a new itemstack containing the units removed from the slot
	 */
	@Override
	public ItemStack decrStackSize(int slotIndex, int count) {
		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
		if (itemStackInSlot.isEmpty())
			return ItemStack.EMPTY; // isEmpt(); EMPTY_ITEM

		ItemStack itemStackRemoved;
		if (itemStackInSlot.getCount() <= count) { // getStackSize()
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.getCount() == 0) { // getStackSize
				setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	// overwrites the stack in the given slotIndex with the given stack
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		itemStacks[slotIndex] = itemstack;
		if (itemstack.isEmpty() && itemstack.getMaxStackSize() > getInventoryStackLimit()) { // isEmpty();
																								// getStackSize()
			itemstack.setCount(getInventoryStackLimit()); // setStackSize
		}
		markDirty();
	}

	// This is the maximum number if items allowed in each slot
	// This only affects things such as hoppers trying to insert items you need
	// to use the container to enforce this for players
	// inserting items via the gui
	@Override
	public int getInventoryStackLimit() {
		return 64;
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
	
	// Return true if the given player is able to use this block. In this case
	// it checks that
	// 1) the world tileentity hasn't been replaced in the meantime, and
	// 2) the player isn't too far away from the centre of the block
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(this.pos) != this)
			return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET,
				pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}

	// Return true if the given stack is allowed to go in the given slot. In
	// this case, we can insert anything.
	// This only affects things such as hoppers trying to insert items you need
	// to use the container to enforce this for players
	// inserting items via the gui
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
		return true;
	}

	// This is where you save any data that you don't want to lose when the tile
	// entity unloads
	// In this case, it saves the itemstacks stored in the container
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {
		super.writeToNBT(parentNBTTagCompound); // The super call is required to
												// save and load the
												// tileEntity's location

		// to use an analogy with Java, this code generates an array of hashmaps
		// The itemStack in each slot is converted to an NBTTagCompound, which
		// is effectively a hashmap of key->value pairs such
		// as slot=1, id=2353, count=1, etc
		// Each of these NBTTagCompound are then inserted into NBTTagList, which
		// is similar to an array.
		NBTTagList dataForAllSlots = new NBTTagList();
		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (!this.itemStacks[i].isEmpty()) { // isEmpty()
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		es.writeToNBT(parentNBTTagCompound);
		// the array of hashmaps is then inserted into the parent hashmap for
		// the container
		parentNBTTagCompound.setTag("Items", dataForAllSlots);
		// return the NBT Tag Compound
		return parentNBTTagCompound;
	}

	// This is where you load the data that you saved in writeToNBT
	@Override
	public void readFromNBT(NBTTagCompound parentNBTTagCompound) {
		super.readFromNBT(parentNBTTagCompound); // The super call is required
		es.readFromNBT(parentNBTTagCompound);											// to save and load the
													// tiles location
		final byte NBT_TYPE_COMPOUND = 10; // See NBTBase.createNewByType() for
											// a listing
		NBTTagList dataForAllSlots = parentNBTTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

		Arrays.fill(itemStacks, ItemStack.EMPTY); // set all slots to empty
													// EMPTY_ITEM
		for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			int slotIndex = dataForOneSlot.getByte("Slot") & 255;

			if (slotIndex >= 0 && slotIndex < this.itemStacks.length) {
				this.itemStacks[slotIndex] = new ItemStack(dataForOneSlot);
			}
		}
	}

	// set all slots to empty
	@Override
	public void clear() {
		Arrays.fill(itemStacks, ItemStack.EMPTY); // empty item
	}

	// will add a key for this container to the lang file so we can name it in
	// the GUI
	@Override
	public String getName() {
		return "container.itemenerg.name";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	// standard code to look up what the human-readable name is
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	// -----------------------------------------------------------------------------------------------------------
	// The following methods are not needed for this example but are part of
	// IInventory so they must be implemented

	/**
	 * This method removes the entire contents of the given slot and returns it.
	 * Used by containers such as crafting tables which return any items in
	 * their slots when you close the GUI
	 * 
	 * @param slotIndex
	 * @return
	 */
	@Override
	public ItemStack removeStackFromSlot(int slotIndex) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (!itemStack.isEmpty())
			setInventorySlotContents(slotIndex, ItemStack.EMPTY); // isEmpty(),
																	// EMPTY_ITEM
		return itemStack;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) 
	{
		return es.extractEnergy(maxExtract, simulate);
	}

}
