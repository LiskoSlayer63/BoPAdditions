package liznet.bopadditions.items;

import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class GemPickaxe extends ItemPickaxe {

	public GemPickaxe(ModMaterial material) {
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_pickaxe");
		super.setTranslationKey("pickaxe" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
}
