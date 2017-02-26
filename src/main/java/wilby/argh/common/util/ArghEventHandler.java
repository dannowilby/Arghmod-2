package wilby.argh.common.util;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import wilby.argh.common.ArghItems;
import wilby.argh.common.enchantment.ArghEnchantments;

public class ArghEventHandler 
{
	
	@SubscribeEvent
	public void BreakEvent(TickEvent.PlayerTickEvent be)
	{
		
		if(be.player.world.getWorldTime() % 20 == 0)
		{
			
			if(!be.player.isCreative() && !be.player.inventory.armorItemInSlot(2).getDisplayName().equalsIgnoreCase("jetpack"))
			{
				be.player.capabilities.allowFlying = false;
				be.player.capabilities.isFlying = false;
				be.player.jumpMovementFactor = .02f;
			}
		}
		
		if(be.player.world.getTotalWorldTime() % 200 == 0)
		{
			ItemStack item = be.player.inventory.getCurrentItem();
			
			if(EnchantmentHelper.getEnchantments(item).get(ArghEnchantments.repair) != null)		
			{
				if(EnchantmentHelper.getEnchantments(item).get(ArghEnchantments.repair) == 1)		
				{
					item.getItem().setDamage(item, item.getItemDamage() - 1);	
				}
					
				if(EnchantmentHelper.getEnchantments(item).get(ArghEnchantments.repair) == 2)		
				{
					item.getItem().setDamage(item, item.getItemDamage() - 2);
				}
			}
			
		}
	}
	
	@SubscribeEvent
	public void Break(BlockEvent.BreakEvent be)
	{
		ItemStack item = be.getPlayer().inventory.getCurrentItem();
		
		if(EnchantmentHelper.getEnchantments(item).get(ArghEnchantments.silk) != null)		
		{
			if(Block.isEqualTo(be.getState().getBlock(), Blocks.MOB_SPAWNER))
			{
				EntityItem ei = new EntityItem(be.getWorld(), be.getPos().getX(), be.getPos().getY(), be.getPos().getZ(), new ItemStack(Item.getItemFromBlock(Blocks.MOB_SPAWNER)));
				be.getWorld().spawnEntity(ei);
			}
		}
	}
	
}
