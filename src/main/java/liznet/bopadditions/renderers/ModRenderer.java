package liznet.bopadditions.renderers;

import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModRenderer {
	private static boolean ENABLE_ITEM_RENDERER = true;
	private static boolean ENABLE_ARMOR_RENDERER = true;
	
	private static ModRenderItem modRenderItem;
	private static Field modelManager = ObfuscationReflectionHelper.findField(Minecraft.class, "field_175617_aL");
	private static Field renderItem = ObfuscationReflectionHelper.findField(Minecraft.class, "field_175621_X");
	private static Field itemRenderer = ObfuscationReflectionHelper.findField(ItemRenderer.class, "field_178112_h");
	private static Field playerRenderer = ObfuscationReflectionHelper.findField(RenderManager.class, "field_178637_m");
	private static Field skinMap = ObfuscationReflectionHelper.findField(RenderManager.class, "field_178636_l");

	@SuppressWarnings("unchecked")
	public static void init() {
		if(!ENABLE_ITEM_RENDERER && !ENABLE_ARMOR_RENDERER) return;
		
		Minecraft mc = Minecraft.getMinecraft();
        
		if(ENABLE_ITEM_RENDERER) {
	        modelManager.setAccessible(true);
	        renderItem.setAccessible(true);
		}
		
		if(ENABLE_ARMOR_RENDERER)
			playerRenderer.setAccessible(true);
        
        try
        {
        	if(ENABLE_ITEM_RENDERER){
        		modRenderItem = new ModRenderItem(mc.getTextureManager(), (ModelManager) modelManager.get(mc), mc.getItemColors(), ((RenderItem)renderItem.get(mc)).getItemModelMesher());
        		renderItem.set(mc, modRenderItem);
        		itemRenderer.set(mc.getItemRenderer(), modRenderItem);
        	}
        	if(ENABLE_ARMOR_RENDERER){
	            playerRenderer.set(mc.getRenderManager(), new ModRenderPlayer(mc.getRenderManager()));
	            ((Map<String, RenderPlayer>)skinMap.get(mc.getRenderManager())).put("default", new ModRenderPlayer(mc.getRenderManager()));
	            ((Map<String, RenderPlayer>)skinMap.get(mc.getRenderManager())).put("slim", new ModRenderPlayer(mc.getRenderManager(), true));
        	}
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        
        if(ENABLE_ITEM_RENDERER)
        	mc.getRenderManager().entityRenderMap.put(EntityItem.class, new RenderEntityItem(mc.getRenderManager(), modRenderItem));
	}
}
