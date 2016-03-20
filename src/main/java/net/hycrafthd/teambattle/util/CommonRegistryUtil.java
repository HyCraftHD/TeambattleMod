package net.hycrafthd.teambattle.util;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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
			} else if (r instanceof ShapelessOreRecipe) {
				List<Object> rs = ((ShapelessOreRecipe) r).getInput();
				ItemStack[] ar = new ItemStack[rs.size()];
				for (int i = 0; i < ar.length; i++) {
					if (rs.get(i) instanceof ItemStack) {
						ar[i] = (((ItemStack) rs.get(i)).copy());
					} else if (rs.get(i) instanceof Item) {
						ar[i] = (new ItemStack((Item) rs.get(i)));
					} else if (rs.get(i) instanceof Block) {
						ar[i] = (new ItemStack((Block) rs.get(i)));
					} else if (rs.get(i) instanceof String) {
						ar[i] = OreDictionary.getOres((String) rs.get(i)).get(0);
					} else {
						ar[i] = null;
					}
				}
				stacks.add(new CommonGuiRecipe(ar, r.getRecipeOutput()));
			} else if (r instanceof ShapedOreRecipe) {
				try {
					ShapedOreRecipe rep = (ShapedOreRecipe) r;
					stacks.add(new CommonGuiRecipe(matches4(rep), rep.getRecipeOutput()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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

	public static ItemStack[] matches4(ShapedOreRecipe re) {
		int width = (Integer) getField(ShapedOreRecipe.class, re, "width");
		int height = (Integer) getField(ShapedOreRecipe.class, re, "height");
		for (int x = 0; x <= 3 - width; x++) {
			for (int y = 0; y <= 3 - height; ++y) {
				ItemStack[] st = checkMatch4(re, x, y, false);
				if (st != null) {
					return st;
				}

				ItemStack[] st2 = checkMatch4(re, x, y, true);
				if (st2 != null) {
					return st2;
				}
			}
		}
		return null;

	}

	public static ItemStack[] checkMatch4(ShapedOreRecipe re, int startX, int startY, boolean mirror) {
		ItemStack[] stacks = new ItemStack[9];
		int width = (Integer) getField(ShapedOreRecipe.class, re, "width");
		int height = (Integer) getField(ShapedOreRecipe.class, re, "height");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int subX = x - startX;
				int subY = y - startY;
				ItemStack itemstack;
				Object target = null;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
					if (mirror) {
						target = re.getInput()[width - subX - 1 + subY * width];
					} else {
						target = re.getInput()[subX + subY * width];
					}
				}

				if (target instanceof ItemStack) {
					itemstack = (((ItemStack) target).copy());
				} else if (target instanceof Item) {
					itemstack = (new ItemStack((Item) target));
				} else if (target instanceof Block) {
					itemstack = (new ItemStack((Block) target));
				} else if (target instanceof String) {
					itemstack = OreDictionary.getOres((String) target).get(0);
				} else {
					itemstack = null;
				}
				stacks[(x + (y * 3))] = itemstack;

				/*
				 * if (target instanceof ItemStack) { if
				 * (!OreDictionary.itemMatches((ItemStack) target, slot, false))
				 * { return false; } } else if (target instanceof List) {
				 * boolean matched = false;
				 * 
				 * Iterator<ItemStack> itr = ((List<ItemStack>)
				 * target).iterator(); while (itr.hasNext() && !matched) {
				 * matched = OreDictionary.itemMatches(itr.next(), slot, false);
				 * }
				 * 
				 * if (!matched) { return false; } } else if (target == null &&
				 * slot != null) { return false; }
				 */
			}
		}

		return stacks;
	}

	// public static ItemStack[] matches4(ShapedOreRecipe re) {
	// int width = (Integer) getField(ShapedOreRecipe.class, re, "width");
	// int height = (Integer) getField(ShapedOreRecipe.class, re, "height");
	// for (int i = 0; i <= 3 - width; ++i) {
	// for (int j = 0; j <= 3 - height; ++j) {
	// ItemStack[] st = checkMatch4(re, i, j, true);
	// if (st != null) {
	// return st;
	// }
	//
	// ItemStack[] st2 = checkMatch4(re, i, j, false);
	// if (st2 != null) {
	// return st2;
	// }
	// }
	// }
	//
	// return null;
	// }
	//
	// /**
	// * Checks if the region of a crafting inventory is match for the recipe.
	// */
	// private static ItemStack[] checkMatch4(ShapedOreRecipe re, int
	// p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
	// ItemStack[] stacks = new ItemStack[9];
	//
	// int width = (Integer) getField(ShapedOreRecipe.class, re, "width");
	// int height = (Integer) getField(ShapedOreRecipe.class, re, "height");
	//
	// for (int i = 0; i < 3; ++i) {
	// for (int j = 0; j < 3; ++j) {
	// int k = i - p_77573_2_;
	// int l = j - p_77573_3_;
	// ItemStack itemstack = null;
	//
	// if (k >= 0 && l >= 0 && k < width && l < height) {
	// System.out.println(re.getRecipeOutput() + " : " + (width - k - 1 + l *
	// height) + " : " + re.getInput().length);
	// /*if (p_77573_4_) {
	//
	// Object obj = re.getInput()[width - k - 1 + l * height];
	//
	//
	// if (obj instanceof ItemStack) {
	// itemstack = (((ItemStack) obj).copy());
	// } else if (obj instanceof Item) {
	// itemstack = (new ItemStack((Item) obj));
	// } else if (obj instanceof Block) {
	// itemstack = (new ItemStack((Block) obj));
	// } else if (obj instanceof String) {
	// itemstack = OreDictionary.getOres((String) obj).get(0);
	// } else {
	// itemstack = null;
	// }
	// } else {
	// Object obj = re.getInput()[k + l * width];
	//
	// if (obj instanceof ItemStack) {
	// itemstack = (((ItemStack) obj).copy());
	// } else if (obj instanceof Item) {
	// itemstack = (new ItemStack((Item) obj));
	// } else if (obj instanceof Block) {
	// itemstack = (new ItemStack((Block) obj));
	// } else if (obj instanceof String) {
	// itemstack = OreDictionary.getOres((String) obj).get(0);
	// } else {
	// itemstack = null;
	// }
	// }*/
	// }
	//
	// stacks[(i + (j * 3))] = itemstack;
	// }
	// }
	// return stacks;
	// }

	// public static ItemStack[] matches4(ShapedOreRecipe inv) {
	// int width = (Integer)getField(ShapedOreRecipe.class, inv, "width");
	// int height = (Integer)getField(ShapedOreRecipe.class, inv, "height");
	// for (int x = 0; x <= inv.MAX_CRAFT_GRID_WIDTH - width; x++) {
	// for (int y = 0; y <= inv.MAX_CRAFT_GRID_HEIGHT - height; ++y) {
	// System.out.println(inv.getRecipeOutput() + " : " + width + " : " + height
	// + " : ");
	// for(Object obj : inv.getInput()) {
	// System.out.println(obj);
	// }
	// /*ItemStack[] st = checkMatch4(inv, x, y, false);
	// if (st != null) {
	// return st;
	// }
	//
	// ItemStack[] sts = checkMatch4(inv, x, y, true);
	// if (sts != null) {
	// return sts;
	// }*/
	// }
	// }
	//
	// return null;
	// }
	//
	// public static ItemStack[] checkMatch4(ShapedOreRecipe inv, int startX,
	// int startY, boolean mirror) {
	// ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
	// for (int x = 0; x < inv.MAX_CRAFT_GRID_WIDTH; x++) {
	// for (int y = 0; y < inv.MAX_CRAFT_GRID_HEIGHT; y++) {
	// int subX = x - startX;
	// int subY = y - startY;
	// Object target = null;
	//
	// if (subX >= 0 && subY >= 0 && subX < inv.getRecipeSize() && subY <
	// inv.getRecipeSize()) {
	// if (mirror) {
	// target = inv.getInput()[inv.getRecipeSize() - subX - 1 + subY *
	// inv.getRecipeSize()];
	// } else {
	// int with = (Integer)getField(ShapedOreRecipe.class, inv, "width");
	// System.out.println(inv.getRecipeOutput() + " : " + with + " : " +
	// inv.getInput().length + " : " +(subX + subY * with) + " : " +
	// inv.getInput().length);
	// //target = inv.getInput()[subX + subY * with];
	// }
	// }
	//
	// if (target instanceof ItemStack) {
	// stack.add((ItemStack) target);
	// } else if (target instanceof List) {
	//
	// Iterator<ItemStack> itr = ((List<ItemStack>) target).iterator();
	// while (itr.hasNext()) {
	// stack.add(itr.next());
	// }
	// }
	// }
	// }
	//
	// ItemStack[] sta = new ItemStack[stack.size()];
	// int v = 0;
	// for (ItemStack st : stack) {
	// sta[v] = st;
	// v++;
	// }
	// return sta;
	// }

	private static Object getField(Class clazz, Object obj, String fieldstr) {
		try {
			Field field = clazz.getDeclaredField(fieldstr);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception ex) {
			return null;
		}
	}

	public static List<CommonGuiRecipe> getGuirecipes() {
		return guirecipes;
	}

}
