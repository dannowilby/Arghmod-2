package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import wilby.argh.api.block.ArghBlock;

public class BlockBananaPlant extends ArghBlock implements IPlantable
{

	public BlockBananaPlant(Material materialIn, String name) 
	{
		super(materialIn, name);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) 
	{
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) 
	{
		return this.getDefaultState();
	}

}
