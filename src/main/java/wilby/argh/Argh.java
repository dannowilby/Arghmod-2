package wilby.argh;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import wilby.argh.common.CommonProxy;

@Mod(modid = Argh.MODID, version = Argh.VERSION, name = Argh.NAME)
public class Argh
{
    public static final String MODID = "argh";
    public static final String VERSION = "1.0";
    public static final String NAME = "argh";
    
    @SidedProxy(clientSide = "wilby.argh.client.ClientProxy", serverSide = "wilby.argh.common.CommonProxy")
	public static CommonProxy proxy = new CommonProxy();
    
    public Initialisation init = new Initialisation();
    
    @Instance("argh")
    public static Argh argh = new Argh();
    
	public static int guiInvId = 0;

	public static Logger modlogger;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	modlogger = event.getModLog();
    	modlogger.info("Get ready for some fun! Argh is beginning to load!");
    	
    	proxy.preinit();
    	init.preinit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    	init.init();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	proxy.postinit();
    	
    	modlogger.info("Done loading Argh, now preparing for hyperspace!");
    }
}
