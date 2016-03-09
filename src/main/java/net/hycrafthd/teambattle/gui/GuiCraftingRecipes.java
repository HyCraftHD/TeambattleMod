package net.hycrafthd.teambattle.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class GuiCraftingRecipes extends GuiScreen {

	private ResourceLocation gui = new ResourceLocation(TeambattleReference.resource + "textures/gui/crafting.png");
	private ItemStack stack;
	private ItemStack[] input;
	private GuiScreen lastgui;
	private int xSize = 175;
	private int ySize = 88;

	public GuiCraftingRecipes(ItemStack stack, ItemStack[] input, GuiScreen lastgui) {
		this.stack = stack;
		this.input = input;
		this.lastgui = lastgui;
	}

	public void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1) {
			mc.displayGuiScreen(lastgui);
			if (mc.currentScreen == null) {
				mc.setIngameFocus();
			}
		}
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;

		// TODO Render system for tooltips would be nice!

		// int mx = (this.width - mouseX) / 2;
		// int my = (this.height - mouseY) / 2;

		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		this.drawItemStack(stack, i + 124, j + 35);

		// System.out.println(i + " " + j + " : " + mouseX + " " + mouseY);

		// if (0 - mx < 8 && 0 - mx > -8 && 0 - my < 8 && 0 - my > -8) {
		// this.drawHoveringText(stack.getTooltip(mc.thePlayer,
		// mc.gameSettings.advancedItemTooltips), mouseX, mouseY);
		// }

		if (input != null) {
			try {
				for (int k = 0; k < input.length; k++) {
					ItemStack current = input[k];
					if (current != null) {
						InputRenderType inputrender = InputRenderType.byId(k);
						this.drawItemStack(current, i + inputrender.getX() + 30, j + inputrender.getY() + 17);

						int ix = inputrender.getX();
						int iy = inputrender.getY();

						/*
						 * if (ix - mx < 8 && ix - mx >= -9 && iy - my < 8 && iy
						 * - my >= -9) {
						 * this.drawHoveringText(current.getTooltip(mc.
						 * thePlayer, mc.gameSettings.advancedItemTooltips),
						 * mouseX, mouseY); }
						 */
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private void drawItemStack(ItemStack stack, int x, int y) {
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
