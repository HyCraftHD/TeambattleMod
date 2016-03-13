package net.hycrafthd.teambattle;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TConfigs {

	public static Configuration config;

	public static boolean showHUD;
	public static boolean showEntity;

	public TConfigs(FMLPreInitializationEvent event) {

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		showHUD = getShowHUD();
		showEntity = getShowEntity();
		config.save();
	}

	public static void updateShowHUD(boolean b) {
		setShowHUD(b);
		config.save();
		showHUD = getShowHUD();
	}

	public static boolean getShowHUD() {
		return config.get("BOOLEAN", "shownHUD", true, "Change hud visibility!").getBoolean();
	}

	public static void setShowHUD(boolean b) {
		config.get("BOOLEAN", "shownHUD", true, "Change hud visibility!").set(b);
	}

	public static void updateShowEntity(boolean b) {
		setShowEntity(b);
		config.save();
		showEntity = getShowEntity();
	}

	public static boolean getShowEntity() {
		return config.get("BOOLEAN", "showEntity", true, "Change entity visibility!").getBoolean();
	}

	public static void setShowEntity(boolean b) {
		config.get("BOOLEAN", "showEntity", true, "Change entity visibility!").set(b);
	}

}
