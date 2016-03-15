package net.hycrafthd.teambattle.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiCustomButton extends GuiButton {
	protected static ResourceLocation texture = new ResourceLocation("textures/gui/widgets.png");

	public GuiCustomButton(int i, int j, int k, int l, int i1, String s) {
		super(i, j, k, l, i1, s);
	}

	public void drawButton(Minecraft minecraft, int i, int j) {
		if (!visible) {
			return;
		}

		FontRenderer fontrenderer = minecraft.fontRendererObj;
		minecraft.renderEngine.bindTexture(texture);
		GlStateManager.color(1, 1, 1, 1);

		boolean flag = i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
		int k = getHoverState(flag);

		this.drawTexturedModalRect(xPosition, yPosition, 0, 46 + k * 20, width / 2, height / 2);

		this.drawTexturedModalRect(xPosition + width / 2, yPosition, 200 - width / 2, 46 + k * 20, width / 2, height / 2);

		this.drawTexturedModalRect(xPosition, yPosition + height / 2, 0, 46 + k * 20 + 20 - height / 2, width / 2, height / 2);

		this.drawTexturedModalRect(xPosition + width / 2, yPosition + height / 2, 200 - width / 2, 46 + k * 20 + 20 - height / 2, width / 2, height / 2);

		this.mouseDragged(minecraft, i, j);

		if (!enabled) {
			this.drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xffa0a0a0);
		} else if (flag) {
			this.drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xffffa0);
		} else {
			this.drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, 0xe0e0e0);
		}
	}
}
