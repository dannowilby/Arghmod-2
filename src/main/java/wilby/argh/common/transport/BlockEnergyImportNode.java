package wilby.argh.common.transport;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wilby.argh.api.block.ArghContainerBlock;

public class BlockEnergyImportNode extends ArghContainerBlock
{

	protected BlockEnergyImportNode(Material materialIn, String name)
	{
		super(materialIn, name);
	}
	
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		if(world.getTileEntity(pos) instanceof TileEntityImportNode)
		{
			TileEntityImportNode te = (TileEntityImportNode) world.getTileEntity(pos);
			te.setNodeType(EnumNodeType.ENERGY);
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityImportNode();
	}

}
