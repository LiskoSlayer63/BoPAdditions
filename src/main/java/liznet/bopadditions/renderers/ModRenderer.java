package liznet.bopadditions.renderers;

import java.lang.reflect.Field;
import java.util.Map;

import liznet.bopadditions.BOPAdditionsConfig;
import liznet.bopadditions.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModRenderer 
{
	private static ModRenderItem modRenderItem;
	private static Field modelManager = ObfuscationReflectionHelper.findField(Minecraft.class, "field_175617_aL");
	private static Field renderItem = ObfuscationReflectionHelper.findField(Minecraft.class, "field_175621_X");
	private static Field itemRenderer = ObfuscationReflectionHelper.findField(ItemRenderer.class, "field_178112_h");
	private static Field playerRenderer = ObfuscationReflectionHelper.findField(RenderManager.class, "field_178637_m");
	private static Field skinMap = ObfuscationReflectionHelper.findField(RenderManager.class, "field_178636_l");

	@SuppressWarnings("unchecked")
	public static void init() 
	{
		if(!BOPAdditionsConfig.ENABLE_ITEM_RENDERER && !BOPAdditionsConfig.ENABLE_ARMOR_RENDERER) return;
		
		Minecraft mc = Minecraft.getMinecraft();
		
		Logger.debug("Initializing Custom Renderers ...");
        
		if(BOPAdditionsConfig.ENABLE_ITEM_RENDERER) 
		{
	        modelManager.setAccessible(true);
	        renderItem.setAccessible(true);
		}
		
		if(BOPAdditionsConfig.ENABLE_ARMOR_RENDERER)
			playerRenderer.setAccessible(true);
        
        try
        {
        	if(BOPAdditionsConfig.ENABLE_ITEM_RENDERER)
        	{
        		modRenderItem = new ModRenderItem(mc.getTextureManager(), (ModelManager) modelManager.get(mc), mc.getItemColors(), ((RenderItem)renderItem.get(mc)).getItemModelMesher());
        		renderItem.set(mc, modRenderItem);
        		itemRenderer.set(mc.getItemRenderer(), modRenderItem);
            	mc.getRenderManager().entityRenderMap.put(EntityItem.class, new RenderEntityItem(mc.getRenderManager(), modRenderItem));
            	
            	Logger.debug("ITEM Custom Renderers set!");
        	}
        	
        	if(BOPAdditionsConfig.ENABLE_ARMOR_RENDERER)
        	{
	            playerRenderer.set(mc.getRenderManager(), new ModRenderPlayer(mc.getRenderManager()));
	            ((Map<String, RenderPlayer>)skinMap.get(mc.getRenderManager())).put("default", new ModRenderPlayer(mc.getRenderManager()));
	            ((Map<String, RenderPlayer>)skinMap.get(mc.getRenderManager())).put("slim", new ModRenderPlayer(mc.getRenderManager(), true));
        		
	            Logger.debug("ARMOR Custom Renderers set!");
        	}
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
			Logger.error(e);
        }
	}
}
