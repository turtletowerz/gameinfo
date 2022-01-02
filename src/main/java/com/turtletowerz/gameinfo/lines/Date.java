package com.turtletowerz.gameinfo.lines;

import com.turtletowerz.gameinfo.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.LightType;

public class Date extends Line {
	private final long DAY = 24000L;
	private final long HOUR = 1000L;
	private final float MINUTE = 16.66F;

	private long day;
	private long hour;
	private long minute;

	private static int sunlight;
	private static int blocklight;
	private static final int LIGHTTHRESHOLD = 0; // This changed from 7 in 1.18, so it would be nice to keep a constant field to future-proof any changes

	public String getDayString() {
		return ((day < 10) ? "0" : "") + day;
	}

	public String getHourString() {
		return ((hour < 10) ? "0" : "") + hour;
	}

	public String getMinuteString() {
		return ((minute < 10) ? "0" : "") + minute;
	}

	private String formatLight(int light) {
		String ret = Integer.toString(light);
		if (light < 10) {
			ret = "0" + ret;
		}

		if (light <= LIGHTTHRESHOLD) {
			ret = "§c" + ret;
		} else {
			ret = "§f" + ret;
		}
		return ret;
	}

	@Override
	public void update(MinecraftClient client) {
		long time = client.world.getTimeOfDay();

		day = time / DAY;
		time = ((time % DAY) + HOUR * 6) % DAY;
		hour = time / HOUR;
		minute = (long)((time % HOUR) / MINUTE);

		this.text = "§eDate: §fDay " + getDayString() + " " + getHourString() + ":" + getMinuteString();

		// If both types of light are hidden we return just the date
		if (Config.INSTANCE.HiddenLight || (Config.INSTANCE.HiddenSunLight && Config.INSTANCE.HiddenBlockLight))
			return;

		sunlight = client.world.getLightLevel(LightType.SKY, Coords.getBlockPos());
		blocklight = client.world.getLightLevel(LightType.BLOCK, Coords.getBlockPos());

		this.text += " §e- Light: ";
		if (Config.INSTANCE.HiddenSunLight) { // If the block light is the only visible light
			this.text += formatLight(blocklight);
		} else {
			if (!Config.INSTANCE.HiddenBlockLight) {
				this.text += formatLight(blocklight) + "§f/";
			}
			this.text += formatLight(sunlight);
		}
	}
}
