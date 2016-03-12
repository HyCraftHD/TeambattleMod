package net.hycrafthd.teambattle.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.asm.util.VisitorHelper;
import net.hycrafthd.teambattle.asm.util.VisitorHelper.TransformProvider;
import net.hycrafthd.teambattle.asm.visitor.PlayerRendererHookVisitor;
import net.minecraft.launchwrapper.IClassTransformer;

public class TeambattleAccessTransformer implements IClassTransformer {

	@Override
	public byte[] transform(final String name, String transformedName, byte[] bytes) {
		if (bytes == null)
			return bytes;
		if (transformedName.equals("net.minecraft.client.renderer.entity.RenderPlayer")) {
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(String name, ClassVisitor cv) {
					TeambattleReference.log.info(String.format("Trying to patch RenderPlayer (class: %s)", name));
					return new PlayerRendererHookVisitor(name, cv);
				}
			});
		}
		return bytes;
	}
}