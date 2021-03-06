package net.hycrafthd.teambattle.asm.util;

import net.hycrafthd.teambattle.util.ReflectionUtil;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class FieldMatcher {
	private final String clsName;
	private final String description;
	private final String srgName;
	private final String mcpName;

	public FieldMatcher(String clsName, String description, String mcpName, String srgName) {
		this.clsName = clsName;
		this.description = description;
		this.srgName = srgName;
		this.mcpName = mcpName;
	}

	public boolean match(String fieldName, String fieldDesc) {
		if (!fieldDesc.equals(description))
			return false;
		if (fieldName.equals(mcpName))
			return true;
		if (!ReflectionUtil.useSrgNames())
			return false;
		String mapped = FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(clsName, fieldName, fieldDesc);
		return mapped.equals(srgName);
	}
}