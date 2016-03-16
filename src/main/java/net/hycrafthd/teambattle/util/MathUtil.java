package net.hycrafthd.teambattle.util;

import java.util.Random;

import net.minecraft.util.MathHelper;

public class MathUtil extends MathHelper {

	public static float getRandomFloatInRange(Random rand, float min, float max) {
		return min >= max ? min : rand.nextFloat() * (max - min) + min;
	}

}
