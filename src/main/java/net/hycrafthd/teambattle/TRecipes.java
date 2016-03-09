package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TRecipes {

	public TRecipes() {
		funance();
		crafing();
	}

	private void crafing() {
		CommonRegistryUtil.registerShaplessCraftingRecipe(new ItemStack(TItems.teambattleingot, 3), new ItemStack(TItems.teambattleingotraw), new ItemStack(Items.diamond));
		CommonRegistryUtil.registerGuiRecipe(new ItemStack(TItems.teambattleingot, 3), new ItemStack(TItems.teambattleingotraw), new ItemStack(Items.diamond), null, null, null, null, null, null, null);

		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlesword), new Object[] { "i", "i", "s", 'i', TItems.teambattleingot, 's', Items.stick });
		CommonRegistryUtil.registerGuiRecipe(new ItemStack(TItems.teambattlesword), new ItemStack(TItems.teambattleingot), null, null, new ItemStack(TItems.teambattleingot), null, null, new ItemStack(Items.stick), null, null);
	
		//TODO not ready ... Not all recipes
		
	}

	private void funance() {
	}

}
