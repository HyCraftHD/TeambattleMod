package net.hycrafthd.teambattle.item;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemTeambattleSword extends ItemSword {

	private final ToolMaterial material;
	
	public ItemTeambattleSword(ToolMaterial teambattletool) {
		super(teambattletool);
		this.material = teambattletool;
	}

	public int getItemEnchantability() {
		return this.material.getEnchantability();
	}

}
