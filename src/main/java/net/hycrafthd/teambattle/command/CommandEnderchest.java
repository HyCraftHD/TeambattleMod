package net.hycrafthd.teambattle.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class CommandEnderchest extends CommandBase {

	@Override
	public String getCommandName() {
		return "enderchest";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (sender instanceof EntityPlayer) {
			return true;
		}
		return false;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "command.enderchest.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Entity entity = sender.getCommandSenderEntity();
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			player.displayGUIChest(player.getInventoryEnderChest());
		}
	}

}
