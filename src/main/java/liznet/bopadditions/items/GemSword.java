package liznet.bopadditions.items;

import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class GemSword extends ItemSword {

	public GemSword(ModMaterial material) {
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_sword");
		super.setTranslationKey("sword" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
	
}
