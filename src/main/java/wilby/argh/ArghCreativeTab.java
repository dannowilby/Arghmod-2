package wilby.argh;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArghCreativeTab extends CreativeTabs
{
	
	String name;
	
	public String getName()
	{
		return name;
	}
	
	public ArghCreativeTab(String name)
	{
		super(name);
		this.name = name;
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(Item.getItemFromBlock(Reference.solar));
	}
	
}
