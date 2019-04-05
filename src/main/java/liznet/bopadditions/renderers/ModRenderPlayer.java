package liznet.bopadditions.renderers;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class ModRenderPlayer extends RenderPlayer {

	public ModRenderPlayer(RenderManager renderManager) {
		super(renderManager, false);
		
        layerRenderers.remove(0);
        addLayer(new ModLayerBipedArmor(this));
	}
	
	public ModRenderPlayer(RenderManager renderManager, boolean b)
    {
        super(renderManager, b);
        
        layerRenderers.remove(0);
        addLayer(new ModLayerBipedArmor(this));
    }
}
