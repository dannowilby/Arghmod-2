package wilby.api;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ArghContainerBlock extends ArghBlock 
{

	protected ArghContainerBlock(Material materialIn, String name) 
	{
		super(materialIn, name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return null;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	
}