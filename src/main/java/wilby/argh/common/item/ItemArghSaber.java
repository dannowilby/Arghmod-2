package wilby.argh.common.item;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wilby.argh.api.item.ArghItemTool;

public class ItemArghSaber extends ArghItemTool 
{
	
	static Set<Block> ef = Sets.newHashSet(new Block[]{Blocks.WEB});
	
	public ItemArghSaber(String name, int maxEnergy)
	{
		super(name, maxEnergy, Item.ToolMaterial.valueOf("saber").getEfficiencyOnProperMaterial(), Item.ToolMaterial.valueOf("saber").getDamageVsEntity(), Item.ToolMaterial.valueOf("saber"), ef);
		this.setContainerItem(this);
		this.setMaxDamage(maxEnergy);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if(this.getEnergyStored(stack) < 64)
			return true;
		
		System.out.println("argh");
		this.extractEnergy(stack, 64, false);
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		this.setDamage(stack, this.getMaxEnergyStored(stack) - this.getEnergyStored(stack));
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.AQUA + "" + this.getEnergyStored(stack) + "/" + this.getMaxEnergyStored(stack) + " RF");
		}
		else
		{
			tooltip.add(TextFormatting.DARK_GRAY + "Hold down shift to view how much Rf is stored");
		}
		
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;	
	}
	
	@Override
	public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking())
		{
			
				this.receiveEnergy(player.getHeldItem(hand), 128, false);
	
				return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(EnumHand.MAIN_HAND));
			
		}

		return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(EnumHand.MAIN_HAND));
		
	}
	
}
