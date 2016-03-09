package net.hycrafthd.teambattle.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommonGuiRecipe {

	private final ItemStack[] recipeInput;
	private final ItemStack recipeOutput;

	public CommonGuiRecipe(ItemStack[] input, ItemStack output) {
		this.recipeInput = input;
		this.recipeOutput = output;
	}

	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}

	public ItemStack[] getRecipeInput() {
		return recipeInput;
	}
}