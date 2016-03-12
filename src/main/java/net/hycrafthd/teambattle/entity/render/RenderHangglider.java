package net.hycrafthd.teambattle.entity.render;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.entity.EntityHangGlider;
import net.hycrafthd.teambattle.entity.model.ModelHangGlider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHangglider extends RenderLiving {

	private ResourceLocation texture = new ResourceLocation(TeambattleReference.resource + "textures/entity/hangglider.png");

	public RenderHangglider() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelHangGlider(), 0.0F);
	}

	@Override
	public boolean shouldRender(EntityLiving livingEntity, ICamera camera, double camX, double camY, double camZ) {
		return livingEntity.ridingEntity != null;
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
