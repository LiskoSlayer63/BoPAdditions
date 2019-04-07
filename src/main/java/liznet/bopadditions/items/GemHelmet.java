package liznet.bopadditions.items;

import net.minecraft.item.ItemArmor;
import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GemHelmet extends ItemArmor implements ICustomEnchantColor {
	private String gemName;
	
	public GemHelmet(ModMaterial material) {
		super(material.getArmorMaterial(), 0, EntityEquipmentSlot.HEAD);
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_helmet");
		super.setTranslationKey("helmet" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}

	@Override
	public int getEnchantColor() {
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}
}