package net.hycrafthd.teambattle.gui;

import java.io.IOException;

import net.hycrafthd.teambattle.TConfigs;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeambattleSettings extends GuiScreen {

	private GuiSlider gamma;

	@Override
	public void initGui() {
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120, 200, 20, "Done"));
		buttonList.add(gamma = new GuiSlider(1, width / 2 - 100, height / 4, 200, 20, "Gamma ", "%", 0.0, 1000.0, mc.gameSettings.gammaSetting * 100, true, true));
		buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 24, 200, 20, "Show entity: " + TConfigs.showEntity));
		buttonList.add(new GuiButton(3, width / 2 - 100, height / 4 + 48, 200, 20, "Show HUD: " + TConfigs.showHUD));
		buttonList.add(new GuiButton(4, width / 2 - 100, height / 4 + 72, 200, 20, "Custom sword sound: " + TConfigs.customSwordSound));
		buttonList.add(new GuiButton(5, width / 2 - 100, height / 4 + 96, 200, 20, "Change fov: " + TConfigs.fovAtBowOrSpeed));
	}

	@Override
	public void updateScreen() {
		mc.gameSettings.gammaSetting = (float) gamma.sliderValue * 10;
	}

	@Override
	public void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			mc.displayGuiScreen(new GuiIngameMenu());
			break;
		case 2:
			TConfigs.updateShowHUD(!TConfigs.showHUD);
			button.displayString = "Show HUD: " + TConfigs.showHUD;
			break;
		case 3:
			TConfigs.updateShowEntity(!TConfigs.showEntity);
			button.displayString = "Show Entity: " + TConfigs.showEntity;
			break;
		case 4:
			TConfigs.updateCustomSwordSound(!TConfigs.customSwordSound);
			button.displayString = "Custom sword sound: " + TConfigs.customSwordSound;
			break;
		case 5:
			TConfigs.updateFovAtBowOrSpeed(!TConfigs.fovAtBowOrSpeed);
			button.displayString = "Change fov: " + TConfigs.fovAtBowOrSpeed;
			break;
		}

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Teambattle Settings", this.width / 2, 40, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
