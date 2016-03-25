package net.hycrafthd.teambattle.util;

import java.lang.reflect.Field;

import net.hycrafthd.teambattle.asm.util.VisitorHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.launchwrapper.Launch;

public class ReflectionUtil {

	public static boolean isKeyPressed(KeyBinding instance) {
		try {
			Field field;
			if (useSrgNames()) {
				field = KeyBinding.class.getDeclaredField("field_151474_i");
			} else {
				field = KeyBinding.class.getDeclaredField("pressTime");
			}
			field.setAccessible(true);
			Object obj = field.get(instance);
			int c = (Integer) obj;
			if (c > 0) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean useSrgNames() {
		Boolean deobfuscated = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		return deobfuscated == null || !deobfuscated;
	}

}
