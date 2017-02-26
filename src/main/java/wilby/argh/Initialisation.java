package wilby.argh;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import wilby.argh.common.ArghBlocks;
import wilby.argh.common.ArghItems;
import wilby.argh.common.ArghTileEntities;
import wilby.argh.common.enchantment.ArghEnchantments;
import wilby.argh.common.gui.ArghGuiHandler;
import wilby.argh.common.transport.ArghTransport;
import wilby.argh.common.util.ArghEventHandler;
import wilby.argh.common.util.ArghRecipes;

public class Initialisation {

	ArghEventHandler aeh = new ArghEventHandler();

	static class ArghCreativeTab extends CreativeTabs {
	
		String name;
		
		public String getName()
		{
			return name;
		}
		
		public ArghCreativeTab(String name)
		{
			super(name);
			this.name = name;
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Item.getItemFromBlock(ArghBlocks.solar));
		}
		
	}
	
	public static CreativeTabs arghTab;
	
	
			
	public Initialisation() {

	}

	public void preinit() {
		ArghEnchantments.init();
		arghTab = new ArghCreativeTab("Arghon");
		FMLCommonHandler.instance().bus().register(aeh);
		MinecraftForge.EVENT_BUS.register(aeh);
	}

	public void init() {
		ArghBlocks.init();
		ArghItems.init();
		ArghTransport.init();
		ArghRecipes.init();
		ArghTileEntities.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(Argh.argh, new ArghGuiHandler());
	}

	public void postinit() {

	}

}
