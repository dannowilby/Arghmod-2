package wilby.argh.common.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import wilby.api.ArghItemArmour;
import wilby.api.ArghTier;
import wilby.argh.Argh;
import wilby.argh.common.util.ArghUtil;

public class ItemJetpack extends ArghItemArmour
{
		
	public static final String jetpackName = "jetpack";
	public static final int durability = 17;
	public final static int[] reductionAmounts = {0, 0, 7, 0};
	public static final int enchantability = 8;
	public final static SoundEvent soundOnEquipt = SoundEvents.BLOCK_ANVIL_HIT;
	public static final float toughness = 1.0f;
	
	public static ArmorMaterial JETPACK = EnumHelper.addArmorMaterial(jetpackName, jetpackName, durability, reductionAmounts, enchantability, soundOnEquipt, toughness);
	
	public ArghTier at;
	
	public ItemJetpack(String name, ArghTier t, int maxEnergy) 
	{
		super(JETPACK, 2, EntityEquipmentSlot.CHEST, name, t);
		at = t;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		int t = this.getMaxEnergyStored(stack) - this.getEnergyStored(stack);
		this.setDamage(stack, t);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		if(ArghTier.TIER1.equals(at))
		{
			tooltip.add(TextFormatting.GOLD + at.getDisplayName());
		}
		if(ArghTier.TIER2.equals(at))
		{
			tooltip.add(TextFormatting.GRAY + at.getDisplayName());
		}
		if(ArghTier.TIER3.equals(at))
		{
			tooltip.add(TextFormatting.AQUA + at.getDisplayName());
		}
		
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
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot x, String type)
	{
	    return Argh.MODID + ":textures/armor/" + this.jetpackName + "_1.png";
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;	
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if(at.equals(ArghTier.TIER1))
		{
			if(this.getEnergyStored(stack) > 0)
			{
				player.jumpMovementFactor = .07f;
				player.capabilities.allowFlying = true;	
			}
			else
			{
				player.jumpMovementFactor = .02f;
				if(player.isCreative())
				{
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.setFlySpeed(.05f);
					player.capabilities.setPlayerWalkSpeed(.1f);
				}
			}
		}
		if(at.equals(ArghTier.TIER2))
		{
			if(this.getEnergyStored(stack) > 0)
			{
				player.jumpMovementFactor = .07f;
				player.capabilities.allowFlying = true;
				player.capabilities.setPlayerWalkSpeed(1.0f);
				player.capabilities.setFlySpeed(.1f);
			}
			else
			{
				player.jumpMovementFactor = .02f;
				if(player.isCreative())
				{
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.setFlySpeed(.05f);
					player.capabilities.setPlayerWalkSpeed(.1f);
				}
			}
		}
		if(at.equals(ArghTier.TIER3))
		{
			if(this.getEnergyStored(stack) > 0)
			{
				player.jumpMovementFactor = .07f;
				player.capabilities.allowFlying = true;
				player.capabilities.setPlayerWalkSpeed(1.5f);
				player.capabilities.setFlySpeed(.5f);
			}
			else
			{
				player.jumpMovementFactor = .02f;
				if(player.isCreative())
				{
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.setFlySpeed(.05f);
					player.capabilities.setPlayerWalkSpeed(.1f);
				}
			}
		}
		
		if(player.capabilities.isFlying)
		{
			
			this.extractEnergy(stack, 64, false);
		}
	}
	
	@Override
	public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		
		if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
			{
				player.setItemStackToSlot(EntityEquipmentSlot.CHEST, player.getHeldItem(hand));

				player.setHeldItem(hand, ItemStack.EMPTY);
			}
		
		return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(EnumHand.MAIN_HAND));
		
	}
}
