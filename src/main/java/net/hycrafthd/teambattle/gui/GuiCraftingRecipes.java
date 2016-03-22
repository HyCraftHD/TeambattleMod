package net.hycrafthd.teambattle.gui;

import java.io.IOException;

import net.hycrafthd.teambattle.TeambattleReference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiCraftingRecipes extends GuiScreen {

	private ResourceLocation gui = new ResourceLocation(TeambattleReference.resource + "textures/gui/crafting.png");
	private ItemStack stack;
	private ItemStack[] input;
	private GuiScreen lastgui;
	private int xSize = 176;
	private int ySize = 89;

	public GuiCraftingRecipes(ItemStack stack, ItemStack[] input, GuiScreen lastgui) {
		this.stack = stack;
		this.input = input;
		this.lastgui = lastgui;
	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}

	public void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			mc.displayGuiScreen(lastgui);
			if (mc.currentScreen == null) {
				mc.setIngameFocus();
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableTexture2D();
		this.mc.getTextureManager().bindTexture(gui);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		this.drawItemStack(stack, i + 124, j + 35);

		if (input != null) {
			for (int k = 0; k < input.length; k++) {
				ItemStack current = input[k];
				if (current != null) {
					InputRenderType inputrender = InputRenderType.byId(k);

					int ix = i + inputrender.getX() + 30;
					int iy = j + inputrender.getY() + 17;

					this.drawItemStack(current, ix, iy);
				}
			}
		}

		if ((i + 124) < mouseX && (i + 124) + 16 > mouseX && j + 35 < mouseY && (j + 35) + 16 > mouseY) {
			this.drawHoveringText(stack.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips), mouseX, mouseY);
		}

		if (input != null) {
			for (int k = 0; k < input.length; k++) {
				ItemStack current = input[k];
				if (current != null) {
					InputRenderType inputrender = InputRenderType.byId(k);

					int ix = i + inputrender.getX() + 30;
					int iy = j + inputrender.getY() + 17;

					if (ix < mouseX && ix + 16 > mouseX && iy < mouseY && iy + 16 > mouseY) {
						this.drawHoveringText(current.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips), mouseX, mouseY);
					}
				}
			}
		}

	}

	private void drawItemStack(ItemStack stack, int x, int y) {
		if (stack.getItemDamage() > 15) {
			stack.setItemDamage(0);
		}
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.enableRescaleNormal();
		this.itemRender.renderItemIntoGUI(stack, x, y);
	}

	enum InputRenderType {
		_0(0, 0, 0), _1(1, 18, 0), _2(2, 36, 0), _3(3, 0, 18), _4(4, 18, 18), _5(5, 36, 18), _6(6, 0, 36), _7(7, 18, 36), _8(8, 36, 36);

		public static InputRenderType byId(int id) {
			if (id < 0 || id >= all.length) {
				id = 0;
			}
			return all[id];
		}

		public int getId() {
			return id;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		private int id;
		private int x;
		private int y;

		private static final InputRenderType[] all = new InputRenderType[values().length];

		InputRenderType(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		static {
			for (InputRenderType type : values()) {
				all[type.getId()] = type;
			}
		}
	}

}
