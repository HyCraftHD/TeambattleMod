package net.hycrafthd.teambattle.item;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.container.ContainerTeambattleBackpack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemTeambattleBackpack extends Item {

	public ItemTeambattleBackpack() {
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.openGui(TeambattleReference.instance, 0, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return itemstack;
	};

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int metadata, boolean bool) {
		if (itemstack.getTagCompound() == null) {
			itemstack.setTagCompound(new NBTTagCompound());
		}

		if (world.isRemote || !bool) {
			return;
		}

		if (((EntityPlayer) entity).openContainer == null || ((EntityPlayer) entity).openContainer instanceof ContainerPlayer) {
			return;
		}
		if (((EntityPlayer) entity).openContainer instanceof ContainerTeambattleBackpack) {

			ContainerTeambattleBackpack myContainer = (ContainerTeambattleBackpack) ((EntityPlayer) entity).openContainer;
			if (myContainer.updateNotification) {
				myContainer.saveToNBT(itemstack);
				myContainer.updateNotification = false;
			}
		}
	}
}
