package wilby.argh.common.transport;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityItemduct extends TileEntity implements ITickable
{

	EnumFacing direction;
	
	@Override
	public void update()
	{
		transport();
	}
	
	private void transport()
	{
		
	}

	public BlockPos findNextDirection()
	{
		
		
		return null;
	}
	
	public EnumFacing getDirection()
	{
		return direction;
	}
	
	public void setDirection(EnumFacing facing)
	{
		direction = facing;
	}

}
