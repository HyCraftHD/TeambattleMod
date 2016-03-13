package net.hycrafthd.teambattle.gui;

import java.util.Iterator;

import net.hycrafthd.teambattle.TConfigs;
import net.hycrafthd.teambattle.TeamBattleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeambattleOverlay {

	private Minecraft mc;

	public GuiTeambattleOverlay(RenderGameOverlayEvent event) {
		mc = Minecraft.getMinecraft();

		if (!mc.gameSettings.showDebugInfo) {
			if (TConfigs.showHUD)
				drawLeftHud();
			if (TConfigs.showEntity) {
				float rotX = (float) (45 * Math.sin(mc.thePlayer.rotationYaw / 90 * Math.PI));
				GuiInventory.drawEntityOnScreen(event.resolution.getScaledWidth() - 25, event.resolution.getScaledHeight() - 10, 40, -rotX, -mc.thePlayer.rotationPitch, mc.thePlayer);
			}
		}

	}

	private void drawLeftHud() {
		GuiIngame gui = mc.ingameGUI;
		gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "FPS" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + String.valueOf(mc.getDebugFPS()), 2, 2, 14737632);

		gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "X" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + String.valueOf((int) mc.thePlayer.posX), 2, 17, 14737632);
		gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "Y" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + String.valueOf((int) mc.thePlayer.posY), 2, 27, 14737632);
		gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "Z" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + String.valueOf((int) mc.thePlayer.posZ), 2, 37, 14737632);

		gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "Facing" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + mc.thePlayer.getHorizontalFacing(), 2, 52, 14737632);

		if (!mc.isSingleplayer()) {
			gui.drawString(mc.fontRendererObj, EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + "Ping" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RESET + getPingInformation(), 2, 67, 14737632);
		}
	}

	private String getPingInformation() {
		NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
		if (handler == null)
			return "?";
		NetworkPlayerInfo info = handler.getPlayerInfo(handler.getGameProfile().getId());
		if (info == null)
			return "?";
		int ping = info.getResponseTime();
		if (ping == 0)
			return "?";
		return "" + ping;
	}

}