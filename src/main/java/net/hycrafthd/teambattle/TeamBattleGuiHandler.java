package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.container.ContainerTeambattleBackpack;
import net.hycrafthd.teambattle.gui.GuiCraftingRecipes;
import net.hycrafthd.teambattle.gui.GuiTeambattleBackpack;
import net.hycrafthd.teambattle.inventory.InventoryTeambattleBackpack;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class TeamBattleGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			InventoryTeambattleBackpack backpack = new InventoryTeambattleBackpack(player.inventory.getCurrentItem(), player);
			return new ContainerTeambattleBackpack(backpack, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 0) {
			InventoryTeambattleBackpack backpack = new InventoryTeambattleBackpack(player.inventory.getCurrentItem(), player);
			return new GuiTeambattleBackpack(backpack, player.inventory);
		}
		return null;
	}

}
