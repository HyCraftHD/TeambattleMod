package net.hycrafthd.teambattle.sound;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MovingSoundEntity extends MovingSound {

	private final Entity entity;

	public MovingSoundEntity(Entity entity, ResourceLocation res, float volume, float pitch) {
		super(res);
		this.volume = volume;
		this.pitch = pitch;
		this.entity = entity;
	}

	public void update() {
		if (entity.isDead) {
			donePlaying = true;
		} else {
			this.xPosF = (float) this.entity.posX;
			this.yPosF = (float) this.entity.posY;
			this.zPosF = (float) this.entity.posZ;
		}
	}
}