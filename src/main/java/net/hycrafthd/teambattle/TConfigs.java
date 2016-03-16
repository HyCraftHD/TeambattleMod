package net.hycrafthd.teambattle;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TConfigs {

	public static Configuration config;

	public static boolean showHUD;
	public static boolean showEntity;
	public static boolean customSwordSound;
	public static boolean fovAtBowOrSpeed;

	public TConfigs(FMLPreInitializationEvent event) {

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		showHUD = getShowHUD();
		showEntity = getShowEntity();
		customSwordSound = getCustomSwordSound();
		fovAtBowOrSpeed = getFovAtBowOrSpeed();
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

	public static void updateCustomSwordSound(boolean b) {
		setCustomSwordSound(b);
		config.save();
		customSwordSound = getCustomSwordSound();
	}

	public static boolean getCustomSwordSound() {
		return config.get("BOOLEAN", "customswordsound", true, "Switch custom hitting sounds on or off!").getBoolean();
	}

	public static void setCustomSwordSound(boolean b) {
		config.get("BOOLEAN", "customswordsound", true, "Switch custom hitting sounds on or off!").set(b);
	}

	public static void updateFovAtBowOrSpeed(boolean b) {
		setFovAtBowOrSpeed(b);
		config.save();
		fovAtBowOrSpeed = getFovAtBowOrSpeed();
	}

	public static boolean getFovAtBowOrSpeed() {
		return config.get("BOOLEAN", "fovatboworsprinting", true, "Switch Fov to on or off!").getBoolean();
	}

	public static void setFovAtBowOrSpeed(boolean b) {
		config.get("BOOLEAN", "fovatboworsprinting", true, "Switch Fov to on or off!").set(b);
	}

}
