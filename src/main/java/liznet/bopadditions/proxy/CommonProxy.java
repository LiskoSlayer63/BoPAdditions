package liznet.bopadditions.proxy;

import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.items.ModItems;
import liznet.bopadditions.materials.ModMaterials;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=BOPAdditions.modId)
public class CommonProxy
{
	public void preInit() {
		ModMaterials.init();
		ModItems.init();
	}
	
	public void init() {
		
	}

	public void postInit() {
		
	}
	

	// SERVER EVENTS
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ModItems.registerItems(event);
	}
}
