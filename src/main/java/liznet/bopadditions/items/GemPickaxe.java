package liznet.bopadditions.items;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class GemPickaxe extends ItemPickaxe implements ICustomEnchantColor {
	private String gemName;
	
	public GemPickaxe(ModMaterial material) {
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_pickaxe");
		super.setTranslationKey("pickaxe" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}

	@Override
	public int getEnchantColor() {
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}
}
