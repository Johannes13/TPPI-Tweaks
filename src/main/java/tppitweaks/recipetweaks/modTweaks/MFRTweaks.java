package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.setup.Machine;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.recipetweaks.RecipeAddition;
import tppitweaks.recipetweaks.RecipeRemoval;
import tppitweaks.recipetweaks.TweakingRegistry;
import tppitweaks.recipetweaks.TweakingRegistry.TweakingAction;
import cpw.mods.fml.common.registry.GameRegistry;

public class MFRTweaks
{
	@RecipeRemoval(requiredModids="MineFactoryReloaded")
	public static void init()
	{
		if (ConfigurationHandler.buffUnifierRecipe)
			TweakingRegistry.markItemForRecipeRemoval(Machine.Unifier.getBlockId(), 8, TweakingAction.CHANGED, "Cheapened for use", "in JABBA barrels");
	}
	
	@RecipeAddition(requiredModids="MineFactoryReloaded")
	public static void addRecipes()
	{
		if (ConfigurationHandler.buffUnifierRecipe)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MineFactoryReloadedCore.machineBaseItem, 3), 
					"RRR",
					"SSS",

					'R', "sheetPlastic",
					'S', "stone"
					));

			GameRegistry.addRecipe(new ShapedOreRecipe(Machine.Unifier.getItemStack(), 
					"RRR",
					"rCr",
					" M ", 

					'R', "sheetPlastic",
					'r', Item.redstone,
					'C', Item.comparator,
					'M', MineFactoryReloadedCore.machineBaseItem
					));
		}
	}
}
