package wilby.argh.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.Argh;
import wilby.argh.api.block.ArghBlock;
import wilby.argh.api.block.ArghContainerBlock;
import wilby.argh.common.block.BlockArghFuel;
import wilby.argh.common.block.BlockBox;
import wilby.argh.common.block.BlockItemEnergiser;
import wilby.argh.common.block.BlockQuarry;
import wilby.argh.common.block.BlockSolarPanel;

public class ArghBlocks 
{
	public static ArghContainerBlock box;
	public static ArghContainerBlock solar;
	public static ArghContainerBlock quarry;
	public static ArghContainerBlock itemEnergiser;
	public static ArghBlock arghFuel;
	
	public static void init()
	{
		ArrayList<ArghBlock> stack = new ArrayList<ArghBlock>();
		
		solar = new BlockSolarPanel(Material.GLASS, "solarpanel");
		quarry = new BlockQuarry(Material.IRON, "quarry");
		box = new BlockBox(Material.WOOD, "box");
		itemEnergiser = new BlockItemEnergiser(Material.IRON, "itemenerg");
		
		arghFuel = new BlockArghFuel(Material.TNT, "arghfuel");
		
		stack.add(quarry);
		stack.add(box);
		stack.add(arghFuel);
		stack.add(solar);
		stack.add(itemEnergiser);
		
		stack.forEach((block) -> {
			GameRegistry.registerWithItem(block);
		});
		
		//We have to do this manually because otherwise it does not work correctly
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(quarry), 0, new ModelResourceLocation(Argh.MODID + ":" + "quarry", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(box), 0, new ModelResourceLocation(Argh.MODID + ":" + "box", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(solar), 0, new ModelResourceLocation(Argh.MODID + ":" + "solarpanel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(arghFuel), 0, new ModelResourceLocation(Argh.MODID + ":" + "arghfuel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(itemEnergiser), 0, new ModelResourceLocation(Argh.MODID + ":" + "itemenerg", "inventory"));
	}
	
}
