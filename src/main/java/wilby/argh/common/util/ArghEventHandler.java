package wilby.argh.common.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import wilby.argh.Reference;

public class ArghEventHandler 
{
	
	@SubscribeEvent
	public void BreakEvent(TickEvent.PlayerTickEvent be)
	{
		
		if(be.player.world.getWorldTime() % 20 == 0)
		{
			
			if(!be.player.isCreative())
			{
				
				if(!be.player.inventory.armorItemInSlot(2).getUnlocalizedName().substring(5).equalsIgnoreCase(Reference.resonantJetpack.getName()) || !be.player.inventory.armorItemInSlot(2).getUnlocalizedName().substring(5).equalsIgnoreCase(Reference.enforcedJetpack.getName()) || !be.player.inventory.armorItemInSlot(2).getUnlocalizedName().substring(5).equalsIgnoreCase(Reference.hardenedJetpack.getName()))
				
				be.player.capabilities.allowFlying = false;
				be.player.capabilities.isFlying = false;
				be.player.jumpMovementFactor = .02f;
			}
		}
		
		if(be.player.world.getTotalWorldTime() % 200 == 0)
		{
			ItemStack item = be.player.inventory.getCurrentItem();
			
			if(EnchantmentHelper.getEnchantments(item).get(Reference.repair) != null)		
			{
				if(EnchantmentHelper.getEnchantments(item).get(Reference.repair) == 1)		
				{
					item.getItem().setDamage(item, item.getItemDamage() - 1);	
				}
					
				if(EnchantmentHelper.getEnchantments(item).get(Reference.repair) == 2)		
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
			if(EnchantmentHelper.getEnchantments(item).get(Reference.silk) != null)		
			{
				
				if(be.getWorld().getTileEntity(be.getPos()) instanceof TileEntityMobSpawner)
				{
					TileEntityMobSpawner tes = (TileEntityMobSpawner) be.getWorld().getTileEntity(be.getPos());
					NBTTagCompound nbt = new NBTTagCompound();
				
					NBTTagCompound b = tes.writeToNBT(nbt);
					
					ItemStack a = new ItemStack(Reference.mob);
					
					a.setTagCompound(b);
					
					EntityItem i = new EntityItem(be.getWorld(), be.getPos().getX(), be.getPos().getY(), be.getPos().getZ(), a);
					be.getWorld().spawnEntity(i);
				}
				 
			}
		
	}
	
	@SubscribeEvent
	public void steal(LivingHurtEvent e)
	{
		if(e.getSource().getEntity() instanceof EntityPlayer)
		{
			
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			
			if(EnchantmentHelper.getEnchantments(player.getHeldItem(player.getActiveHand())).get(Reference.steal) != null)
			{
				int i = EnchantmentHelper.getEnchantments(player.getHeldItem(player.getActiveHand())).get(Reference.steal);
				
				switch(i)
				{
					case 1:
						player.heal(1);
					case 2:
						player.heal(2);
					case 3:
						player.heal(3);
					case 4:
						player.heal(4);
					default:
				}
			}
		}
	}
	
}
