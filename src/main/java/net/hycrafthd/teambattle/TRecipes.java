package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class TRecipes {

	public TRecipes() {
		funance();
		crafing();
	}

	private void crafing() {
		CommonRegistryUtil.registerShaplessCraftingRecipe(new ItemStack(TItems.teambattleingot, 3), new ItemStack(TItems.teambattleingotraw), new ItemStack(Items.diamond));
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlesword), new Object[] { "i", "i", "s", 'i', TItems.teambattleingot, 's', Items.stick });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlepickaxe), new Object[] { "iii", "#s#", "#s#", 'i', TItems.teambattleingot, 's', Items.stick });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattleshovel), new Object[] { "i", "s", "s", 'i', TItems.teambattleingot, 's', Items.stick });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlebow), new Object[] { "si#", "s#i", "si#", 'i', TItems.teambattleingot, 's', Items.string });
		String[][] armorpattern = new String[][] { { "iii", "i#i" }, { "i#i", "iii", "iii" }, { "iii", "i#i", "i#i" }, { "i#i", "i#i" } };
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlehelmet), new Object[] { armorpattern[0], 'i', TItems.teambattleingot });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlechestplate), new Object[] { armorpattern[1], 'i', TItems.teambattleingot });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattleleggings), new Object[] { armorpattern[2], 'i', TItems.teambattleingot });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattleboots), new Object[] { armorpattern[3], 'i', TItems.teambattleingot });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlebackpack), new Object[] { "iii", "isi", "iii", 'i', TItems.teambattleingot, 's', Items.string });
		CommonRegistryUtil.registerShapedCraftingRecipe(new ItemStack(TItems.teambattlehangglider), new Object[] { "iww", "iiw", "iii", 'i', TItems.teambattleingot, 'w', Blocks.wool });

		// CommonRegistryUtil.registerGuiRecipe(new
		// ItemStack(TItems.teambattlesword), new
		// ItemStack(TItems.teambattleingot), null, null, new
		// ItemStack(TItems.teambattleingot), null, null, new
		// ItemStack(Items.stick), null, null);
		// CommonRegistryUtil.registerGuiRecipe(new
		// ItemStack(TItems.teambattleingot, 3), new
		// ItemStack(TItems.teambattleingotraw), new ItemStack(Items.diamond),
		// null, null, null, null, null, null, null);
		//
		// //TODO not ready ... Not all recipes

	}

	private void funance() {
	}

}
