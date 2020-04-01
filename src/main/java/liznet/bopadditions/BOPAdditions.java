package liznet.bopadditions;

import liznet.bopadditions.logging.Logger;
import liznet.bopadditions.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BOPAdditions.modId, name = BOPAdditions.name, version = BOPAdditions.version, dependencies = "required-after:biomesoplenty", acceptedMinecraftVersions = "[1.12.2]")
public class BOPAdditions 
{
	public static final String modId = "bopadditions";
	public static final String name = "Biomes O' Plenty Additions";
	public static final String version = "1.1.2";

	@Mod.Instance(BOPAdditions.modId)
	public static BOPAdditions instance;
	
	@SidedProxy(serverSide = "liznet.bopadditions.proxy.CommonProxy", clientSide = "liznet.bopadditions.proxy.ClientProxy")
    public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		Logger.init(event.getModLog());
		Logger.enableDebug(BOPAdditionsConfig.DEBUG);
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit();
	}

}