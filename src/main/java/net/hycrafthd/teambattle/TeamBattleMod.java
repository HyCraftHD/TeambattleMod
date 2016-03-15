package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.creativetab.CreativeTabTeambattle;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.hycrafthd.teambattle.world.TWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(name = TeambattleReference.name, modid = TeambattleReference.modid, version = TeambattleReference.version, acceptedMinecraftVersions = "1.8.9", acceptableRemoteVersions = "1.8.9")
public class TeambattleMod {

	public static CreativeTabs tab;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		new TConfigs(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		tab = new CreativeTabTeambattle();
		new TBlocks();
		new TItems();
		new TEntitys();
		TeambattleReference.proxy.registerEvents();
		CommonRegistryUtil.registerGuiHandler(new TGuihandler());
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new TRecipes();
		CommonRegistryUtil.registerWorldGenerator(new TWorldGenerator(), 0);
		TeambattleReference.proxy.registerModels();
		TeambattleReference.proxy.registerEntityRenders();
		TeambattleReference.proxy.registerEffects();
		TeambattleReference.proxy.registerKeybinding();
		TeambattleReference.proxy.registerColorThread();
	}

	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		new TCommands(event);
	}

}
