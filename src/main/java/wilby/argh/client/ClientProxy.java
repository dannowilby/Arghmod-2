package wilby.argh.client;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wilby.argh.client.tileentityspecialrenderer.TileEntitySpecialRendererQuarry;
import wilby.argh.client.tileentityspecialrenderer.TileEntitySpecialRendererSolarPanel;
import wilby.argh.common.CommonProxy;
import wilby.argh.common.tileentity.TileEntityQuarry;
import wilby.argh.common.tileentity.TileEntitySolarPanel;

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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySolarPanel.class, new TileEntitySpecialRendererSolarPanel());
	}
	
	@Override
	public void postinit()
	{
		
	}
	
}
