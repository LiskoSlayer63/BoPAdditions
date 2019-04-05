package liznet.bopadditions.proxy;

import java.util.HashMap;
import java.util.Map;
import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.items.ModItems;
import liznet.bopadditions.renderers.ModRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=BOPAdditions.modId)
public class ClientProxy extends CommonProxy
{
	private static boolean ENABLE_RENDERER = true;
	private static Map<String, String> colorMap = new HashMap<String, String>();

	@Override
	public void preInit() {
		super.preInit();
		
		colorMap.put("AMBER", "e08c10");
		colorMap.put("AMETHYST", "d03bdf");
		colorMap.put("EMERALD", "009529");
		colorMap.put("MALACHITE", "098a73");
		colorMap.put("PERIDOT", "4b830e");
		colorMap.put("RUBY", "900125");
		colorMap.put("SAPPHIRE", "0d5b81");
		colorMap.put("TANZANITE", "610386");
		colorMap.put("TOPAZ", "e45b07");
	}

	@Override
	public void init() {
		super.init();
		
		if(ENABLE_RENDERER)
			ModRenderer.init();
	}

	@Override
	public void postInit() {
		super.postInit();
	}
	
	// CLIENT EVENTS
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		ModItems.registerRenders(event);
	}
	
	@SubscribeEvent
	public static void onConfigChanged(final OnConfigChangedEvent event) 
	{
		if (event.getModID().equals(BOPAdditions.modId))
			ConfigManager.sync(BOPAdditions.modId, Config.Type.INSTANCE);
	}
	
	
	// HELPER FUNCTIONS
	
	public static float alphaFromColor(int parColor)
    {
        return (parColor >> 24 & 255) / 255.0F;
	}	
	public static float redFromColor(int parColor)
    {
        return (parColor >> 16 & 255) / 255.0F;
    }   
    public static float greenFromColor(int parColor)
    {
        return (parColor >> 8 & 255) / 255.0F;
    }    
    public static float blueFromColor(int parColor)
    {
        return (parColor & 255) / 255.0F;
    }
	public static int getEffectColor(ItemStack stack){
        int alpha = 0x66000000;
        String gemName = ModItems.getGemName(stack);
        
        if(colorMap.containsKey(gemName)){
	        return alpha | Integer.parseInt(colorMap.get(gemName), 16);
        }
		
        return -8372020;
	}
}
