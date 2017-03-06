package wilby.argh.common.item;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase living)
	{
		return stack;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)25.0F, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }
	
	@Override
	public Set<String> getToolClasses(ItemStack stack)
	{
		return ImmutableSet.of("sword");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(hand));
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if(this.getEnergyStored(stack) < 64)
		{
			return true;
		}
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
		return 300;	
	}
	
	
}
