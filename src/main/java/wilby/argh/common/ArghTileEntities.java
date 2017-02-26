package wilby.argh.common;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.common.tileentity.TileEntityBox;
import wilby.argh.common.tileentity.TileEntityItemEnergiser;
import wilby.argh.common.tileentity.TileEntityQuarry;
import wilby.argh.common.tileentity.TileEntitySolarPanel;

public class ArghTileEntities 
{
	public static void init()
	{
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		a.add(new Object[]{TileEntitySolarPanel.class, "solarPanel"});
		a.add(new Object[]{TileEntityQuarry.class, "quarry"});
		a.add(new Object[]{TileEntityBox.class, "box"});
		a.add(new Object[]{TileEntityItemEnergiser.class, "itemenerg"});
		
		a.forEach((j) -> {
			GameRegistry.registerTileEntity((Class<? extends TileEntity>)j[0], (String)j[1]);
		});
	}
}
