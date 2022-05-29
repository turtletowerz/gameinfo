package com.turtletowerz.gameinfo.lines;

import com.turtletowerz.gameinfo.config.Config;
import java.util.Random;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;

public class Biome extends Line {
	public static boolean isSlimeChunk(MinecraftClient client) {
		if (!client.isIntegratedServerRunning())
			return false;

		final long seed = client.getServer().getWorlds().iterator().next().getSeed();
		ChunkPos chunkpos = new ChunkPos(Coords.getBlockPos());
		long chunkX = chunkpos.x, chunkZ = chunkpos.z;

		return new Random(seed + 
			chunkX * chunkX * 4987142 + chunkX * 5947611 +
			chunkZ * chunkZ * 4392871L + (chunkZ * 389711) ^ 987234911L)
		.nextInt(10) == 0;
	}

	@Override
	public void update(MinecraftClient client) {
		String biome = Coords.toTitle(client.world.getRegistryManager().get(Registry.BIOME_KEY).getId(client.world.getBiome(Coords.getBlockPos()).value()), false);

		this.text = "";
		if (!Config.INSTANCE.HiddenBiome) {
			this.text += "§eBiome: §f" + biome;
		}

		if (isSlimeChunk(client)) {
			if (!Config.INSTANCE.HiddenBiome) {
				this.text += " §e§l- ";
			}
			this.text += "§r§aSlime Chunk";
		}
	}
}
