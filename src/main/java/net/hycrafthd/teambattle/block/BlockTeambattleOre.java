package net.hycrafthd.teambattle.block;

import java.util.Random;

import net.hycrafthd.teambattle.TItems;
import net.hycrafthd.teambattle.TeambattleReference;
import net.hycrafthd.teambattle.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeambattleOre extends Block {

	public BlockTeambattleOre() {
		super(Material.rock);
		this.setHardness(4.0F);
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.5F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return TItems.teambattleingotraw;
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return this.quantityDroppedWithBonus(fortune, random);
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return fortune + 1;
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		return MathHelper.getRandomIntegerInRange(rand, 3, 6);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(100) == 0) {
			TeambattleReference.proxy.playSoundStayAtLocation(pos, TeambattleReference.resource + "teambattleore", 1.0F, MathUtil.getRandomFloatInRange(this.RANDOM, 0.8F, 1.2F));
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = (double) ((float) pos.getX() + rand.nextFloat());
			double d1 = (double) ((float) pos.getY() + rand.nextFloat());
			double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
			double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;

			if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
			} else {
				d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
			}

			Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(42, d0, d1, d2, d3, d4, d5, new int[0]);
		}
	}

}
