package net.hycrafthd.teambattle.gui;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.container.ContainerTeambattleBackpack;
import net.hycrafthd.teambattle.inventory.InventoryTeambattleBackpack;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeambattleBackpack extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(TeambattleReference.resource + "textures/gui/backpack.png");

	InventoryTeambattleBackpack inv;

	public GuiTeambattleBackpack(InventoryTeambattleBackpack backpack, InventoryPlayer player) {
		super(new ContainerTeambattleBackpack(backpack, player));
		this.inv = backpack;
		this.xSize = 176;
		this.ySize = 258;
	}

	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(inv != null ? inv.getName() : "Backpack", 7, 7, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawModalRectWithCustomSizedTexture(k, l, 0, 0, this.xSize, this.ySize, 512, 512);
	}
}