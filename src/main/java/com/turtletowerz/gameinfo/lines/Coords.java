package com.turtletowerz.gameinfo.lines;

import com.turtletowerz.gameinfo.GameInfo;
import com.turtletowerz.gameinfo.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

public class Coords extends Line {
	private static Vec3d oldpos;
	private static Vec3d currpos;
	private static BlockPos blockpos;
	private static String direction;

	private static Vec3d netherMult = new Vec3d(1 / 8f, 1, 1 / 8f);
	private static Vec3d overworldMult = new Vec3d(8, 1, 8);

	public static BlockPos getBlockPos() {
		return blockpos;
	}

	public static String getCoords() {
		return (long)currpos.x + " " + (long)currpos.y + " " + (long)currpos.z;
	}

	public static String getVelocity() { // Returns the velocity in blocks per second
		final double vel = oldpos.multiply(1, 0, 1).distanceTo(currpos.multiply(1, 0, 1));
		return (Math.round(vel * 20 * 100) / 100f) + " B/s";
	}

	public static String toTitle(Identifier iden, boolean capitalize) {
		String replaced = iden.getPath().replace("_", " ").replace("the ", ""); // TODO: not working, the_nether becomes The nether
		return (capitalize ? StringUtils.capitalize(replaced) : replaced);
	}

	public static String toTitle(String str, boolean capitalize) {
		String replaced = str.replace("_", " ").replace("the ", "");
		return (capitalize ? StringUtils.capitalize(replaced) : replaced);
	}

	private static boolean aliveCache = false;
	public static void updatePos(MinecraftClient client) {
		oldpos = currpos;
		
		Entity camera = client.getCameraEntity();
		if (oldpos == null)
			oldpos = camera.getPos();

		currpos = camera.getPos();
		blockpos = camera.getBlockPos();

		switch (camera.getHorizontalFacing()) {
			case NORTH:
				direction = "N";
				break;
			case SOUTH:
				direction = "S";
				break;
			case EAST:
				direction = "E";
				break;
			default:
				direction = "W";
		}

		boolean alive = client.player.isAlive();
		if (alive != aliveCache) {
			aliveCache = alive;

			if (Config.INSTANCE.HiddenDeathMessage || alive) {
				return;
			}

			String dim = toTitle(client.player.world.getRegistryKey().getValue(), true);
			GameInfo.sendMessage(client, Text.translatable("text.gameinfo.lastdeathposition")
				.formatted(Formatting.YELLOW)
				.append(": §f" + Coords.getCoords() + " §eDim: §f" + dim)
			);
		}
	}

	@Override
	public void update(MinecraftClient client) {
		boolean inNether = client.player.world.getRegistryKey().equals(World.NETHER);
		Vec3d npos = currpos.multiply(inNether ? overworldMult : netherMult); //inNether ? toOverworld() : toNether();

		// Text text = new LiteralText("§eXYZ: §f%s §e(")
		// 	.append(Utils.getDirection())
		// 	.append("§e) " + (inNether ? "O" : "N") + "-XZ: ")
		// 	.append("§f" + (long)npos.getX() + " " + (long)npos.getZ());

		this.text = String.format(
			"§eXYZ: §f%s §e(§f%s§e) " + (inNether ? "O" : "N") + "-XZ: §f%d %d",
			getCoords(),
			direction,
			(long)npos.getX(),
			(long)npos.getZ()
		);
	}
}
