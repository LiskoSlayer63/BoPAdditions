package liznet.bopadditions.proxy;

import java.util.HashMap;
import java.util.Map;
import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.BOPAdditionsConfig;
import liznet.bopadditions.items.ModItems;
import liznet.bopadditions.logging.Logger;
import liznet.bopadditions.renderers.ModRenderer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=BOPAdditions.modId)
public class ClientProxy extends CommonProxy
{
	private static Map<String, String> COLORMAP = new HashMap<String, String>();

	@Override
	public void preInit() 
	{
		super.preInit();
		Logger.debug("ClientProxy PreInit!");

		COLORMAP.put("AMBER", "e08c10");
		COLORMAP.put("AMETHYST", "d03bdf");
		COLORMAP.put("EMERALD", "009529");
		COLORMAP.put("MALACHITE", "098a73");
		COLORMAP.put("PERIDOT", "4b830e");
		COLORMAP.put("RUBY", "900125");
		COLORMAP.put("SAPPHIRE", "0d5b81");
		COLORMAP.put("TANZANITE", "610386");
		COLORMAP.put("TOPAZ", "e45b07");
		
		Logger.debug(COLORMAP.size() + " Custom Enchant Colors set!");
	}

	@Override
	public void init() 
	{
		super.init();
		Logger.debug("ClientProxy Init!");
		
		if(BOPAdditionsConfig.ENABLE_ARMOR_RENDERER || BOPAdditionsConfig.ENABLE_ITEM_RENDERER)
			ModRenderer.init();
	}

	@Override
	public void postInit() 
	{
		super.postInit();
		Logger.debug("ClientProxy PostInit!");
	}
	
	// CLIENT EVENTS
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) 
	{
		ModItems.registerRenders(event);
		
		Logger.debug("Renders registered!");
	}
	
	@SubscribeEvent
	public static void onConfigChanged(final OnConfigChangedEvent event) 
	{
		if (event.getModID().equals(BOPAdditions.modId))
		{
			ConfigManager.sync(BOPAdditions.modId, Config.Type.INSTANCE);
			
			Logger.debug("Configuration changed!");
		}
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
	public static int getEffectColor(String name)
	{
        int alpha = 0x66000000;
        
        if(COLORMAP.containsKey(name))
	        return alpha | Integer.parseInt(COLORMAP.get(name), 16);
		
        return -8372020;
	}
}
