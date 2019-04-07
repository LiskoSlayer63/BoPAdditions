package liznet.bopadditions.items;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class GemAxe extends ItemAxe implements ICustomEnchantColor 
{
	private String gemName;

	public GemAxe(ModMaterial material) 
	{
		super(material.getToolMaterial(), material.name().toLowerCase().equals("amethyst") ? 12.0F : 8.0F, -3.0F);
		super.setCreativeTab(CreativeTabs.TOOLS);
		
		gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_axe");
		super.setTranslationKey("axe" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}

	@Override
	public int getEnchantColor() 
	{
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}
}
