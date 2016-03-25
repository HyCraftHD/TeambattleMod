package net.hycrafthd.teambattle.proxy;

import net.hycrafthd.teambattle.event.CommonEventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	// Client Stuff
	public void registerModels() {
	}

	// Client Stuff
	public void registerEffects() {
	}

	// Client Stuff
	public void registerKeybinding() {
	}

	// Client Stuff
	public int getColor() {
		return 0;
	}

	// Client Stuff
	public void registerColorThread() {
	}

	// Client Stuff
	public void registerClickThread() {
	}

	// Client Stuff
	public void registerEntityRenders() {
	}

	// Client Stuff
	public void playSoundMovingAtEntity(Entity entity, String path, float soundVolume, float soundPitch) {
	}

	// Client Stuff
	public void playSoundStayAtLocation(BlockPos pos, String path, float soundVolume, float soundPitch) {
	}

	// Client Stuff
	public void addTeambattleRecipes() {
	}

	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}
}
