package liznet.bopadditions.items;

import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class GemAxe extends ItemAxe {

	public GemAxe(ModMaterial material) {
		super(material.getToolMaterial(), material.name().toLowerCase().equals("amethyst") ? 12.0F : 8.0F, -3.0F);
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_axe");
		super.setTranslationKey("axe" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
	
}
