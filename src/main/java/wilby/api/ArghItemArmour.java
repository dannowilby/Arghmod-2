package wilby.api;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import wilby.argh.Reference;

public class ArghItemArmour extends ItemArmor implements IEnergyContainerItem
{
	int capacity, maxExtract, maxReceive;
	int energy = 0;
	String name;
	
	private ArghTier tier;
	
	public ArghItemArmour(ItemArmor.ArmorMaterial a, int render, EntityEquipmentSlot slot, String name, ArghTier t) 
	{
		super(a, render, slot);
		this.tier = t;
		this.capacity = tier.getMaxDamage();
		this.maxReceive = tier.getMaxDamage();
		this.maxExtract = tier.getMaxDamage();
		this.setMaxDamage(tier.getMaxDamage());
		this.name = name;
		this.setMaxStackSize(1);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Reference.arghTab);
	}
	
	public void setTier(ArghTier t)
	{
		this.tier = t;
	}
	
	public ArghTier getTier()
	{
		return this.tier;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public boolean isDamageable()
	{
		return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return true;
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		this.setEnergy(100000);
	}
	
	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot type, Entity entity)
	{
		return true;
	}
	
	public void setEnergy(int e)
	{
		energy = e;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (!container.hasTagCompound()) {
			container.setTagCompound(new NBTTagCompound());
		}
		int energy = container.getTagCompound().getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.getTagCompound().setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

		if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
			return 0;
		}
		int energy = container.getTagCompound().getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.getTagCompound().setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
			return 0;
		}
		return container.getTagCompound().getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return capacity;
	}


}
