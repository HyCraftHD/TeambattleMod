package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.container.ContainerTeambattleAnvil;
import net.hycrafthd.teambattle.container.ContainerTeambattleBackpack;
import net.hycrafthd.teambattle.gui.GuiTeambattleAnvil;
import net.hycrafthd.teambattle.gui.GuiTeambattleBackpack;
import net.hycrafthd.teambattle.inventory.InventoryTeambattleBackpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TGuihandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			InventoryTeambattleBackpack backpack = new InventoryTeambattleBackpack(player.inventory.getCurrentItem(), player);
			return new ContainerTeambattleBackpack(backpack, player.inventory);
		}
		if (ID == 1) {
			return new ContainerTeambattleAnvil(player.inventory, world, new BlockPos(x, y, z), player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			InventoryTeambattleBackpack backpack = new InventoryTeambattleBackpack(player.inventory.getCurrentItem(), player);
			return new GuiTeambattleBackpack(backpack, player.inventory);
		}
		if (ID == 1) {
			return new GuiTeambattleAnvil(player.inventory, world, new BlockPos(x, y, z));
		}
		return null;
	}

}
