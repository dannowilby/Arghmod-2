package wilby.argh;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import wilby.api.ArghBlock;
import wilby.api.ArghContainerBlock;
import wilby.api.ArghItem;
import wilby.api.ArghItemArmour;
import wilby.api.ArghItemTool;
import wilby.argh.common.item.ItemJetpack;
import wilby.argh.common.util.ArghEventHandler;

public class Reference 
{
	
	public static ArghContainerBlock box;
	public static ArghContainerBlock solar;
	public static ArghContainerBlock quarry;
	public static ArghContainerBlock itemEnergiser;
	public static ArghContainerBlock oreRefineryController;
	public static ArghBlock arghFuels;
	public static Block bananaPlant;
	
	public static ArghItemArmour enforcedJetpack;
	public static ArghItemArmour hardenedJetpack;
	public static ArghItemArmour resonantJetpack;

	public static ArghItemTool saber;
	
	public static ArghItem arghFuel;
	public static ArghItem masterCircuit;
	public static ArghItem silicon;
	public static ArghItem arghmetre;
	public static ArghItem diamondDrillhead;
	public static ArghItem ediamond;
	public static ArghItem mob;
	
	public static ArghEventHandler aeh = new ArghEventHandler();
	
	public static Enchantment repair;
    public static Enchantment silk;
    public static Enchantment steal;
    
	public static CreativeTabs arghTab;
	
	public static ArghContainerBlock ienode;
	public static ArghContainerBlock eenode;
	
	public static int guiInvId = 0;
	
}
