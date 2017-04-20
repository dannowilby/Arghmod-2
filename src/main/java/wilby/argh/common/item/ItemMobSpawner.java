package wilby.argh.common.item;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import wilby.api.ArghItem;

public class ItemMobSpawner extends ArghItem
{
	
	NBTTagCompound l = null;
	
	public ItemMobSpawner(String name) 
	{
		super(name);
		this.setContainerItem(this);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{

		if(stack.getTagCompound() != null)
		{
			WeightedSpawnerEntity wse = new WeightedSpawnerEntity(1, stack.getTagCompound().getCompoundTag("SpawnData"));
			String[] h = wse.getNbt().getString("id").split(":");
			
			tooltip.add(TextFormatting.AQUA + h[1]);
		}
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		BlockPos p = null;
		
		if(player.getHeldItem(hand).hasTagCompound())
		{
			NBTTagCompound nbt = player.getHeldItem(hand).getTagCompound();
			
			if(facing.equals(EnumFacing.DOWN))
			{
				p = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ());
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			if(facing.equals(EnumFacing.UP))
			{
				p = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			if(facing.equals(EnumFacing.WEST))
			{
				p = new BlockPos(pos.getX()-1, pos.getY(), pos.getZ());
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			if(facing.equals(EnumFacing.NORTH))
			{
				p = new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1);
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			if(facing.equals(EnumFacing.EAST))
			{
				p = new BlockPos(pos.getX()+1, pos.getY(), pos.getZ());
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			if(facing.equals(EnumFacing.SOUTH))
			{
				p = new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1);
				if(world.getBlockState(p).getBlock().equals(Blocks.AIR))
				{
					nbt.setInteger("x", p.getX());
					nbt.setInteger("y", p.getY());
					nbt.setInteger("z", p.getZ());
					world.setBlockState(p, Blocks.MOB_SPAWNER.getDefaultState());
					world.getTileEntity(p).readFromNBT(nbt);
					player.getHeldItem(hand).setCount(0);
					return EnumActionResult.SUCCESS;
				}
			}
			
		}
		
		System.out.println(facing.getName());
		
		return null;
	}

}
