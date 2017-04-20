package wilby.argh.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import wilby.api.ArghItem;

public class ItemArghFuel extends ArghItem 
{

	public ItemArghFuel(String name) 
	{
		super(name);
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List<String> toolTip, boolean advanced)
	{
		
		toolTip.add("Too explosive for standard uses");
		
	}
	
}
