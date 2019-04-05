package liznet.bopadditions.items;

import net.minecraft.item.ItemArmor;
import liznet.bopadditions.materials.ModMaterial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GemChestplate extends ItemArmor {

	public GemChestplate(ModMaterial material) {
		super(material.getArmorMaterial(), 0, EntityEquipmentSlot.CHEST);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_chestplate");
		super.setTranslationKey("chestplate" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}
}