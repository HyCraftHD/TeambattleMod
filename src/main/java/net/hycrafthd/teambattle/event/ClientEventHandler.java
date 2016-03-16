package net.hycrafthd.teambattle.event;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.teambattle.TConfigs;
import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.entity.EntityHangGlider;
import net.hycrafthd.teambattle.gui.GuiCraftingRecipes;
import net.hycrafthd.teambattle.gui.GuiTeambattleOverlay;
import net.hycrafthd.teambattle.gui.GuiTeambattleSettings;
import net.hycrafthd.teambattle.proxy.ClientProxy;
import net.hycrafthd.teambattle.recipe.CommonGuiRecipe;
import net.hycrafthd.teambattle.util.CommonRegistryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

	@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event) {
		EntityPlayer player = event.entity;
		if (!TConfigs.fovAtBowOrSpeed) {
			if (player.isUsingItem()) {
				if (player.getItemInUse().getItem() == TItems.teambattlebow || player.getItemInUse().getItem() == Items.bow) {
					event.newfov = 1.0f;
					return;
				}
			}
			if (player.getActivePotionEffect(Potion.moveSpeed) != null) {
				event.newfov = 1.0f;
				return;
			}
		}
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
			if (Keyboard.isKeyDown(ClientProxy.craftinggui.getKeyCode())) {
				if (container.getSlotUnderMouse() != null) {
					Slot slot = container.getSlotUnderMouse();
					if (slot.getStack() != null) {
						ItemStack stack = slot.getStack();
						for (CommonGuiRecipe guirecipe : CommonRegistryUtil.shagedrecipes()) {
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

	@SubscribeEvent
	public void onPlayerBodyRender(PlayerBodyRenderEvent event) {
		final AbstractClientPlayer player = event.player;
		if (player.riddenByEntity != null && player.riddenByEntity instanceof EntityHangGlider && !player.onGround) {
			player.limbSwing = 0f;
			player.prevLimbSwingAmount = 0f;
			player.limbSwingAmount = 0f;
			GlStateManager.rotate(75, -1, 0, 0);
			GlStateManager.translate(0, -0.6, 1.2);
		}
	}

	@SubscribeEvent
	public void renderoverlay(RenderGameOverlayEvent event) {
		if (event.type == ElementType.ALL)
			new GuiTeambattleOverlay(event);
	}

	@SubscribeEvent
	public void onGuiScreenonInitGui(GuiScreenEvent.InitGuiEvent event) {
		if (event.gui instanceof GuiIngameMenu) {
			GuiIngameMenu menue = (GuiIngameMenu) event.gui;
			event.buttonList.add(new GuiButton(20, menue.width / 2 - 100, menue.height / 4 + 128, 200, 20, "Teambattle Settings"));
		}
	}

	@SubscribeEvent
	public void onGuiScreenonActionPerformed(GuiScreenEvent.ActionPerformedEvent event) {
		if (event.gui instanceof GuiIngameMenu) {
			if (event.button.id == 20) {
				event.gui.mc.displayGuiScreen(new GuiTeambattleSettings());
			}
		}
	}

}
