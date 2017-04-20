package wilby.manual;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wilby.api.ArghItem;
import wilby.argh.Argh;

public class ItemManual extends ArghItem 
{

	public ItemManual(String name) 
	{
		super(name);
		this.setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		tooltip.add(TextFormatting.AQUA + "Written by: Daniel");
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        playerIn.openGui(Argh.argh, 69, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
        
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }

}
