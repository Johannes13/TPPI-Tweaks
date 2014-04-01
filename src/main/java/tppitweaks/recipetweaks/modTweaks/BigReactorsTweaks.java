package tppitweaks.recipetweaks.modTweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tppitweaks.config.ConfigurationHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BigReactorsTweaks {
	
	public static void init()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			TweakerBase.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).blockID, 0);
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			TweakerBase.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod).blockID, -1);
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			TweakerBase.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass).blockID, 0);
			TweakerBase.markItemForRecipeRemoval(((Block) erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass).blockID, 1);
		}
	}
	
	public static void addRecipes()
	{
		if (ConfigurationHandler.steelReactorCasings)
		{
			ItemStack reactorPartStack = ((erogenousbeef.bigreactors.common.multiblock.block.BlockReactorPart) erogenousbeef.bigreactors.common.BigReactors.blockReactorPart).getReactorCasingItemStack();
			reactorPartStack.stackSize = 4;
			GameRegistry.addRecipe(new ShapedOreRecipe(reactorPartStack, new Object[] { "ICI", "CUC", "ICI", 'I', "ingotSteel", 'C', "ingotGraphite", 'U', "ingotYellorium" }));
		}
		if (ConfigurationHandler.glassFuelRods)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockYelloriumFuelRod, 1), new Object[] { "ICI", "GUG", "ICI", Character.valueOf('I'),
				erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, Character.valueOf('C'), "ingotIron", Character.valueOf('U'), "ingotYellorium", Character.valueOf('G'), "ingotGraphite" }));
		}
		if (ConfigurationHandler.twoReactorGlass)
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 0, 2),
			"gCg",
			
			'g', "glassHardened",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 0, 2),
			"gCg",
			
			'g', "glassReinforced",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockReactorPart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 1, 2),
			"gCg",
			
			'g', "glassHardened",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockTurbinePart, 1, 0)));
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockMultiblockGlass, 1, 2),
			"gCg",
			
			'g', "glassReinforced",
			'C', new ItemStack(erogenousbeef.bigreactors.common.BigReactors.blockTurbinePart, 1, 0)));
		}
	}
}
