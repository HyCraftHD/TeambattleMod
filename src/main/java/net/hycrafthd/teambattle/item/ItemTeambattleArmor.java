package net.hycrafthd.teambattle.item;

import net.hycrafthd.teambattle.TeambattleReference;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTeambattleArmor extends ItemArmor {

	public ItemTeambattleArmor(ArmorMaterial material, int armorType) {
		super(material, 0, armorType);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (slot == 0 || slot == 1 || slot == 3) {
			return TeambattleReference.modid + ":textures/models/armor/armor_layer_1.png";
		} else if (slot == 2) {
			return TeambattleReference.modid + ":textures/models/armor/armor_layer_2.png";
		}
		return null;
	}

}
