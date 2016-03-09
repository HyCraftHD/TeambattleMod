package net.hycrafthd.teambattle.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.gui.GuiCraftingRecipes;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

	@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event) {
		EntityPlayer player = event.entity;
		if (player.isUsingItem() && player.getItemInUse().getItem() == TItems.teambattlebow) {
			int s = player.getItemInUseDuration();
			float f = s * 0.3F;
			if (f > 4.0F) {
				f = 4.0F;
			}
			event.newfov *= 1.0F - f * 0.125F;
		}
	}

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		if (event.itemStack.getItem().getRegistryName().startsWith(TeambattleReference.modid)) {
			String tooltip = StatCollector.translateToLocal("tooltip." + event.itemStack.getUnlocalizedName());
			List<String> list = new ArrayList<String>();
			for (String line : tooltip.split("#break#")) {
				list.add((EnumChatFormatting.AQUA + line + EnumChatFormatting.RESET));
			}
			event.toolTip.addAll(1, list);
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen != null && mc.currentScreen instanceof GuiContainer) {
			GuiContainer container = (GuiContainer) mc.currentScreen;
			if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
				if (container.getSlotUnderMouse() != null) {
					Slot slot = container.getSlotUnderMouse();
					if (slot.getStack() != null) {
						ItemStack stack = slot.getStack();
						for (CommonGuiRecipe guirecipe : CommonRegistryUtil.getGuirecipes()) {
							if (guirecipe.getRecipeOutput() != null && guirecipe.getRecipeOutput().isItemEqual(stack)) {
								mc.displayGuiScreen(new GuiCraftingRecipes(guirecipe.getRecipeOutput(), guirecipe.getRecipeInput(), mc.currentScreen));
								return;
							}
						}
					}
				}
			}
		}
	}

}
