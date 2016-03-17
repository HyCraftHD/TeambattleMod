package net.hycrafthd.teambattle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import net.hycrafthd.teambattle.creativetab.CreativeTabTeambattle;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.hycrafthd.teambattle.world.TWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(name = TeambattleReference.name, modid = TeambattleReference.modid, version = TeambattleReference.version, acceptedMinecraftVersions = "1.8.9", acceptableRemoteVersions = "1.8.9")
public class TeambattleMod {

	public static CreativeTabs tab;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		updateMcModInfo(event);
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
		TeambattleReference.proxy.registerClickThread();
	}

	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		new TCommands(event);
	}

	private void updateMcModInfo(FMLPreInitializationEvent ev) {
		ModMetadata meta = ev.getModMetadata();

		meta.childMods.add(FMLCommonHandler.instance().findContainerFor(meta.modId));

		meta.name = EnumChatFormatting.DARK_AQUA + meta.name + EnumChatFormatting.RESET;

		meta.version = EnumChatFormatting.YELLOW + meta.version + EnumChatFormatting.RESET;

		try {
			meta.authorList.set(0, EnumChatFormatting.BLUE + meta.authorList.get(0) + EnumChatFormatting.RESET);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		meta.credits = EnumChatFormatting.LIGHT_PURPLE + meta.credits + EnumChatFormatting.RESET;

		String str = "";

		try {
			URL url = new URL("https://raw.githubusercontent.com/HyCraftHD/TeambattleMod/master/LICENSE");
			URLConnection con = url.openConnection();
			InputStream filestream = con.getInputStream();
			Reader reader = new InputStreamReader(filestream);
			BufferedReader lineReader = new BufferedReader(reader);
			Iterator<String> iterator = lineReader.lines().iterator();
			while (iterator.hasNext()) {
				str = str + iterator.next() + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String des = "";

		for (char chars : meta.description.toCharArray()) {
			des = des + EnumChatFormatting.AQUA + chars + EnumChatFormatting.RESET;
		}

		String license = "";

		for (char chars : str.toCharArray()) {
			license = license + EnumChatFormatting.GREEN + chars + EnumChatFormatting.RESET;
		}

		meta.description = des + "\n\n" + EnumChatFormatting.DARK_RED + "-------------------LICENSE-------------------" + EnumChatFormatting.RESET + "\n\n" + license;

	}

}
