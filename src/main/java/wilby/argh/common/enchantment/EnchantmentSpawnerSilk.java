package wilby.argh.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentSpawnerSilk extends Enchantment 
{
	private static EntityEquipmentSlot[] t = {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
	
	public EnchantmentSpawnerSilk() 
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, t);
		this.setRegistryName("silk");
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 1;
	}
	
	@Override
	public String getName() 
	{
		return "Lacero Ova";
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
