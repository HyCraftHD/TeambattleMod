package net.hycrafthd.teambattle.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonRegistryUtil {

	private static final List<CommonGuiRecipe> guirecipes = Lists.<CommonGuiRecipe> newArrayList();

	// Blocks
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}

	// Crafting
	public static void registerCraftingRecipe(ItemStack output, Object... obj) {
		CommonRegistryUtil.registerShapedCraftingRecipe(output, obj);
	}

	public static void registerShapedCraftingRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapedRecipe(output, obj);
	}

	public static void registerShaplessCraftingRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapelessRecipe(output, obj);
	}

	// Entity
	public static void registerEntity(Class entityClass, String entityName, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int solidColor, int spotColor, boolean hasSpawnEgg) {
		EntityRegistry.registerModEntity(entityClass, entityName, id, TeambattleReference.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		if (hasSpawnEgg) {
			EntityRegistry.registerEgg(entityClass, solidColor, spotColor);
		}
	}

	// Gui Handler
	public static void registerGuiHandler(IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(TeambattleReference.instance, handler);
	}

	// Items
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	// Oredirectionary
	public static void registerOredirectionary(Block block) {
		OreDictionary.registerOre(block.getUnlocalizedName().substring(5), block);
	}

	public static void registerOredirectionary(Item item) {
		OreDictionary.registerOre(item.getUnlocalizedName().substring(5), item);
	}

	// Smelting
	public static void registerSmelting(Block input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void registerSmelting(Item input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void registerSmelting(ItemStack input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	// TileEntity
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

	// Worldgenerator
	public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
		GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
	}

	// GuiRecipes
	public static void registerGuiRecipe(ItemStack output, ItemStack input0, ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack input7, ItemStack input8) {
		registerGuiRecipes(output, input0, input1, input2, input3, input4, input5, input6, input7, input8);
	}

	private static void registerGuiRecipes(ItemStack output, ItemStack... input) {
		if (input == null)
			return;
		if (output == null)
			return;
		if (input.length != 9)
			return;
		guirecipes.add(new CommonGuiRecipe(input, output));
	}

	public static ArrayList<CommonGuiRecipe> shagedrecipes() {
		ArrayList<CommonGuiRecipe> stacks = new ArrayList<CommonGuiRecipe>();
		List<IRecipe> re = CraftingManager.getInstance().getRecipeList();
		for (IRecipe r : re) {
			if (r instanceof ShapedRecipes) {
				ShapedRecipes rep = (ShapedRecipes) r;
				stacks.add(new CommonGuiRecipe(matches(rep), rep.getRecipeOutput()));
			} else if (r instanceof ShapelessRecipes) {
				List<ItemStack> rs = ((ShapelessRecipes) r).recipeItems;
				ItemStack[] ar = new ItemStack[rs.size()];
				for (int i = 0; i < ar.length; i++) {
					ar[i] = rs.get(i);
				}
				stacks.add(new CommonGuiRecipe(ar, r.getRecipeOutput()));
			} else if (r instanceof ShapedOreRecipe) {
				ShapedOreRecipe rep = (ShapedOreRecipe) r;
				stacks.add(new CommonGuiRecipe(matches4(rep), rep.getRecipeOutput()));
			}
		}
		return stacks;
	}

	public static ItemStack[] matches(ShapedRecipes re) {
		for (int i = 0; i <= 3 - re.recipeWidth; ++i) {
			for (int j = 0; j <= 3 - re.recipeHeight; ++j) {
				ItemStack[] st = checkMatch(re, i, j, true);
				if (st != null) {
					return st;
				}

				ItemStack[] st2 = checkMatch(re, i, j, true);
				if (st2 != null) {
					return st2;
				}
			}
		}

		return null;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
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

	public static ItemStack[] matches4(ShapedOreRecipe inv) {
		for (int x = 0; x <= inv.MAX_CRAFT_GRID_WIDTH - inv.getRecipeSize(); x++) {
			for (int y = 0; y <= inv.MAX_CRAFT_GRID_HEIGHT - inv.getRecipeSize(); ++y) {
				ItemStack[] st = checkMatch4(inv, x, y, false);
				if (st != null) {
					return st;
				}

				ItemStack[] sts = checkMatch4(inv, x, y, true);
				if (sts != null) {
					return sts;
				}
			}
		}

		return null;
	}

	public static ItemStack[] checkMatch4(ShapedOreRecipe inv, int startX, int startY, boolean mirror) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		for (int x = 0; x < inv.MAX_CRAFT_GRID_WIDTH; x++) {
			for (int y = 0; y < inv.MAX_CRAFT_GRID_HEIGHT; y++) {
				int subX = x - startX;
				int subY = y - startY;
				Object target = null;

				if (subX >= 0 && subY >= 0 && subX < inv.getRecipeSize() && subY < inv.getRecipeSize()) {
					if (mirror) {
						target = inv.getInput()[inv.getRecipeSize() - subX - 1 + subY * inv.getRecipeSize()];
					} else {
						target = inv.getInput()[subX + subY * inv.getRecipeSize()];
					}
				}

				if (target instanceof ItemStack) {
					stack.add((ItemStack) target);
				} else if (target instanceof List) {

					Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
					while (itr.hasNext()) {
						stack.add(itr.next());
					}
				}
			}
		}

		ItemStack[] sta = new ItemStack[stack.size()];
		int v = 0;
		for (ItemStack st : stack) {
			sta[v] = st;
			v++;
		}
		return sta;
	}

	public static List<CommonGuiRecipe> getGuirecipes() {
		return guirecipes;
	}

}
