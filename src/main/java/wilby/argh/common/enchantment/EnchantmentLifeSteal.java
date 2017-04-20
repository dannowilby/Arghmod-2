package wilby.argh.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentLifeSteal extends Enchantment 
{

	private static EntityEquipmentSlot[] t = {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
	
	public EnchantmentLifeSteal() 
	{
		super(Enchantment.Rarity.COMMON, EnumEnchantmentType.WEAPON, t);
		this.setRegistryName("lifeSteal");
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 4;
	}
	
	@Override
	public String getName() 
	{
		return "Life Steal";
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
