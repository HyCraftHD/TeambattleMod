package net.hycrafthd.teambattle.container;

import net.hycrafthd.teambattle.inventory.InventoryTeambattleBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerTeambattleBackpack extends Container {

	public InventoryTeambattleBackpack inv;
	public boolean updateNotification;

	public ContainerTeambattleBackpack(InventoryTeambattleBackpack backpackinv, InventoryPlayer playerinv) {

		updateNotification = false;
		this.inv = backpackinv;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(backpackinv, j + i * 9, j * 18 + 8, i * 18 + 18));
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(playerinv, j + i * 9 + 9, j * 18 + 8, i * 18 + 176));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(playerinv, i, i * 18 + 8, 234));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	public void saveToNBT(ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			itemStack.setTagCompound(new NBTTagCompound());
		}
		inv.writeToNBT(itemStack.getTagCompound());
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 8 * 9) {
				if (!this.mergeItemStack(itemstack1, 8 * 9, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 9 * 8, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}
		updateNotification = true;
		return itemstack;
	}

	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = (Slot) inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.isHere(playerIn.inventory, playerIn.inventory.currentItem)) {
				return tmpSlot.getStack();
			}
		}
		if (mode == 2) {
			ItemStack stack = playerIn.inventory.getStackInSlot(clickedButton);
			if (stack != null && stack == playerIn.inventory.getCurrentItem()) {
				return null;
			}
		}
		updateNotification = true;
		return super.slotClick(slotId, clickedButton, mode, playerIn);
	}

}
