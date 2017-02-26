package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wilby.argh.api.block.ArghContainerBlock;
import wilby.argh.common.tileentity.TileEntityQuarry;

public class BlockQuarry extends ArghContainerBlock
{

	public BlockQuarry(Material materialIn, String name) 
	{
		super(materialIn, name);
	}

	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) 
	{
		return new TileEntityQuarry();
	}
	
	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.SOLID;
	}
}
