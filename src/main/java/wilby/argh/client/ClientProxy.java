package wilby.argh.client;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import wilby.argh.client.tileentityspecialrenderer.TileEntitySpecialRendererQuarry;
import wilby.argh.common.CommonProxy;
import wilby.argh.common.tileentity.TileEntityQuarry;

public class ClientProxy extends CommonProxy 
{
	
	@Override
	public void registerRenderers() 
	{
		
	}
	
	@Override
	public void preinit()
	{
		
	}
	
	@Override
	public void init()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityQuarry.class, new TileEntitySpecialRendererQuarry());
	}
	
	@Override
	public void postinit()
	{
		
	}
	
}
