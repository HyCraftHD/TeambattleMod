package net.hycrafthd.teambattle.entity;

import net.hycrafthd.teambattle.item.ItemTeambattleHangglider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHangGlider extends EntityLiving {

	public EntityHangGlider(World world) {
		super(world);
		this.setAlwaysRenderNameTag(true);

	}

	public void onEntityUpdate() {
		super.onEntityUpdate();
		if ((this.ridingEntity != null) && ((this.ridingEntity instanceof EntityPlayer))) {
			EntityPlayer player = (EntityPlayer) this.ridingEntity;
			if (hasHangglider(player)) {
				if (!player.onGround && !this.worldObj.getBlockState(player.getPosition()).getBlock().getMaterial().isLiquid() && !this.worldObj.getBlockState(player.getPosition().down()).getBlock().getMaterial().isLiquid()) {
					if (player.motionY < 0) {
						final double horizontalSpeed;
						final double verticalSpeed;

						if (player.isSprinting()) {
							horizontalSpeed = 0.0425;
							verticalSpeed = 0.5;
						} else if (player.isSneaking()) {
							horizontalSpeed = 0.1;
							verticalSpeed = 1.0;
						} else {
							horizontalSpeed = 0.03;
							verticalSpeed = 0.4;
						}

						player.motionY *= verticalSpeed;
						motionY *= verticalSpeed;
						double x = Math.cos(Math.toRadians(player.rotationYawHead + 90)) * horizontalSpeed;
						double z = Math.sin(Math.toRadians(player.rotationYawHead + 90)) * horizontalSpeed;
						player.motionX += x;
						player.motionZ += z;
						player.fallDistance = 0f;
					}
				}

				if (!this.worldObj.isRemote) {
					this.rotationYaw = player.rotationYaw;
				}
			} else if (!this.worldObj.isRemote) {
				this.setDead();
			}
		} else {
			this.setDead();
		}
	}

	public boolean hasHangglider(EntityPlayer p) {
		return (p != null) && (p.getHeldItem() != null) && ((p.getHeldItem().getItem() instanceof ItemTeambattleHangglider));
	}

	public boolean attackEntityAsMob(Entity entity) {
		return false;
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}
}
