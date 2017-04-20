package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wilby.api.ArghContainerBlock;
import wilby.api.EnumNodeType;
import wilby.argh.common.tileentity.TileEntityImportNode;

public class BlockEnergyImportNode extends ArghContainerBlock
{

	public BlockEnergyImportNode(Material materialIn, String name)
	{
		super(materialIn, name);
		this.setLightLevel(((float)8/(float)15));
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
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
