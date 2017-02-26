package wilby.argh.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import wilby.argh.Initialisation;

public class ArghBlock extends Block 
{
	
	private static String name;
	
	public ArghBlock(Material materialIn, String name) 
	{
		super(materialIn);
		this.name = name;
		this.setCreativeTab(Initialisation.arghTab);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setResistance(1.5f);
		this.setHardness(1.0f);
	}
	
	public static String getName()
	{
		return name;
	}
	
}