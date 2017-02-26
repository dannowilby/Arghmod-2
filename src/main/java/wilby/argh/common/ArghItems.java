package wilby.argh.common;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import wilby.argh.Argh;
import wilby.argh.api.item.ArghItem;
import wilby.argh.api.item.ArghItemArmour;
import wilby.argh.common.item.ItemArghFuel;
import wilby.argh.common.item.ItemArghMetre;
import wilby.argh.common.item.ItemDiamondDrillHead;
import wilby.argh.common.item.ItemJetpack;
import wilby.argh.common.item.ItemMasterCiruit;
import wilby.argh.common.item.ItemSilicon;

public class ArghItems
{

	public static ArghItemArmour jetpack;

	public static ArghItem arghFuel;
	public static ArghItem masterCircuit;
	public static ArghItem silicon;
	public static ArghItem arghmetre;
	public static ArghItem diamondDrillhead;

	public static void init()
	{

		ArrayList<ArghItem> items = new ArrayList<ArghItem>();

		jetpack = new ItemJetpack("jetpack");
		arghFuel = new ItemArghFuel("sarghFuel");
		masterCircuit = new ItemMasterCiruit("masterCircuit");
		silicon = new ItemSilicon("silicon");
		arghmetre = new ItemArghMetre("arghmetre");
		diamondDrillhead = new ItemDiamondDrillHead("drillhead");

		items.add(jetpack);
		items.add(arghFuel);
		items.add(masterCircuit);
		items.add(silicon);
		items.add(arghmetre);
		items.add(diamondDrillhead);

		items.forEach((item) -> {
			GameRegistry.register(item);
			registerRenders(item);
		});

	}

	private static void registerRenders(ArghItem item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(Argh.MODID + ":" + item.getName(), "inventory"));
	}

}
