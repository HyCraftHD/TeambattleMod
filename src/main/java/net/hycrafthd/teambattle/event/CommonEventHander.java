package net.hycrafthd.teambattle.event;

import net.hycrafthd.teambattle.item.ItemTeambattleArmor;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHander {

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		if (!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.entityLiving;

		int i = 0;
		for (int j = 0; j < player.inventory.armorInventory.length; ++j) {
			if (player.inventory.armorInventory[j] != null && player.inventory.armorInventory[j].getItem() instanceof ItemTeambattleArmor) {
				int k = ((ItemTeambattleArmor) player.inventory.armorInventory[j].getItem()).damageReduceAmount;
				i += k;
			}
		}

		player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0F + i);
		if (player.getHealth() > player.getMaxHealth()) {
			player.setHealth(player.getMaxHealth());
		}

	}

	// @SubscribeEvent
	// public void onCraftingReg(PlayerEvent.ItemCraftedEvent craft) {
	// File fl = new File("D:/Recepies.txt");
	// try {
	// fl.createNewFile();
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// FileWriter wr;
	// try {
	// wr = new FileWriter(fl);
	// for (CommonGuiRecipe ar : CommonRegistryUtil.shagedrecipes()) {
	// wr.write("Recepie" + String.format("%n"));
	// for (ItemStack st : ar.getRecipeInput()) {
	// if (st != null) {
	// wr.write(String.valueOf(st) + String.format("%n"));
	// } else {
	// wr.write("null" + String.format("%n"));
	// }
	// }
	// wr.flush();
	// }
	// wr.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
