package liznet.bopadditions.items;

import net.minecraft.item.ItemArmor;
import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GemHelmet extends ItemArmor {

	public GemHelmet(ModMaterial material) {
		super(material.getArmorMaterial(), 0, EntityEquipmentSlot.HEAD);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_helmet");
		super.setTranslationKey("helmet" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
}