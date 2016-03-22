package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.item.ItemTeambattleArmor;
import net.hycrafthd.teambattle.item.ItemTeambattleBackpack;
import net.hycrafthd.teambattle.item.ItemTeambattleBow;
import net.hycrafthd.teambattle.item.ItemTeambattleHangglider;
import net.hycrafthd.teambattle.item.ItemTeambattleIngot;
import net.hycrafthd.teambattle.item.ItemTeambattlePickaxe;
import net.hycrafthd.teambattle.item.ItemTeambattleShovel;
import net.hycrafthd.teambattle.item.ItemTeambattleSword;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import scala.collection.generic.BitOperations.Int;

public class TItems {

	public static Item teambattleingotraw;
	public static Item teambattleingot;

	public static ToolMaterial teambattletool;
	public static ArmorMaterial teambattlearmor;

	public static Item teambattlesword;
	public static Item teambattlepickaxe;
	public static Item teambattleshovel;
	public static Item teambattlebow;

	public static Item teambattlehelmet;
	public static Item teambattlechestplate;
	public static Item teambattleleggings;
	public static Item teambattleboots;

	public static Item teambattlebackpack;
	public static Item teambattlehangglider;

	public TItems() {
		init();
		register();
	}

	private void init() {

		teambattleingotraw = new ItemTeambattleIngot().setUnlocalizedName("teambattleingotraw").setCreativeTab(TeambattleMod.tab);
		teambattleingot = new ItemTeambattleIngot().setUnlocalizedName("teambattleingot").setCreativeTab(TeambattleMod.tab);

		teambattletool = EnumHelper.addToolMaterial("TEAMBATTLE", 3, 50000000, 10.0F, 5.0F, 25);
		teambattlearmor = EnumHelper.addArmorMaterial("TEAMBATTLE", "", 3125000, new int[] { 3, 8, 6, 3 }, 25);

		teambattletool.setRepairItem(new ItemStack(teambattleingot));
		teambattlearmor.customCraftingMaterial = teambattleingot;

		teambattlesword = new ItemTeambattleSword(teambattletool).setUnlocalizedName("teambattlesword").setCreativeTab(TeambattleMod.tab);
		teambattlepickaxe = new ItemTeambattlePickaxe(teambattletool).setUnlocalizedName("teambattlepickaxe").setCreativeTab(TeambattleMod.tab);
		teambattleshovel = new ItemTeambattleShovel(teambattletool).setUnlocalizedName("teambattleshovel").setCreativeTab(TeambattleMod.tab);
		teambattlebow = new ItemTeambattleBow().setUnlocalizedName("teambattlebow").setCreativeTab(TeambattleMod.tab);

		teambattlehelmet = new ItemTeambattleArmor(teambattlearmor, 0).setUnlocalizedName("teambattlehelmet").setCreativeTab(TeambattleMod.tab);
		teambattlechestplate = new ItemTeambattleArmor(teambattlearmor, 1).setUnlocalizedName("teambattlechestplate").setCreativeTab(TeambattleMod.tab);
		teambattleleggings = new ItemTeambattleArmor(teambattlearmor, 2).setUnlocalizedName("teambattleleggings").setCreativeTab(TeambattleMod.tab);
		teambattleboots = new ItemTeambattleArmor(teambattlearmor, 3).setUnlocalizedName("teambattleboots").setCreativeTab(TeambattleMod.tab);

		teambattlebackpack = new ItemTeambattleBackpack().setUnlocalizedName("teambattlebackpack").setCreativeTab(TeambattleMod.tab);
		teambattlehangglider = new ItemTeambattleHangglider().setUnlocalizedName("teambattlehangglider").setCreativeTab(TeambattleMod.tab);
	}

	private void register() {
		CommonRegistryUtil.registerItem(teambattleingotraw);
		CommonRegistryUtil.registerItem(teambattleingot);

		CommonRegistryUtil.registerItem(teambattlesword);
		CommonRegistryUtil.registerItem(teambattlepickaxe);
		CommonRegistryUtil.registerItem(teambattleshovel);
		CommonRegistryUtil.registerItem(teambattlebow);

		CommonRegistryUtil.registerItem(teambattlehelmet);
		CommonRegistryUtil.registerItem(teambattlechestplate);
		CommonRegistryUtil.registerItem(teambattleleggings);
		CommonRegistryUtil.registerItem(teambattleboots);

		CommonRegistryUtil.registerItem(teambattlebackpack);
		CommonRegistryUtil.registerItem(teambattlehangglider);
	}

}
