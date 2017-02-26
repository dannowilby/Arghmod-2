package wilby.argh.common.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.common.ArghBlocks;
import wilby.argh.common.ArghItems;

public class ArghRecipes 
{
	
	public static void init()
	{
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.jetpack),
    			"ICI",
    			"NRN",
    			"LGL",
    			'I', Blocks.IRON_BLOCK, 'R', Items.REDSTONE, 'N', Items.IRON_INGOT, 'G', Items.GUNPOWDER, 'L', Items.GLOWSTONE_DUST, 'C', ArghItems.masterCircuit
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.arghFuel),
    			"GCG",
    			"CCC",
    			"GCG",
    			'C', Items.COAL, 'G', Items.GUNPOWDER
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.silicon, 16), 
				"SSS",
				"SBS",
				"SSS",
				'B', new ItemStack(Items.LAVA_BUCKET), 'S', new ItemStack(Item.getItemFromBlock(Blocks.SAND))
			);
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.masterCircuit),
    			"SSS",
    			"RRR",
    			"SSS",
    			'R', Items.REDSTONE, 'S', ArghItems.silicon
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghBlocks.box),
    			" W ",
    			"W W",
    			" W ",
    			'W', Blocks.PLANKS
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghBlocks.quarry),
    			"SRS",
    			"ddd",
    			"SDS",
    			'R', Items.REDSTONE, 'S', ArghItems.silicon, 'D', ArghItems.diamondDrillhead, 'd', Items.DIAMOND
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.arghmetre),
    			" S ",
    			"IRI",
    			" S ",
    			'R', Items.REDSTONE, 'S', ArghItems.silicon, 'I', Items.IRON_INGOT
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghItems.diamondDrillhead),
    			"RSR",
    			"DSD",
    			"DDD",
    			'R', Items.REDSTONE, 'S', ArghItems.silicon, 'D', Items.DIAMOND
    		);
		
		GameRegistry.addRecipe(new ItemStack(ArghBlocks.arghFuel),
    			"DDD",
    			"DDD",
    			"DDD",
    			'D', ArghItems.arghFuel
    		);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ArghItems.arghFuel, 9), ArghBlocks.arghFuel);
		
	}
	
}
