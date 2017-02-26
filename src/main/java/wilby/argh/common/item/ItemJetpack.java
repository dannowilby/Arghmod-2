package wilby.argh.common.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import wilby.argh.Argh;
import wilby.argh.api.item.ArghItemArmour;

public class ItemJetpack extends ArghItemArmour
{
		
	public static final String jetpackName = "jetpack";
	public static final int durability = 17;
	public final static int[] reductionAmounts = {0, 0, 7, 0};
	public static final int enchantability = 8;
	public final static SoundEvent soundOnEquipt = SoundEvents.BLOCK_ANVIL_HIT;
	public static final float toughness = 1.0f;
	
	public static final int mxenergy = 16334;
	
	public static ArmorMaterial JETPACK = EnumHelper.addArmorMaterial(jetpackName, jetpackName, durability, reductionAmounts, enchantability, soundOnEquipt, toughness);
	
	public ItemJetpack(String name) 
	{
		super(name, mxenergy);
		this.setMaxDamage(mxenergy);
		this.setContainerItem(this);
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
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot type, Entity entity)
	{
		if(type == EntityEquipmentSlot.CHEST)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		
		if(this.getEnergyStored(stack) > 0)
		{
			player.jumpMovementFactor = .07f;
			player.capabilities.allowFlying = true;
		}
		else
		{
			player.jumpMovementFactor = .02f;
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}
		
		if(!player.onGround && world.getWorldTime() % 60 == 0)
		{
			
			this.extractEnergy(stack, 64, false);
		}
	}
	
	@Override
	public ActionResult onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if(player.isSneaking())
		{
			System.out.println(this.getEnergyStored(player.getHeldItem(hand)));
			if(player.getHeldItem(hand).getDisplayName().equalsIgnoreCase("jetpack"))
			{
				this.receiveEnergy(player.getHeldItem(hand), 128, false);
	
				return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(EnumHand.MAIN_HAND));
			}
		}
		else
		{
			if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
			{
				player.setItemStackToSlot(EntityEquipmentSlot.CHEST, player.getHeldItem(hand));

				player.setHeldItem(hand, ItemStack.EMPTY);
			}
		}
		return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(EnumHand.MAIN_HAND));
		
	}
	
}
