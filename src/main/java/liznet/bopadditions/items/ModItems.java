package liznet.bopadditions.items;

import java.util.ArrayList;
import java.util.List;

import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.materials.ModMaterials;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {
	private static List<Item> TOOLS = new ArrayList<Item>();
	private static List<Item> ARMORS = new ArrayList<Item>();
	
	public static void init() {
		for(ModMaterial material : ModMaterials.getMaterials()){
			TOOLS.add(new GemPickaxe(material));
			TOOLS.add(new GemAxe(material));
			TOOLS.add(new GemSpade(material));
			TOOLS.add(new GemHoe(material));
			TOOLS.add(new GemSword(material));
			
			ARMORS.add(new GemHelmet(material));
			ARMORS.add(new GemChestplate(material));
			ARMORS.add(new GemLeggings(material));
			ARMORS.add(new GemBoots(material));
		}
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for(Item tool : TOOLS)
			event.getRegistry().register(tool);
		
		for(Item armor : ARMORS)
			event.getRegistry().register(armor);
	}
	
	public static void registerRenders(ModelRegistryEvent event) {
		for(Item tool : TOOLS)
			ModelLoader.setCustomModelResourceLocation(tool, 0, new ModelResourceLocation(tool.getRegistryName(), "inventory"));
		
		for(Item armor : ARMORS)
			ModelLoader.setCustomModelResourceLocation(armor, 0, new ModelResourceLocation(armor.getRegistryName(), "inventory"));
	}
	
	public static boolean isModItem(ItemStack stack){
		return !stack.isEmpty() && (
				stack.getItem() instanceof GemAxe || 
				stack.getItem() instanceof GemHoe || 
				stack.getItem() instanceof GemPickaxe || 
				stack.getItem() instanceof GemSpade || 
				stack.getItem() instanceof GemSword ||
				stack.getItem() instanceof GemBoots ||
				stack.getItem() instanceof GemChestplate ||
				stack.getItem() instanceof GemHelmet ||
				stack.getItem() instanceof GemLeggings);
	}
	
	public static String getGemName(ItemStack stack) {
		return isModItem(stack) ? stack.getItem().getRegistryName().getPath().split("_")[0].toUpperCase() : null;
	}
}
