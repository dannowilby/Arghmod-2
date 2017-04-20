package wilby.argh.common.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import wilby.api.ArghTier;

public class ArghUtil 
{
	
	public static EnumFacing nrgRelative(BlockPos p, BlockPos pos)
	{
		
		if(p.getX() != pos.getX())
		{
			int i = p.getX() - pos.getX();
			if(i == 1)
			{
				return EnumFacing.WEST;
			}
			if(i == -1)
			{
				return EnumFacing.EAST;
			}
		}
		
		if(p.getY() != pos.getY())
		{
			int i = p.getY() - pos.getY();
			if(i == 1)
			{
				return EnumFacing.DOWN;
			}
			if(i == -1)
			{
				return EnumFacing.UP;
			}
		}
		
		if(p.getZ() != pos.getZ())
		{
			int i = p.getZ() - pos.getZ();
			if(i == 1)
			{
				return EnumFacing.SOUTH;
			}
			if(i == -1)
			{
				return EnumFacing.NORTH;
			}
		}
		
		return EnumFacing.DOWN;
	}
	
	public static ArghTier getTierFromName(String m)
	{
		
		switch(m)
		{
		case "Enforced":
			return ArghTier.TIER1;
		case "Hardened":
			return ArghTier.TIER2;
		case "Resonant":
			return ArghTier.TIER3;
		default:
			return null;
		}
		
	}
	
}
