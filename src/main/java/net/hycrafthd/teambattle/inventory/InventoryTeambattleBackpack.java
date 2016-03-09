package net.hycrafthd.teambattle.inventory;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class InventoryTeambattleBackpack extends InventoryBackpackBase {

	ItemStack stack = null;

	public InventoryTeambattleBackpack(ItemStack itemStack, EntityPlayer player) {
		super(itemStack, player, 8 * 9);
		if (!itemStack.hasTagCompound()) {
			itemStack.setTagCompound(new NBTTagCompound());
		}
		size = 8 * 9;

		inv = new ItemStack[size];

		stack = itemStack;

		readFromNBT(itemStack.getTagCompound());
	}

	@Override
	public String getName() {
		return stack != null ? stack.getDisplayName() : "Backpack";
	}

	@Override
	public void readFromNBT(NBTTagCompound myCompound) {
		NBTTagCompound contentTag = ((NBTTagCompound) myCompound.getTag("inventory"));
		if (contentTag == null) {
			return;
		}

		NBTTagList myList = contentTag.getTagList("items", 10);
		for (int i = 0; i < myList.tagCount() && i < inv.length; i++) {
			NBTTagCompound indexTag = (NBTTagCompound) myList.get(i);
			int index = indexTag.getInteger("item");
			try {
				inv[index] = ItemStack.loadItemStackFromNBT(indexTag);
			} catch (NullPointerException npe) {
				inv[index] = null;
			}
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound myCompound) {
		NBTTagList myList = new NBTTagList();

		for (int i = 0; i < this.inv.length; i++) {
			if (this.inv[i] != null && this.inv[i].stackSize > 0) {
				NBTTagCompound indexTag = new NBTTagCompound();
				myList.appendTag(indexTag);
				indexTag.setInteger("item", i);
				inv[i].writeToNBT(indexTag);
			}
		}
		NBTTagCompound contentTag = new NBTTagCompound();
		contentTag.setTag("items", myList);
		myCompound.setTag("inventory", contentTag);
	}

	@Override
	public void markDirty() {
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
	}

}

abstract class InventoryBackpackBase implements IInventory {

	protected ItemStack[] inv;
	protected int size;

	public InventoryBackpackBase(ItemStack stack, EntityPlayer player, int size) {
	}

	@Override
	public int getSizeInventory() {
		return this.inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inv[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int number) {
		if (inv[slot] == null)
			return null;
		ItemStack returnStack;
		if (inv[slot].stackSize > number) {
			returnStack = inv[slot].splitStack(number);
		} else {
			returnStack = inv[slot];
			inv[slot] = null;
		}
		onInventoryChanged();
		return returnStack;
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
		ItemStack returnStack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return returnStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if (0 <= slot && slot < size) {
			inv[slot] = itemStack;
		}

	}

	public void onInventoryChanged() {
		for (int i = 0; i < size; i++) {
			ItemStack tempStack = getStackInSlot(i);
			if (tempStack != null && tempStack.stackSize == 0) {
				setInventorySlotContents(i, null);
			}
		}
	}

	public void increaseSize(int i) {
		ItemStack[] newInventory = new ItemStack[size + i];
		System.arraycopy(inv, 0, newInventory, 0, size);
		inv = newInventory;
		size = size + i;
	}

	abstract public void readFromNBT(NBTTagCompound myCompound);

	abstract public void writeToNBT(NBTTagCompound myCompound);

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(getName());
	}

	@Override
	abstract public String getName();

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
