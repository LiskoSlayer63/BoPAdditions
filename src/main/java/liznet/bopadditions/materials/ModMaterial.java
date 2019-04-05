package liznet.bopadditions.materials;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import liznet.bopadditions.BOPAdditions;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterial {
	private String name;
	private ToolMaterial toolMaterial = null;
	private ArmorMaterial armorMaterial = null;
	private ItemStack repairItem = new ItemStack(Items.AIR);

	public ModMaterial(String name){
		this.name = name;
	}
	
	public boolean setToolMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability){
		if(toolMaterial == null){
			toolMaterial = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
			return true;
		}
		return false;
	}
	
	public boolean setArmorMaterial(int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness){
		if(armorMaterial == null){
			armorMaterial = EnumHelper.addArmorMaterial(name, BOPAdditions.modId + ":" + name, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
			return true;
		}
		return false;
	}
	
	public void setRepairItem(ItemStack stack){
		toolMaterial.setRepairItem(stack);
		armorMaterial.setRepairItem(stack);
		repairItem = stack;
	}
	
	public String name(){
		return name;
	}
	
	public ToolMaterial getToolMaterial(){
		return toolMaterial;
	}
	
	public ArmorMaterial getArmorMaterial(){
		return armorMaterial;
	}
	
	public ItemStack getRepairItemStack(){
		return repairItem;
	}
}
