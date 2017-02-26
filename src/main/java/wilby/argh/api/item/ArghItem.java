package wilby.argh.api.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;
import wilby.argh.Initialisation;

public class ArghItem extends Item 
{
	String name;
	public ArghItem(String name)
	{
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Initialisation.arghTab);
	}
	
	public String getName()
	{
		return name;
	}
}