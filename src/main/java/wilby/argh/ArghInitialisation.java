package wilby.argh;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.api.ArghBlock;
import wilby.api.ArghContainerBlock;
import wilby.api.ArghItem;
import wilby.api.ArghItemArmour;
import wilby.api.ArghItemTool;
import wilby.api.ArghTier;
import wilby.argh.common.block.BlockArghFuel;
import wilby.argh.common.block.BlockBananaPlant;
import wilby.argh.common.block.BlockBox;
import wilby.argh.common.block.BlockEnergyExportNode;
import wilby.argh.common.block.BlockEnergyImportNode;
import wilby.argh.common.block.BlockItemEnergiser;
import wilby.argh.common.block.BlockOreProcessorController;
import wilby.argh.common.block.BlockQuarry;
import wilby.argh.common.block.BlockSolarPanel;
import wilby.argh.common.enchantment.EnchantmentLifeSteal;
import wilby.argh.common.enchantment.EnchantmentRepair;
import wilby.argh.common.enchantment.EnchantmentSpawnerSilk;
import wilby.argh.common.gui.ArghGuiHandler;
import wilby.argh.common.item.ItemArghFuel;
import wilby.argh.common.item.ItemArghMetre;
import wilby.argh.common.item.ItemArghSaber;
import wilby.argh.common.item.ItemDiamondDrillHead;
import wilby.argh.common.item.ItemEnergisedDiamond;
import wilby.argh.common.item.ItemEnforcedJetpack;
import wilby.argh.common.item.ItemHardenedJetpack;
import wilby.argh.common.item.ItemMasterCiruit;
import wilby.argh.common.item.ItemMobSpawner;
import wilby.argh.common.item.ItemResonantJetpack;
import wilby.argh.common.item.ItemSilicon;
import wilby.argh.common.tileentity.TileEntityBox;
import wilby.argh.common.tileentity.TileEntityExportNode;
import wilby.argh.common.tileentity.TileEntityImportNode;
import wilby.argh.common.tileentity.TileEntityItemEnergiser;
import wilby.argh.common.tileentity.TileEntityOreRefinery;
import wilby.argh.common.tileentity.TileEntityQuarry;
import wilby.argh.common.tileentity.TileEntitySolarPanel;
import wilby.argh.common.util.ArghRecipes;
import wilby.manual.ItemManual;

public class ArghInitialisation 
{
	public static void init(org.apache.logging.log4j.Logger l2, File config)
	{
		
		Configuration c = new Configuration(config);
    	
		String category = "main";
		
    	c.load();
    	
    	boolean hardcore = c.getBoolean("Hardcore", category, false, "True will make argh mobs more powerful and ores more rare");
    	
    	String pigmanSpawn = c.getString("Pigman Spawn Biomes", category, "", "List the biomes you want the mob not to spawn in");
    	int pigmanAmount = c.getInt("Pigman Spawn Amount", category, 5, 3, 7, "Size of pigman spawns");
    	
    	String oreSpawn = c.getString("Ore Spawn Biomes", category, "", "List the biomes you want the ore not to spawn in");
    	int oreAmount = c.getInt("Ore Spawn Amount", category, 5, 3, 7, "Size of ore spawns");
    			
    	c.save();
		
    	Reference.arghTab = new ArghCreativeTab("Arghon");
		
		ArrayList<ArghBlock> stack = new ArrayList<ArghBlock>();
		
		Reference.solar = new BlockSolarPanel(Material.GLASS, "solarpanel");
		Reference.quarry = new BlockQuarry(Material.IRON, "quarry");
		Reference.box = new BlockBox(Material.WOOD, "box");
		Reference.itemEnergiser = new BlockItemEnergiser(Material.IRON, "itemenerg");
		Reference.oreRefineryController = new BlockOreProcessorController("opc");
		
		Reference.arghFuels = new BlockArghFuel(Material.TNT, "arghfuel");
		Reference.bananaPlant = new BlockBananaPlant(Material.PLANTS, "bananaplant");
		
		stack.add(Reference.quarry);
		stack.add(Reference.box);
		stack.add(Reference.arghFuels);
		stack.add(Reference.solar);
		stack.add(Reference.itemEnergiser);
		stack.add(Reference.oreRefineryController);
		
		for(int l = 0; l < stack.size(); l++)
		{
			GameRegistry.registerWithItem(stack.get(l));
			l2.info("Registering Block: " + stack.get(l).getName());
		}
		
		GameRegistry.registerWithItem(Reference.bananaPlant);
		
		//We have to do this manually because otherwise it does not work correctly
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.quarry), 0, new ModelResourceLocation(Argh.MODID + ":" + "quarry", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.box), 0, new ModelResourceLocation(Argh.MODID + ":" + "box", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.solar), 0, new ModelResourceLocation(Argh.MODID + ":" + "solarpanel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.arghFuels), 0, new ModelResourceLocation(Argh.MODID + ":" + "arghfuel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.itemEnergiser), 0, new ModelResourceLocation(Argh.MODID + ":" + "itemenerg", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.bananaPlant), 0, new ModelResourceLocation(Argh.MODID + ":" + "bananaplant", "inventory"));
		
		EnumHelper.addToolMaterial("saber", 0, 16334, 4.0f, 25.0f, 10);
		
		ArrayList<ArghItem> items = new ArrayList<ArghItem>();
		
		Reference.enforcedJetpack = new ItemEnforcedJetpack("enforcedJetpack", ArghTier.TIER1);
		Reference.hardenedJetpack = new ItemHardenedJetpack("hardenedJetpack", ArghTier.TIER2);
		Reference.resonantJetpack = new ItemResonantJetpack("resonantJetpack", ArghTier.TIER3);
		
		Reference.saber = new ItemArghSaber("saber", 16334);
		Reference.arghFuel = new ItemArghFuel("sarghFuel");
		Reference.masterCircuit = new ItemMasterCiruit("masterCircuit");
		Reference.silicon = new ItemSilicon("silicon");
		Reference.arghmetre = new ItemArghMetre("arghmetre");
		Reference.diamondDrillhead = new ItemDiamondDrillHead("drillhead");
		Reference.ediamond = new ItemEnergisedDiamond("ediamond");
		Reference.mob = new ItemMobSpawner("mob");
		
		items.add(Reference.arghFuel);
		items.add(Reference.masterCircuit);
		items.add(Reference.silicon);
		items.add(Reference.arghmetre);
		items.add(Reference.diamondDrillhead);
		items.add(Reference.ediamond);
		items.add(Reference.mob);
		
		for(int l = 0; l < items.size(); l++)
		{
			GameRegistry.register(items.get(l));
			registerRendersItem(items.get(l));
			l2.info("Registering Item: " + items.get(l).getName());
		}
		
		l2.info("Registering Item Container: " + Reference.saber.getName());
		GameRegistry.register(Reference.saber);
		renderTool(Reference.saber);
		l2.info("Registering Item Container: " + Reference.enforcedJetpack.getName());
		GameRegistry.register(Reference.enforcedJetpack);
		renderArmour(Reference.enforcedJetpack);
		l2.info("Registering Item Container: " + Reference.hardenedJetpack.getName());
		GameRegistry.register(Reference.hardenedJetpack);
		renderArmour(Reference.hardenedJetpack);
		l2.info("Registering Item Container: " + Reference.resonantJetpack.getName());
		GameRegistry.register(Reference.resonantJetpack);
		renderArmour(Reference.resonantJetpack);
		
		
		GameRegistry.register(new ItemManual("manual"));
		
		FMLCommonHandler.instance().bus().register(Reference.aeh);
		MinecraftForge.EVENT_BUS.register(Reference.aeh);
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		a.add(new Object[]{TileEntitySolarPanel.class, "solarPanel"});
		a.add(new Object[]{TileEntityQuarry.class, "quarry"});
		a.add(new Object[]{TileEntityBox.class, "box"});
		a.add(new Object[]{TileEntityItemEnergiser.class, "itemenerg"});
		a.add(new Object[]{TileEntityOreRefinery.class, "refinery"});
		
		for(int l = 0; l < a.size(); l++)
		{
			GameRegistry.registerTileEntity((Class<? extends TileEntity>)a.get(l)[0], (String)a.get(l)[1]);
			l2.info("Registering Tile Entity: " + a.get(l)[1]);
		}
		
		Reference.repair = new EnchantmentRepair();
		Reference.silk = new EnchantmentSpawnerSilk();
		Reference.steal = new EnchantmentLifeSteal();
    	l2.info("Registering Enchantment: " + Reference.repair.getName());
    	l2.info("Registering Enchantment: " + Reference.silk.getName());
    	l2.info("Registering Enchantment: " + Reference.steal.getName());
    	GameRegistry.register(Reference.repair);
    	GameRegistry.register(Reference.silk);
    	GameRegistry.register(Reference.steal);

		NetworkRegistry.INSTANCE.registerGuiHandler(Argh.argh, new ArghGuiHandler());
		
		Reference.ienode = new BlockEnergyImportNode(Material.CLOTH, "ei");
		Reference.eenode = new BlockEnergyExportNode(Material.CLOTH, "ee");
		GameRegistry.registerTileEntity(TileEntityExportNode.class, "exportNode");
		GameRegistry.registerTileEntity(TileEntityImportNode.class, "importNode");
		
		ArrayList<ArghContainerBlock> blocks = new ArrayList<ArghContainerBlock>();
		
		blocks.add(Reference.eenode);
		blocks.add(Reference.ienode);
		
		for(int l = 0; l < blocks.size(); l++)
		{
			GameRegistry.registerWithItem(blocks.get(l));
			l2.info("Registering Transport Block: " + blocks.get(l).getName());
		}
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.ienode), 0, new ModelResourceLocation(Argh.MODID + ":" + "ei", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(Reference.eenode), 0, new ModelResourceLocation(Argh.MODID + ":" + "ee", "inventory"));
		
		ArghRecipes.init(l2);
	}
	
	//Rendering
	
	private static void renderTool(ArghItemTool item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(Argh.MODID + ":" + item.getName(), "inventory"));
	}
	
	private static void renderArmour(ArghItemArmour item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(Argh.MODID + ":" + item.getName(), "inventory"));
	}
	
	private static void registerRendersItem(ArghItem item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(Argh.MODID + ":" + item.getName(), "inventory"));
	}
	
}
