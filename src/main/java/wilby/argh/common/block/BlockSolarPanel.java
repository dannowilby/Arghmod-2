package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import wilby.argh.api.block.ArghContainerBlock;
import wilby.argh.common.tileentity.TileEntityQuarry;
import wilby.argh.common.tileentity.TileEntitySolarPanel;

public class BlockSolarPanel extends ArghContainerBlock
{

	public BlockSolarPanel(Material materialIn, String name) 
	{
		super(materialIn, name);
	}
	
	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) 
	{
		return new TileEntitySolarPanel();
	}

}
