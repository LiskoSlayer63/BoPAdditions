package liznet.bopadditions.items;

import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class GemSpade extends ItemSpade {
	
	public GemSpade(ModMaterial material) {
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_shovel");
		super.setTranslationKey("shovel" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
	
}
