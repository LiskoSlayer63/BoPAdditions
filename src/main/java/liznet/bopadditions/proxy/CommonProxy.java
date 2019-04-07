package liznet.bopadditions.proxy;

import liznet.bopadditions.BOPAdditions;
import liznet.bopadditions.ai.ModAIFindPlayer;
import liznet.bopadditions.items.ModItems;
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
	
	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event) {
	    if (event.getEntity() instanceof EntityEnderman) {
        	EntityEnderman enderman = (EntityEnderman)event.getEntity();
        	EntityAITaskEntry[] entries = enderman.targetTasks.taskEntries.toArray(new EntityAITaskEntry[enderman.targetTasks.taskEntries.size()]);
        	
        	for(EntityAITaskEntry entry : entries){
        		EntityAIBase ai = entry.action;
        		
            	if(ai.getClass().getName().equals("net.minecraft.entity.monster.EntityEnderman$AIFindPlayer")){
            		enderman.targetTasks.removeTask(ai);
                	enderman.targetTasks.addTask(1, new ModAIFindPlayer(enderman));
            	}
        	}
	    }
	}
}
