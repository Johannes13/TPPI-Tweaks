package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class TETweaks {

	public static void init()
	{
		TweakerBase.markItemForRecipeRemoval(thermalexpansion.block.TEBlocks.blockDevice.blockID, 2);
	}
	
	public static void addRecipes() {
		try {
			GameRegistry.addShapelessRecipe(new ItemStack(Item.paper, 3), new Object[] {thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips, thermalexpansion.item.TEItems.woodchips});
		}catch(Throwable t) {}
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(thermalexpansion.block.TEBlocks.blockDevice.blockID, 1, 2), new Object[]{
			"scs",
			"tpt",
			"sns",
			
			's', "ingotSteel",
			'p', Block.pistonBase,
			't', "ingotTin",
			'c', Block.chest,
			'n', thermalexpansion.item.TEItems.pneumaticServo
		}));
		
		if (OreDictionary.getOres("dustRuby").size() != 0)
			thermalexpansion.util.crafting.PulverizerManager.addIngotNameToDustRecipe(2400, "gemRuby", OreDictionary.getOres("dustRuby").get(0));
	}
	
	public static ItemStack getEnderium()
	{
		return thermalexpansion.item.TEItems.ingotEnderium.copy();
	}

	public static ItemStack getResonantCell()
	{
		return thermalexpansion.block.energycell.BlockEnergyCell.cellResonant.copy();
	}
}
