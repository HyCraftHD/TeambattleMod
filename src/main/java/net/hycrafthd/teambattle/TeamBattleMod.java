package net.hycrafthd.teambattle;

import net.hycrafthd.teambattle.creativetab.CreativeTabTeambattle;
import net.hycrafthd.teambattle.event.ClientEventHandler;
import net.hycrafthd.teambattle.event.CommonEventHander;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.hycrafthd.teambattle.world.TWorldGenerator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = TeambattleReference.name, modid = TeambattleReference.modid, version = TeambattleReference.version, acceptedMinecraftVersions = "1.8.9", acceptableRemoteVersions = "1.8.9")
public class TeamBattleMod {

	public static CreativeTabs tab;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		tab = new CreativeTabTeambattle();
		new TBlocks();
		new TItems();
		TeambattleReference.proxy.registerEvents();
		CommonRegistryUtil.registerGuiHandler(new TeamBattleGuiHandler());
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new TRecipes();
		CommonRegistryUtil.registerWorldGenerator(new TWorldGenerator(), 0);
		TeambattleReference.proxy.registerModels();
		TeambattleReference.proxy.registerEffects();
		TeambattleReference.proxy.registerColorThread();
	}

}
