package net.hycrafthd.teambattle.proxy;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.ibm.icu.impl.ICUService.Key;

import net.hycrafthd.teambattle.TBlocks;
import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.entity.EntityHangGlider;
import net.hycrafthd.teambattle.entity.render.RenderHangglider;
import net.hycrafthd.teambattle.event.ClientEventHandler;
import net.hycrafthd.teambattle.particle.EntityTeambattleOreFX;
import net.hycrafthd.teambattle.sound.MovingSoundEntity;
import net.hycrafthd.teambattle.sound.PositionedSoundPos;
import net.hycrafthd.teambattle.util.ClientRegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	Minecraft mc = Minecraft.getMinecraft();

	public static KeyBinding craftinggui;
	public static KeyBinding attack2;

	public void registerModels() {

		// Blocks
		ClientRegistryUtil.registerModelRenderer(TBlocks.teambattleore);
		ClientRegistryUtil.registerModelRenderer(TBlocks.teambattleanvil);

		// Items
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleingotraw);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleingot);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlesword);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlepickaxe);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleshovel);
		ClientRegistryUtil.registerItemVariants(TItems.teambattlebow, "teambattlebow", "teambattlebow_0", "teambattlebow_1", "teambattlebow_2");
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlebow);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlehelmet);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlechestplate);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleleggings);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattleboots);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlebackpack);
		ClientRegistryUtil.registerModelRenderer(TItems.teambattlehangglider);

	}

	public void registerEffects() {
		EffectRenderer effect = mc.effectRenderer;
		effect.registerParticle(42, new EntityTeambattleOreFX.Factory());
	}

	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		super.registerEvents();
	}

	public void registerKeybinding() {
		craftinggui = new KeyBinding("keybinding.keycraftinggui", Keyboard.KEY_K, "category.keybinding.teambattle");
		attack2 = new KeyBinding("keybinding.keyattack2", Keyboard.KEY_X, "category.keybinding.teambattle");
		ClientRegistryUtil.registerKeybinding(craftinggui);
		ClientRegistryUtil.registerKeybinding(attack2);
	}

	public void registerEntityRenders() {
		ClientRegistryUtil.registerEntityRenderer(EntityHangGlider.class, new RenderHangglider());
	}

	public void playSoundMovingAtEntity(Entity entity, String path, float soundVolume, float soundPitch) {
		Minecraft.getMinecraft().getSoundHandler().playSound(new MovingSoundEntity(entity, new ResourceLocation(path), soundVolume, soundPitch));
	}

	public void playSoundStayAtLocation(BlockPos pos, String path, float soundVolume, float soundPitch) {
		Minecraft.getMinecraft().getSoundHandler().playSound(new PositionedSoundPos(pos, new ResourceLocation(path), soundVolume, soundPitch));
	}

	Color color = null;

	public void registerColorThread() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Random rand = new Random();
					float red = rand.nextFloat();
					float green = rand.nextFloat();
					float blue = rand.nextFloat();
					color = new Color(red, green, blue);
					try {
						Thread.sleep(250L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "color");
		th.start();
	}

	public int getColor() {
		if (color == null)
			return 0;
		return color.getRGB();
	}

}
