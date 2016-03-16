package net.hycrafthd.teambattle.item;

import net.hycrafthd.teambattle.TConfigs;
import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.util.MathUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemTeambattleSword extends ItemSword {

	private final ToolMaterial material;

	public ItemTeambattleSword(ToolMaterial teambattletool) {
		super(teambattletool);
		this.material = teambattletool;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (TConfigs.customSwordSound) {
			TeambattleReference.proxy.playSoundStayAtLocation(attacker.getPosition(), TeambattleReference.resource + "teambattleswordhit", 1.0F, MathUtil.getRandomFloatInRange(attacker.getRNG(), 0.7F, 1.2F));
		}
		return super.hitEntity(stack, target, attacker);
	}

}
