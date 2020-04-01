package liznet.bopadditions;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.RequiresWorldRestart;

@Config(modid = BOPAdditions.modId)
public class BOPAdditionsConfig 
{
	@Comment({
	  "Enable debugging mode.",
	  "(for development use only)"
	})
	@Name("Enable Debug")
	@RequiresMcRestart
	public static boolean DEBUG = false;
	
	@Comment({
	  "Enable custom enchantment colors on items.",
	  "(may cause conflicts)"
	})
	@Name("Enable Item Enchants")
	@RequiresMcRestart
	public static boolean ENABLE_ITEM_RENDERER = false;
	
	@Comment({
	  "Enable custom enchantment colors on armors.",
	  "(may cause conflicts)"
	})
	@Name("Enable Armor Enchants")
	@RequiresMcRestart
	public static boolean ENABLE_ARMOR_RENDERER = false;

	@Comment({
	  "Same effect than on the pumpkin, but without the filter.",
	  "(may cause conflicts)"
	})
	@Name("Enderman ignore Amethyst Helmet")
	@RequiresWorldRestart
	public static boolean ENDERMAN_IGNORE_AMETHYST_HELMET = true;
}
