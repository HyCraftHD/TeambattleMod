package net.hycrafthd.teambattle.asm.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

import com.google.common.base.Throwables;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.asm.util.MappedType;
import net.hycrafthd.teambattle.asm.util.MethodMatcher;
import net.hycrafthd.teambattle.event.PlayerBodyRenderEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.common.MinecraftForge;

public class PlayerRendererHookVisitor extends ClassVisitor {

	private static class InjectorMethodVisitor extends MethodVisitor {

		private final Method postMethod;

		public InjectorMethodVisitor(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);

			try {
				postMethod = Method.getMethod(PlayerRendererHookVisitor.class.getMethod("post", AbstractClientPlayer.class, float.class));
			} catch (Throwable t) {
				throw Throwables.propagate(t);
			}

			TeambattleReference.log.info((String.format("Injecting hook %s.%s into EntityPlayerRender.rotateCorpse", PlayerRendererHookVisitor.class, postMethod)));
		}

		@Override
		public void visitInsn(int opcode) {
			if (opcode == Opcodes.RETURN) {
				final Type hookCls = Type.getType(PlayerRendererHookVisitor.class);
				visitVarInsn(Opcodes.ALOAD, 1);
				visitVarInsn(Opcodes.FLOAD, 4);
				visitMethodInsn(Opcodes.INVOKESTATIC, hookCls.getInternalName(), postMethod.getName(), postMethod.getDescriptor());
				TeambattleReference.log.info("Injected!");
			}

			super.visitInsn(opcode);
		}
	}

	public static void post(AbstractClientPlayer player, float partialTickTime) {
		MinecraftForge.EVENT_BUS.post(new PlayerBodyRenderEvent(player, partialTickTime));
	}

	private final MethodMatcher modifiedMethod;

	public PlayerRendererHookVisitor(String rendererTypeCls, ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		Type injectedMethodType = Type.getMethodType(Type.VOID_TYPE, MappedType.of(AbstractClientPlayer.class).type(), Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE);
		modifiedMethod = new MethodMatcher(rendererTypeCls, injectedMethodType.getDescriptor(), "rotateCorpse", "func_77043_a");
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		return modifiedMethod.match(name, desc) ? new InjectorMethodVisitor(parent) : parent;
	}

}