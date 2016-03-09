package net.hycrafthd.teambattle.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityTeambattleOreFX extends EntityFX {
	private float oreParticleScale;
	private double positionX;
	private double positionY;
	private double positionZ;

	protected EntityTeambattleOreFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.motionX = xSpeedIn;
		this.motionY = ySpeedIn;
		this.motionZ = zSpeedIn;
		this.positionX = this.posX = xCoordIn;
		this.positionY = this.posY = yCoordIn;
		this.positionZ = this.posZ = zCoordIn;
		float f = this.rand.nextFloat() * 0.6F + 0.4F;
		this.oreParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + this.rand.nextFloat() * 2.0F + 1;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F * f;
		this.particleGreen *= this.rand.nextFloat();
		this.particleRed *= this.rand.nextFloat();
		this.particleBlue *= this.rand.nextFloat();
		this.particleMaxAge = (int) (Math.random() * 10.0D) + 10;
		this.noClip = true;
		this.setParticleTextureIndex((int) (Math.random() * 8.0D));
	}

	/**
	 * Renders the particle
	 */
	public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
		float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge;
		f = 1.0F - f;
		f = f * f;
		f = 1.0F - f;
		this.particleScale = this.oreParticleScale * f;
		super.renderParticle(worldRendererIn, entityIn, partialTicks, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
	}

	public int getBrightnessForRender(float partialTicks) {
		int i = super.getBrightnessForRender(partialTicks);
		float f = (float) this.particleAge / (float) this.particleMaxAge;
		f = f * f;
		f = f * f;
		int j = i & 255;
		int k = i >> 16 & 255;
		k = k + (int) (f * 15.0F * 16.0F);

		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	/**
	 * Gets how bright this entity is.
	 */
	public float getBrightness(float partialTicks) {
		float f = super.getBrightness(partialTicks);
		float f1 = (float) this.particleAge / (float) this.particleMaxAge;
		f1 = f1 * f1 * f1 * f1;
		return f * (1.0F - f1) + f1;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		float f = (float) this.particleAge / (float) this.particleMaxAge;
		float f1 = -f + f * f * 2.0F;
		float f2 = 1.0F - f1;
		this.posX = this.positionX + this.motionX * (double) f2;
		this.posY = this.positionY + this.motionY * (double) f2 + (double) (1.0F - f);
		this.posZ = this.positionZ + this.motionZ * (double) f2;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new EntityTeambattleOreFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
}