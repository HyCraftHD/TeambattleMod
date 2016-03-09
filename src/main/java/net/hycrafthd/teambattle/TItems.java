package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.item.ItemTeambattleArmor;
import net.hycrafthd.teambattle.item.ItemTeambattleBackpack;
import net.hycrafthd.teambattle.item.ItemTeambattleBow;
import net.hycrafthd.teambattle.item.ItemTeambattleIngot;
import net.hycrafthd.teambattle.item.ItemTeambattlePickAxe;
import net.hycrafthd.teambattle.item.ItemTeambattleShovel;
import net.hycrafthd.teambattle.item.ItemTeambattleSword;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import scala.collection.generic.BitOperations.Int;

public class TItems {

	public static Item teambattleingotraw;
	public static Item teambattleingot;

	public static ToolMaterial teambattletool = EnumHelper.addToolMaterial("TEAMBATTLE", 3, 50000000, 10.0F, 6.0F, 30);
	public static ArmorMaterial teambattlearmor = EnumHelper.addArmorMaterial("TEAMBATTLE", "", 3125000, new int[] { 3, 8, 6, 3 }, 30);

	public static Item teambattlesword;
	public static Item teambattlepickaxe;
	public static Item teambattleshovel;
	public static Item teambattlebow;

	public static Item teambattlehelmet;
	public static Item teambattlechestplate;
	public static Item teambattleleggings;
	public static Item teambattleboots;
	
	public static Item teambattlebackpack;
	
	public TItems() {
		init();
		register();
	}

	private void init() {
		teambattleingotraw = new ItemTeambattleIngot().setUnlocalizedName("teambattleingotraw").setCreativeTab(TeamBattleMod.tab);
		teambattleingot = new ItemTeambattleIngot().setUnlocalizedName("teambattleingot").setCreativeTab(TeamBattleMod.tab);

		teambattlesword = new ItemTeambattleSword(teambattletool).setUnlocalizedName("teambattlesword").setCreativeTab(TeamBattleMod.tab);
		teambattlepickaxe = new ItemTeambattlePickAxe(teambattletool).setUnlocalizedName("teambattlepickaxe").setCreativeTab(TeamBattleMod.tab);
		teambattleshovel = new ItemTeambattleShovel(teambattletool).setUnlocalizedName("teambattleshovel").setCreativeTab(TeamBattleMod.tab);
		teambattlebow = new ItemTeambattleBow().setUnlocalizedName("teambattlebow").setCreativeTab(TeamBattleMod.tab);

		teambattlehelmet = new ItemTeambattleArmor(teambattlearmor, 0).setUnlocalizedName("teambattlehelmet").setCreativeTab(TeamBattleMod.tab);
		teambattlechestplate = new ItemTeambattleArmor(teambattlearmor, 1).setUnlocalizedName("teambattlechestplate").setCreativeTab(TeamBattleMod.tab);
		teambattleleggings = new ItemTeambattleArmor(teambattlearmor, 2).setUnlocalizedName("teambattleleggings").setCreativeTab(TeamBattleMod.tab);
		teambattleboots = new ItemTeambattleArmor(teambattlearmor, 3).setUnlocalizedName("teambattleboots").setCreativeTab(TeamBattleMod.tab);
		
		teambattlebackpack = new ItemTeambattleBackpack().setUnlocalizedName("teambattlebackpack").setCreativeTab(TeamBattleMod.tab);
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
	}

}
