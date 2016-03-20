package net.hycrafthd.teambattle.util;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientRegistryUtil {

	private static final List<CommonGuiRecipe> guirecipes = Lists.<CommonGuiRecipe> newArrayList();

	// Models
	public static void registerModelRenderer(Object obj) {
		ClientRegistryUtil.registerModelRenderer(obj, 0);
	}

	public static void registerModelRenderer(Object obj, int meta) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			ClientRegistryUtil.registerModelRenderer(item, meta, new ModelResourceLocation(TeambattleReference.resource + item.getUnlocalizedName().substring(5), "inventory"));
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	public static void registerModelRenderer(Object obj, int meta, ModelResourceLocation loc) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	// Model variants
	public static void registerItemVariants(Item item, String... names) {
		for (String name : names) {
			ModelBakery.registerItemVariants(item, new ResourceLocation(TeambattleReference.resource + name));
		}
	}

	// Entity
	public static void registerEntityRenderer(Class<? extends Entity> entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}

	// Keybinding
	public static void registerKeybinding(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}

	public static void registerGuiRecipes(ItemStack output, ItemStack... input) {
		if (input == null)
			return;
		if (output == null)
			return;
		if (input.length != 9)
			return;
		guirecipes.add(new CommonGuiRecipe(input, output));
	}

	// Recipes
	public static void addTeambattleRecipes() {
		ArrayList<CommonGuiRecipe> stacks = new ArrayList<CommonGuiRecipe>();
		List<IRecipe> re = CraftingManager.getInstance().getRecipeList();
		for (IRecipe r : re) {
			if (r.getRecipeOutput() != null && r.getRecipeOutput().getItem() != null && r.getRecipeOutput().getItem().getRegistryName().startsWith(TeambattleReference.modid)) {
				if (r instanceof ShapedRecipes) {
					ShapedRecipes rep = (ShapedRecipes) r;
					stacks.add(new CommonGuiRecipe(RecipeUtil.matches(rep), rep.getRecipeOutput()));
				} else if (r instanceof ShapelessRecipes) {
					List<ItemStack> rs = ((ShapelessRecipes) r).recipeItems;
					ItemStack[] ar = new ItemStack[rs.size()];
					for (int i = 0; i < ar.length; i++) {
						ar[i] = rs.get(i);
					}
					stacks.add(new CommonGuiRecipe(ar, r.getRecipeOutput()));
				} else {
					continue;
				}
			}
		}
		guirecipes.addAll(stacks);
	}

	public static List<CommonGuiRecipe> getGuirecipes() {
		return guirecipes;
	}
}
