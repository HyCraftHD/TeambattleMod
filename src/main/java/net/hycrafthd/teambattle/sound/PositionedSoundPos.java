package net.hycrafthd.teambattle.sound;

import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class PositionedSoundPos extends PositionedSound {

	public PositionedSoundPos(BlockPos pos, ResourceLocation soundResource, float volume, float pitch) {
		super(soundResource);
		this.xPosF = pos.getX();
		this.yPosF = pos.getY();
		this.zPosF = pos.getZ();
		this.volume = volume;
		this.pitch = pitch;
	}

}
