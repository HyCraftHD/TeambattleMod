package net.hycrafthd.teambattle.asm.util;

import net.hycrafthd.teambattle.util.ReflectionUtil;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class MethodMatcher {
	private final String clsName;
	private final String description;
	private final String srgName;
	private final String mcpName;

	public MethodMatcher(String clsName, String description, String mcpName, String srgName) {
		this.clsName = clsName;
		this.description = description;
		this.srgName = srgName;
		this.mcpName = mcpName;
	}

	public MethodMatcher(MappedType cls, String description, String mcpName, String srgName) {
		this(cls.name(), description, mcpName, srgName);
	}

	public boolean match(String methodName, String methodDesc) {
		if (!methodDesc.equals(description))
			return false;
		if (methodName.equals(mcpName))
			return true;
		if (!ReflectionUtil.useSrgNames())
			return false;
		String mapped = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(clsName, methodName, methodDesc);
		return mapped.equals(srgName);
	}

	@Override
	public String toString() {
		return String.format("Matcher: %s.[%s,%s] %s", clsName, srgName, mcpName, description);
	}

}