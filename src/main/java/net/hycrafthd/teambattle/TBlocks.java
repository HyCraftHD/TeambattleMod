package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.block.BlockTeambattleAnvil;
import net.hycrafthd.teambattle.block.BlockTeambattleOre;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.block.Block;

public class TBlocks {

	public static Block teambattleore;
	public static Block teambattleanvil;

	public TBlocks() {
		init();
		register();
	}

	private void init() {
		teambattleore = new BlockTeambattleOre().setUnlocalizedName("teambattleore").setCreativeTab(TeambattleMod.tab);
		teambattleanvil = new BlockTeambattleAnvil().setUnlocalizedName("teambattleanvil").setCreativeTab(TeambattleMod.tab);
	}

	private void register() {
		CommonRegistryUtil.registerBlock(teambattleore);
		CommonRegistryUtil.registerBlock(teambattleanvil);
	}

}
