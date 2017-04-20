package wilby.api;

import net.minecraft.item.Item;
import wilby.argh.Reference;

public class ArghItem extends Item 
{
	String name;
	public ArghItem(String name)
	{
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Reference.arghTab);
	}
	
	public String getName()
	{
		return name;
	}
}