package liznet.bopadditions.items;

import liznet.bopadditions.interfaces.ICustomEnchantColor;
import liznet.bopadditions.materials.ModMaterial;
import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class GemSword extends ItemSword implements ICustomEnchantColor 
{
	private String gemName;
	
	public GemSword(ModMaterial material) 
	{
		super(material.getToolMaterial());
		super.setCreativeTab(CreativeTabs.COMBAT);
		
		gemName = material.name().toLowerCase();
		
		super.setRegistryName(gemName + "_sword");
		super.setTranslationKey("sword" + gemName.substring(0, 1).toUpperCase() + gemName.substring(1));
	}

	@Override
	public int getEnchantColor() 
	{
		return ClientProxy.getEffectColor(gemName.toUpperCase());
	}
}
