package wilby.argh.common.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import wilby.argh.common.block.BlockOreProcessorController;

public class TileEntityOreRefinery extends TileEntity implements IEnergyReceiver, IInventory, IFluidTank, ITickable {

	public EnergyStorage es = new EnergyStorage(128000);
	public FluidTank ft = new FluidTank(16000);
	
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
	public FluidStack getFluid() 
	{
		return ft.getFluid();
	}

	@Override
	public int getFluidAmount() 
	{
		return ft.getFluidAmount();
	}

	@Override
	public int getCapacity() 
	{
		return ft.getCapacity();
	}

	@Override
	public FluidTankInfo getInfo() 
	{
		return ft.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) 
	{
		return ft.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) 
	{
		return ft.drain(maxDrain, doDrain);
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) 
	{
		return es.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() 
	{
		if(world.getWorldTime() % 20 == 0)
			formMultiblock();
	}
	
	public boolean formMultiblock() 
	{
		if(world.getBlockState(pos).getBlock() instanceof BlockOreProcessorController)
		{
			System.out.println(world.getBiome(pos).getBiomeName());
			return true;
		}
		
		return false;
	}

}
