package liznet.bopadditions.items;

import net.minecraft.item.ItemArmor;
import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GemBoots extends ItemArmor {

	public GemBoots(ModMaterial material) {
		super(material.getArmorMaterial(), 0, EntityEquipmentSlot.FEET);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_boots");
		super.setTranslationKey("boots" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
}