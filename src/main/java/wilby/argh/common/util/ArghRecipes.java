package wilby.argh.common.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.api.ArghTier;
import wilby.argh.Reference;
import wilby.argh.common.item.ItemJetpack;

public class ArghRecipes 
{
	
	public static void init(org.apache.logging.log4j.Logger l)
	{
		
		l.info("Starting Parsing Recipes");
		
		GameRegistry.addRecipe(new ItemStack(Reference.enforcedJetpack),
    			"ICI",
    			"NRN",
    			"LGL",
    			'I', Blocks.IRON_BLOCK, 'R', Items.REDSTONE, 'N', Items.IRON_INGOT, 'G', Items.GUNPOWDER, 'L', Items.GLOWSTONE_DUST, 'C', Reference.masterCircuit
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.hardenedJetpack), 
				"DDD",
				"DDD",
				"DDD",
				'D', Blocks.DIAMOND_BLOCK
			);
		
		GameRegistry.addRecipe(new ItemStack(Reference.resonantJetpack), 
				"DDD",
				"DDD",
				"DDD",
				'D', Blocks.EMERALD_BLOCK
			);
		
		GameRegistry.addRecipe(new ItemStack(Reference.solar),
    			"GGG",
    			"RDR",
    			"IMI",
    			'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'G', Blocks.GLASS, 'D', Items.DIAMOND, 'M', Reference.masterCircuit
    		);
		
		/*GameRegistry.addRecipe(new ItemStack(ArghItems.arghFuel),
    			"GCG",
    			"CCC",
    			"GCG",
    			'C', Items.COAL, 'G', Items.GUNPOWDER
    		);*/
		
		GameRegistry.addRecipe(new ItemStack(Reference.silicon, 16), 
				"SSS",
				"SBS",
				"SSS",
				'B', new ItemStack(Items.LAVA_BUCKET), 'S', new ItemStack(Item.getItemFromBlock(Blocks.SAND))
			);
		
		GameRegistry.addRecipe(new ItemStack(Reference.masterCircuit),
    			"SSS",
    			"RRR",
    			"SSS",
    			'R', Items.REDSTONE, 'S', Reference.silicon
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.box),
    			" W ",
    			"W W",
    			" W ",
    			'W', Blocks.PLANKS
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.quarry),
    			"SRS",
    			"ddd",
    			"SDS",
    			'R', Items.REDSTONE, 'S', Reference.silicon, 'D', Reference.diamondDrillhead, 'd', Items.DIAMOND
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.arghmetre),
    			" S ",
    			"IRI",
    			" S ",
    			'R', Items.REDSTONE, 'S', Reference.silicon, 'I', Items.IRON_INGOT
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.diamondDrillhead),
    			"RSR",
    			"DSD",
    			"DDD",
    			'R', Items.REDSTONE, 'S', Reference.silicon, 'D', Reference.ediamond
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.arghFuel),
    			"DDD",
    			"DDD",
    			"DDD",
    			'D', Reference.arghFuel
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.itemEnergiser),
    			"SDS",
    			"RMR",
    			"SDS",
    			'D', Items.DIAMOND, 'S', Reference.silicon, 'R', Items.REDSTONE, 'M', Reference.masterCircuit
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.saber),
    			" DD",
    			"RD ",
    			"SR ",
    			'D', Reference.ediamond, 'R', Items.REDSTONE, 'S', Items.STICK
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.eenode, 8),
    			"I I",
    			" D ",
    			"I I",
    			'D', Reference.ediamond, 'I', Reference.silicon
    		);
		
		GameRegistry.addRecipe(new ItemStack(Reference.ienode, 8),
    			"IRI",
    			" D ",
    			"IRI",
    			'D', Reference.ediamond, 'I', Reference.silicon, 'R', Items.REDSTONE
    		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(Reference.arghFuel, 9), Reference.arghFuel);
		
		l.info("Finished Parsing Recipes");
		
		
	}
	
}
