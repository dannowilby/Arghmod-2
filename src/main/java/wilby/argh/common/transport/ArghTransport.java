package wilby.argh.common.transport;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.api.block.ArghContainerBlock;

public class ArghTransport
{
	
	public static ArghContainerBlock ienode;
	public static ArghContainerBlock eenode;
	
	public static void init()
	{
		ienode = new BlockEnergyImportNode(Material.CLOTH, "ei");
		eenode = new BlockEnergyExportNode(Material.CLOTH, "ee");
		GameRegistry.registerTileEntity(TileEntityExportNode.class, "exportNode");
		GameRegistry.registerTileEntity(TileEntityImportNode.class, "importNode");
		
		ArrayList<ArghContainerBlock> blocks = new ArrayList<ArghContainerBlock>();
		
		blocks.add(eenode);
		blocks.add(ienode);
		
		blocks.forEach((block) -> {
			GameRegistry.registerWithItem(block);
		});
	}
}
