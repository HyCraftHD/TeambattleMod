package net.hycrafthd.teambattle.item;

import java.awt.Color;

import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.TeambattleReference;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTeambattleIngot extends Item {

	public ItemTeambattleIngot() {
		super();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		if (stack != null && stack.getItem() != null && stack.getItem() == TItems.teambattleingotraw) {
			return TeambattleReference.proxy.getColor();
		}
		return 16777215;
	}
}
