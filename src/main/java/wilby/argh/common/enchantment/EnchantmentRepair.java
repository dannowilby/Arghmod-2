package wilby.argh.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.*;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentRepair extends Enchantment
{
	private static EntityEquipmentSlot[] t = {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};

	public EnchantmentRepair() 
	{
		super(Enchantment.Rarity.COMMON, EnumEnchantmentType.BREAKABLE, t);
		this.setRegistryName("repair");
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 2;
	}
	
	@Override
	public String getName() 
	{
		return "Repair";
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public boolean isAllowedOnBooks() 
	{
		return true;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 2 + 10 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

}
