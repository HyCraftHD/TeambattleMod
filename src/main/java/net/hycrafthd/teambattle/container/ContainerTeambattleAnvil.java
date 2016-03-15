package net.hycrafthd.teambattle.container;

import net.hycrafthd.teambattle.TBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerTeambattleAnvil extends ContainerRepair {

	private World theWorld;
	private BlockPos selfPosition;

	public ContainerTeambattleAnvil(InventoryPlayer inv, World world, BlockPos pos, EntityPlayer player) {
		super(inv, world, player.getPosition(), player);
		this.theWorld = world;
		this.selfPosition = pos;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.theWorld.getBlockState(this.selfPosition).getBlock() != TBlocks.teambattleanvil ? false : playerIn.getDistanceSq((double) this.selfPosition.getX() + 0.5D, (double) this.selfPosition.getY() + 0.5D, (double) this.selfPosition.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void updateRepairOutput() {
		super.updateRepairOutput();
		maximumCost = 3;
		Slot slot = inventorySlots.get(2);
		if (slot != null) {
			ItemStack stack = slot.getStack();
			if (stack != null) {
				stack.setRepairCost(0);
				slot.putStack(stack);
				inventorySlots.set(2, slot);
			}
		}
	}

	@Override
	public void updateItemName(String newName) {
		super.updateItemName(newName.replace('&', '\u00a7'));
	}

}