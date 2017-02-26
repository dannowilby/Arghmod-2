package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import wilby.argh.api.block.ArghBlock;

public class BlockArghFuel extends ArghBlock 
{

	public BlockArghFuel(Material materialIn, String name) 
	{
		super(materialIn, name);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,	EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		world.newExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 1f, false, false);
		return true;
	}
	
	
}
