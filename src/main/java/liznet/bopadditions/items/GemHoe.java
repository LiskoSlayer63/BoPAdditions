package liznet.bopadditions.items;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemHoe;

public class GemHoe extends ItemHoe implements ICustomEnchantColor {
	private String gemName;
	
	public GemHoe(ModMaterial material) {
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		String gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_hoe");
		super.setTranslationKey("hoe" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}

	@Override
	public int getEnchantColor() {
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}
}
