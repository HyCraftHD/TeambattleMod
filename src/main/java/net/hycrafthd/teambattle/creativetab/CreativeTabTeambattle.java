package net.hycrafthd.teambattle.creativetab;

import java.util.List;

import net.hycrafthd.teambattle.TBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabTeambattle extends CreativeTabs {

	public CreativeTabTeambattle() {
		super("teambattletab");
		this.setBackgroundImageName("item_search.png");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(TBlocks.teambattleore);
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}

}
