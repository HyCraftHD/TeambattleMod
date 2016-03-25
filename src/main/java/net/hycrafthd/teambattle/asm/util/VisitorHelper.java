package net.hycrafthd.teambattle.asm.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import com.google.common.base.Preconditions;

import net.hycrafthd.teambattle.util.ReflectionUtil;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class VisitorHelper {

	public abstract static class TransformProvider {
		private final int flags;

		public TransformProvider(int flags) {
			this.flags = flags;
		}

		public abstract ClassVisitor createVisitor(String name, ClassVisitor cv);
	}

	public static byte[] apply(byte[] bytes, String name, TransformProvider context) {
		Preconditions.checkNotNull(bytes);
		ClassReader cr = new ClassReader(bytes);
		ClassWriter cw = new ClassWriter(cr, context.flags);
		ClassVisitor mod = context.createVisitor(name, cw);

		try {
			cr.accept(mod, 0);
			return cw.toByteArray();
		} catch (RuntimeException e) {
			return bytes;
		}
	}

	public static String getMappedName(String clsName) {
		return ReflectionUtil.useSrgNames() ? FMLDeobfuscatingRemapper.INSTANCE.unmap(clsName) : clsName;
	}
}