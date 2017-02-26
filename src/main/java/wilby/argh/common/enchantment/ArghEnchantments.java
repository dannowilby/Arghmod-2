package wilby.argh.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ArghEnchantments 
{
	
	public static Enchantment repair;
    public static Enchantment silk;
	
	public static void init()
	{
		
		repair = new EnchantmentRepair();
    	silk = new EnchantmentSpawnerSilk();
		
    	GameRegistry.register(repair);
    	GameRegistry.register(silk);
    	
	}
	
}
