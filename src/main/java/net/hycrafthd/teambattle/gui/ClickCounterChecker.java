package net.hycrafthd.teambattle.gui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.hycrafthd.teambattle.asm.util.VisitorHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClickCounterChecker {

	private static int timer = 0;
	private static long updateTime = 0L;
	private static HashMap<Integer, Integer> clickList = new HashMap();
	private static boolean click = false;
	private static double clicks = 0.0D;
	private static double result = 0.0D;

	public static void update() {
		int am = (int) clicks;
		keyCheck();
		if ((clickList.values().isEmpty()) && (clicks != 0.0D) && (am == 0)) {
			updateTime = System.currentTimeMillis();
		}
		if (updateTime + 1000L < System.currentTimeMillis()) {
			updateTime = System.currentTimeMillis();
			clickList.put(Integer.valueOf(timer), Integer.valueOf((int) clicks));
			if (timer > 1) {
				clickList.remove(Integer.valueOf(timer - 1));
			}
			timer += 1;
			if (clicks == 0.0D) {
				timer = 1;
				clickList.clear();
			}
			clicks = 0.0D;
		}
		double totalClicks = 0.0D;
		for (Iterator localIterator = clickList.values().iterator(); localIterator.hasNext();) {
			int s = ((Integer) localIterator.next()).intValue();
			totalClicks += s;
		}
		if (totalClicks != 0.0D) {
			double dTimer = clickList.size();
			double res = totalClicks / dTimer;
			if (result > res) {
				result -= 0.5D;
			}
			if (result < res) {
				result += 0.5D;
			}
		} else if (result > 0.0D) {
			result -= 0.5D;
		} else {
			result = 0.0D;
		}
	}

	private static void keyCheck() {
		if (Mouse.isCreated() && Keyboard.isCreated()) {
			if (isKeyPressed(Minecraft.getMinecraft().gameSettings.keyBindAttack) || isKeyPressed(Minecraft.getMinecraft().gameSettings.keyBindUseItem)) {
				if (!click) {
					clicks += 1.0D;
					click = true;
				}
			} else {
				click = false;
			}
		}
	}

	private static boolean isKeyPressed(KeyBinding instance) {
		try {
			Field field;
			if (VisitorHelper.useSrgNames()) {
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

	public static double getClickToDisplay() {
		return Double.parseDouble(String.format(Locale.ENGLISH, "%1.2f", result));
	}

}
