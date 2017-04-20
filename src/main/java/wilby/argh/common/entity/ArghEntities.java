package wilby.argh.common.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityAnimal;

public class ArghEntities 
{
	
	public static EntityAnimal pigman;
	
	public static void init()
	{
		pigman = new EntityPigman(Minecraft.getMinecraft().world);
	}
	
}
