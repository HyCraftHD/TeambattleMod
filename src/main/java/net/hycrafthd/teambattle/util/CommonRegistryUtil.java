package net.hycrafthd.teambattle.util;

import java.util.List;

import com.google.common.collect.Lists;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
		if(input.length != 9)
			return;
		guirecipes.add(new CommonGuiRecipe(input, output));
	}
	
	public static List<CommonGuiRecipe> getGuirecipes() {
		return guirecipes;
	}

}
