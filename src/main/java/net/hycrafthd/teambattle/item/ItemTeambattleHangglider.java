package net.hycrafthd.teambattle.item;

import java.util.List;

import net.hycrafthd.teambattle.entity.EntityHangGlider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTeambattleHangglider extends Item {

	public ItemTeambattleHangglider() {
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (player.riddenByEntity == null) {
			if ((itemStack.stackSize > 0) && (!world.isRemote)) {
				EntityHangGlider glider = new EntityHangGlider(world);

				glider.posX = player.posX;
				glider.posY = player.posY + 1.25D;
				glider.posZ = player.posZ;

				glider.mountEntity(player);

				world.spawnEntityInWorld(glider);
			}
		} else if ((player.riddenByEntity != null) && ((player.riddenByEntity instanceof EntityHangGlider))) {
			player.riddenByEntity.setDead();
		}
		return itemStack;
	}

}
