package net.hycrafthd.teambattle.util;

import java.util.List;

import net.hycrafthd.teambattle.TeambattleReference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientRegistryUtil {

	public static void registerModelRenderer(Object obj) {
		ClientRegistryUtil.registerModelRenderer(obj, 0);
	}

	public static void registerModelRenderer(Object obj, int meta) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			ClientRegistryUtil.registerModelRenderer(item, meta, new ModelResourceLocation(TeambattleReference.resource + item.getUnlocalizedName().substring(5), "inventory"));
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	public static void registerModelRenderer(Object obj, int meta, ModelResourceLocation loc) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	public static void registerItemVariants(Item item, String... names) {
		for (String name : names) {
			ModelBakery.registerItemVariants(item, new ResourceLocation(TeambattleReference.resource + name));
		}
	}

	public static void registerEntityRenderer(Class<? extends Entity> entityClass, IRenderFactory<Entity> render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}

	public static void registerKeybinding(KeyBinding key) {
		ClientRegistry.registerKeyBinding(key);
	}
}
