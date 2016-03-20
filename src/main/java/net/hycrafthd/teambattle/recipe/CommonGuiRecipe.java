package net.hycrafthd.teambattle.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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