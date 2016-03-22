package net.hycrafthd.teambattle.item;

import java.util.ArrayList;
import java.util.List;

import net.hycrafthd.teambattle.TBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemTeambattlePickaxe extends ItemPickaxe {

	private List<Block> blocks = new ArrayList<Block>();

	public ItemTeambattlePickaxe(ToolMaterial material) {
		super(material);
		blocks.add(TBlocks.teambattleore);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		if ((player instanceof EntityPlayerMP)) {
			breaking(itemstack, pos, (EntityPlayerMP) player);
			return true;
		}
		return false;

	}

	private void breaking(ItemStack stack, BlockPos pos, EntityPlayerMP playermp) {

		World world = playermp.worldObj;
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (!world.isRemote) {
			if (!world.isAirBlock(pos)) {
				block.onBlockHarvested(world, pos, state, playermp);
				if (block.removedByPlayer(world, pos, playermp, true)) {
					block.onBlockDestroyedByPlayer(world, pos, state);
					if (!playermp.capabilities.isCreativeMode) {
						block.harvestBlock(world, playermp, pos, state, world.getTileEntity(pos));
						block.dropXpOnBlockBreak(world, pos, block.getExpDrop(world, pos, 0));
						stack.damageItem(1, playermp);
					}
				}
				playermp.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
			}
		}

		if (block instanceof BlockRedstoneOre || block instanceof BlockOre || blocks.contains(block)) {
			if (playermp.worldObj.getBlockState(pos.up()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.up()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.up()).getBlock())) {
				this.breaking(stack, pos.up(), playermp);
			}
			if (playermp.worldObj.getBlockState(pos.down()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.down()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.down()).getBlock())) {
				this.breaking(stack, pos.down(), playermp);
			}
			if (playermp.worldObj.getBlockState(pos.north()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.north()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.north()).getBlock())) {
				this.breaking(stack, pos.north(), playermp);
			}
			if (playermp.worldObj.getBlockState(pos.south()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.south()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.south()).getBlock())) {
				this.breaking(stack, pos.south(), playermp);
			}
			if (playermp.worldObj.getBlockState(pos.west()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.west()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.west()).getBlock())) {
				this.breaking(stack, pos.west(), playermp);
			}
			if (playermp.worldObj.getBlockState(pos.east()).getBlock() instanceof BlockRedstoneOre || playermp.worldObj.getBlockState(pos.east()).getBlock() instanceof BlockOre || blocks.contains(playermp.worldObj.getBlockState(pos.east()).getBlock())) {
				this.breaking(stack, pos.east(), playermp);
			}
		}
	}

}
