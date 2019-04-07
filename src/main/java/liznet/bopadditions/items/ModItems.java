package liznet.bopadditions.items;

import java.util.ArrayList;
import java.util.List;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.logging.Logger;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.materials.ModMaterials;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ModItems 
{
	private static List<Item> TOOLS = new ArrayList<Item>();
	private static List<Item> ARMORS = new ArrayList<Item>();
	
	public static void init() 
	{
		for(ModMaterial material : ModMaterials.getMaterials())
		{
			TOOLS.add(new GemPickaxe(material));
			TOOLS.add(new GemAxe(material));
			TOOLS.add(new GemSpade(material));
			TOOLS.add(new GemHoe(material));
			TOOLS.add(new GemSword(material));
			
			Logger.debug("Created tools for material " + material.name());
			
			ARMORS.add(new GemHelmet(material));
			ARMORS.add(new GemChestplate(material));
			ARMORS.add(new GemLeggings(material));
			ARMORS.add(new GemBoots(material));
			
			Logger.debug("Created armors for material " + material.name());
		}
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		for(Item tool : TOOLS) 
		{
			event.getRegistry().register(tool);
			
			Logger.debug("Registered ITEM for Tool " + tool.getRegistryName());
		}
		Logger.debug(TOOLS.size() + " ITEMS created for Tools!");
		
		for(Item armor : ARMORS)
		{
			event.getRegistry().register(armor);
			
			Logger.debug("Registered ITEM for Armor " + armor.getRegistryName());
		}
		Logger.debug(ARMORS.size() + " ITEMS created for Armors!");
	}
	
	public static void registerRenders(ModelRegistryEvent event) 
	{
		for(Item tool : TOOLS)
		{
			ModelLoader.setCustomModelResourceLocation(tool, 0, new ModelResourceLocation(tool.getRegistryName(), "inventory"));
			
			Logger.debug("Registered RENDER for Tool " + tool.getRegistryName());
		}
		Logger.debug(TOOLS.size() + " RENDERS created for Tools!");
		
		for(Item armor : ARMORS)
		{
			ModelLoader.setCustomModelResourceLocation(armor, 0, new ModelResourceLocation(armor.getRegistryName(), "inventory"));
			
			Logger.debug("Registered RENDER for Armor " + armor.getRegistryName());
		}
		Logger.debug(ARMORS.size() + " RENDERS created for Armors!");
	}
	
	public static boolean hasCustomEffect(ItemStack stack)
	{
		return !stack.isEmpty() && stack.getItem() instanceof ICustomEnchantColor;
	}
}
