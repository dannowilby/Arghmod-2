package wilby.argh.common.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import wilby.api.ArghContainerBlock;
import wilby.argh.common.tileentity.TileEntityOreRefinery;

public class BlockOreProcessorController extends ArghContainerBlock 
{
	
	public BlockOreProcessorController(String name) 
	{
		super(Material.IRON, name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityOreRefinery();
	}

}
