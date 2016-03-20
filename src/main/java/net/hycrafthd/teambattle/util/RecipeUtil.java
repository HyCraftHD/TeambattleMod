package net.hycrafthd.teambattle.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;

public class RecipeUtil {

	public static ItemStack[] matches(ShapedRecipes re) {
		for (int i = 0; i <= 3 - re.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - re.recipeHeight; ++j) {
				ItemStack[] st = checkMatch(re, i, j, true);
				if (st != null) {
					return st;
				}
				ItemStack[] st2 = checkMatch(re, i, j, false);
				if (st2 != null) {
					return st2;
				}
			}
		}

		return null;
	}

	private static ItemStack[] checkMatch(ShapedRecipes re, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		ItemStack[] stacks = new ItemStack[9];

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				int k = i - p_77573_2_;
				int l = j - p_77573_3_;
				ItemStack itemstack = null;

				if (k >= 0 && l >= 0 && k < re.recipeWidth && l < re.recipeHeight) {
					if (p_77573_4_) {
						itemstack = re.recipeItems[re.recipeWidth - k - 1 + l * re.recipeWidth];
					} else {
						itemstack = re.recipeItems[k + l * re.recipeWidth];
					}
				}
				stacks[(i + (j * 3))] = itemstack;
			}
		}
		return stacks;
	}
}
