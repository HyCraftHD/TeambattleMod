package net.hycrafthd.teambattle.proxy;

import net.hycrafthd.teambattle.event.CommonEventHander;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	// Client Stuff
	public void registerModels() {
	}

	// Client Stuff
	public void registerEffects() {
	}

	// Client Stuff
	public int getColor() {
		return 0;
	}

	// Client Stuff
	public void registerColorThread() {
	}

	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new CommonEventHander());
	}

}
