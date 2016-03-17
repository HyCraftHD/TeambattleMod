package net.hycrafthd.teambattle.item;

import java.util.Random;

import net.hycrafthd.teambattle.TeambattleReference;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemTeambattleBow extends ItemBow {

	public ItemTeambattleBow() {
		super();
		this.setMaxDamage(50000000);
	}

	@Override
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		ModelResourceLocation mrl = new ModelResourceLocation(TeambattleReference.resource + "teambattlebow", "inventory");
		useRemaining = getMaxItemUseDuration(stack) - useRemaining;
		if (player.getItemInUse() != null) {
			if (useRemaining >= 10) {
				mrl = new ModelResourceLocation(TeambattleReference.resource + "teambattlebow_2", "inventory");
			} else if (useRemaining > 5) {
				mrl = new ModelResourceLocation(TeambattleReference.resource + "teambattlebow_1", "inventory");
			} else if (useRemaining > 0) {
				mrl = new ModelResourceLocation(TeambattleReference.resource + "teambattlebow_0", "inventory");
			}
		}
		return mrl;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public int getItemEnchantability() {
		return 30;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
		boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

		if (flag || playerIn.inventory.hasItem(Items.arrow)) {
			int s = this.getMaxItemUseDuration(stack) - timeLeft;

			float f = s * 0.1F;

			if ((double) f < 0.2D) {
				return;
			}

			if (f > 1.0F) {
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrow(worldIn, playerIn, f * 1.5F);

			entityarrow.motionX *= 1.5;
			entityarrow.motionY *= 1.5;
			entityarrow.motionZ *= 1.5;

			if (f == 1.0F) {
				entityarrow.setIsCritical(true);
			}

			int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

			if (j > 0) {
				entityarrow.setDamage((entityarrow.getDamage() + j * 0.5) - new Random().nextFloat() * new Random().nextFloat());
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

			if (k > 0) {
				entityarrow.setKnockbackStrength(k);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack);

			if (l > 0) {
				entityarrow.setFire(20 + l * 100);
			}

			stack.damageItem(1, playerIn);
			worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag) {
				entityarrow.canBePickedUp = 2;
			} else {
				playerIn.inventory.consumeInventoryItem(Items.arrow);
			}

			playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

			if (!worldIn.isRemote) {
				worldIn.spawnEntityInWorld(entityarrow);
			}
		}
	}

}
