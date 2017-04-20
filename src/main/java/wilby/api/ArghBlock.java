package wilby.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import wilby.argh.Reference;

public class ArghBlock extends Block 
{
	
	private String name;
	
	public ArghBlock(Material materialIn, String name) 
	{
		super(materialIn);
		this.name = name;
		this.setCreativeTab(Reference.arghTab);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setResistance(1.5f);
		this.setHardness(1.0f);
	}
	
	public String getName()
	{
		return name;
	}
	
}