package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.block.BlockTeambattleOre;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TBlocks {

	public static Block teambattleore;

	public TBlocks() {
		init();
		register();
	}

	private void init() {
		teambattleore = new BlockTeambattleOre().setUnlocalizedName("teambattleore").setCreativeTab(TeamBattleMod.tab);
	}

	private void register() {
		CommonRegistryUtil.registerBlock(teambattleore);
	}

}
