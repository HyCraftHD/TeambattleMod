package net.hycrafthd.teambattle.world;

import java.util.Random;

import net.hycrafthd.teambattle.TBlocks;
import net.hycrafthd.teambattle.util.GenerationUtil;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TWorldGenerator implements IWorldGenerator {

	private void nether(Random random, int x, int z, World world) {
	}

	private void overworld(Random random, int x, int z, World world) {
		GenerationUtil.generateOre(TBlocks.teambattleore.getDefaultState(), random, x, z, world, 8, 2, 20, 4, 10);
	}

	private void end(Random random, int x, int z, World world) {
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		switch (world.provider.getDimensionId()) {
		case -1:
			nether(random, x, z, world);
			break;
		case 0:
			overworld(random, x, z, world);
			break;
		case 1:
			end(random, x, z, world);
			break;
		}
	}

}
