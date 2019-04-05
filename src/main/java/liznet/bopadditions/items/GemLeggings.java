package liznet.bopadditions.items;

import net.minecraft.item.ItemArmor;
import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GemLeggings extends ItemArmor {

	public GemLeggings(ModMaterial material) {
		super(material.getArmorMaterial(), 0, EntityEquipmentSlot.LEGS);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_leggings");
		super.setTranslationKey("leggings" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
}