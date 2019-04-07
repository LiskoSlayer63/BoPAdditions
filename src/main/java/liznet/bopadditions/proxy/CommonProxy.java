package liznet.bopadditions.proxy;

import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.BOPAdditionsConfig;
import liznet.bopadditions.ai.ModAIFindPlayer;
import liznet.bopadditions.items.ModItems;
import liznet.bopadditions.logging.Logger;
import liznet.bopadditions.materials.ModMaterials;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid=BOPAdditions.modId)
public class CommonProxy
{
	public void preInit() 
	{
		Logger.debug("CommonProxy PreInit!");
		
		ModMaterials.init();
		ModItems.init();
	}
	
	public void init() 
	{
		Logger.debug("CommonProxy Init!");
	}

	public void postInit() 
	{
		Logger.debug("CommonProxy PostInit!");
	}
	

	// SERVER EVENTS
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		ModItems.registerItems(event);
		
		Logger.debug("Items registered!");
	}
	
	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event) 
	{
	    if (event.getEntity() instanceof EntityEnderman && BOPAdditionsConfig.ENDERMAN_IGNORE_AMETHYST_HELMET) 
	    {
        	EntityEnderman enderman = (EntityEnderman)event.getEntity();
        	EntityAITaskEntry[] entries = enderman.targetTasks.taskEntries.toArray(new EntityAITaskEntry[enderman.targetTasks.taskEntries.size()]);
        	
        	for(EntityAITaskEntry entry : entries)
        	{
        		EntityAIBase ai = entry.action;
        		
            	if(ai.getClass().getName().equals("net.minecraft.entity.monster.EntityEnderman$AIFindPlayer"))
            	{
            		enderman.targetTasks.removeTask(ai);
                	enderman.targetTasks.addTask(1, new ModAIFindPlayer(enderman));
                	
                	Logger.debug("Replaced AI Task " + ai.getClass().getName() + " > " + ModAIFindPlayer.class.getName() + " for entity " + enderman.getName());
            	}
        	}
	    }
	}
}
