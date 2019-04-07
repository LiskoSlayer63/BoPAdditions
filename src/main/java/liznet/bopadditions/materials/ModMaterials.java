package liznet.bopadditions.materials;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import java.util.ArrayList;
import java.util.List;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.item.*;
import liznet.bopadditions.logging.Logger;

public class ModMaterials 
{
	private static List<ModMaterial> MATERIALS = new ArrayList<ModMaterial>();
	
	public static ModMaterial[] getMaterials()
	{
		return MATERIALS.toArray(new ModMaterial[MATERIALS.size()]);
	}
	
	public static void init()
	{
		NonNullList<ItemStack> gems = NonNullList.create();
		BOPItems.gem.getSubItems(BOPItems.gem.getCreativeTab(), gems);
		
		
		// Build MATERIALS
		
		ModMaterial AMBER = new ModMaterial("AMBER");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		AMBER.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		AMBER.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems amberGem = BOPGems.valueOf(AMBER.name());
		AMBER.setRepairItem(gems.get(amberGem.ordinal()));
		
		MATERIALS.add(AMBER);
		
		
		ModMaterial AMETHYST = new ModMaterial("AMETHYST");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		AMETHYST.setToolMaterial(3, 2342, 20.0F, 7.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		AMETHYST.setArmorMaterial(16, new int[]{6, 12, 16, 6}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5);
		BOPGems amethystGem = BOPGems.valueOf(AMETHYST.name());
		AMETHYST.setRepairItem(gems.get(amethystGem.ordinal()));
		
		MATERIALS.add(AMETHYST);
		
		
		ModMaterial EMERALD = new ModMaterial("EMERALD");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		EMERALD.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		EMERALD.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		EMERALD.setRepairItem(new ItemStack(Items.EMERALD));

		MATERIALS.add(EMERALD);
		
		
		ModMaterial MALACHITE = new ModMaterial("MALACHITE");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		MALACHITE.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		MALACHITE.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems malachiteGem = BOPGems.valueOf(MALACHITE.name());
		MALACHITE.setRepairItem(gems.get(malachiteGem.ordinal()));
		
		MATERIALS.add(MALACHITE);
		
		
		ModMaterial PERIDOT = new ModMaterial("PERIDOT");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		PERIDOT.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		PERIDOT.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems peridotGem = BOPGems.valueOf(PERIDOT.name());
		PERIDOT.setRepairItem(gems.get(peridotGem.ordinal()));
		
		MATERIALS.add(PERIDOT);
		
		
		ModMaterial RUBY = new ModMaterial("RUBY");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		RUBY.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		RUBY.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems rubyGem = BOPGems.valueOf(RUBY.name());
		RUBY.setRepairItem(gems.get(rubyGem.ordinal()));
		
		MATERIALS.add(RUBY);
		
		
		ModMaterial SAPPHIRE = new ModMaterial("SAPPHIRE");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		SAPPHIRE.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		SAPPHIRE.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems sapphireGem = BOPGems.valueOf(SAPPHIRE.name());
		SAPPHIRE.setRepairItem(gems.get(sapphireGem.ordinal()));
		
		MATERIALS.add(SAPPHIRE);
		
		
		ModMaterial TANZANITE = new ModMaterial("TANZANITE");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		TANZANITE.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		TANZANITE.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems tanzaniteGem = BOPGems.valueOf(TANZANITE.name());
		TANZANITE.setRepairItem(gems.get(tanzaniteGem.ordinal()));
		
		MATERIALS.add(TANZANITE);
		
		
		ModMaterial TOPAZ = new ModMaterial("TOPAZ");
		// harvestLevel, maxUses, efficiency, damage, enchantability)
		TOPAZ.setToolMaterial(3, 1561, 12.0F, 3.0F, 22);
		// durability, reductionAmounts { feet, legs, body, head }, enchantability, soundOnEquip, toughness)
		TOPAZ.setArmorMaterial(16, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2);
		BOPGems topazGem = BOPGems.valueOf(TOPAZ.name());
		TOPAZ.setRepairItem(gems.get(topazGem.ordinal()));
		
		MATERIALS.add(TOPAZ);
		
		Logger.debug(MATERIALS.size() + " Materials created!");
	}
}
