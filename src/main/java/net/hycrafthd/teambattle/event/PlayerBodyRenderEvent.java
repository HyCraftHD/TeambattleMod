package net.hycrafthd.teambattle.event;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerBodyRenderEvent extends Event {

	public final AbstractClientPlayer player;

	public final float partialTickTime;

	public PlayerBodyRenderEvent(AbstractClientPlayer player, float partialTickTime) {
		this.player = player;
		this.partialTickTime = partialTickTime;
	}
}