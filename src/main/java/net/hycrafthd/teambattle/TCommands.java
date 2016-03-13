package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.command.CommandEnderchest;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class TCommands {

	public TCommands(FMLServerStartingEvent event) {
		register(event);
	}

	private void register(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandEnderchest());
	}

}
