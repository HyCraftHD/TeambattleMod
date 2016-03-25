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

		Slot slot0 = inventorySlots.get(0);
		Slot slot1 = inventorySlots.get(1);
		Slot slot2 = inventorySlots.get(2);

		if (slot0 != null && slot1 != null && slot2 != null) {
			ItemStack stack0 = slot0.getStack();
			ItemStack stack1 = slot1.getStack();
			ItemStack stack2 = slot2.getStack();
			if (stack0 != null && stack2 != null && stack0.isItemEqual(stack2) && stack1 == null) {
				maximumCost = 1;
			} else {
				maximumCost = 3;
			}

			if (stack2 != null) {
				stack2.setRepairCost(0);
				slot2.putStack(stack2);
				inventorySlots.set(2, slot2);
			}
		}
	}

	@Override
	public void updateItemName(String newName) {
		super.updateItemName(newName.replace('&', '\u00a7'));
	}

}