package com.turtletowerz.gameinfo;

import com.turtletowerz.gameinfo.config.Config;
import com.turtletowerz.gameinfo.lines.*;
import java.util.ArrayList;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Render {
	private static boolean updating = false;

	private static ArrayList<Line> lines = new ArrayList<Line>();

	public static void init() {
		lines.add(new Coords());
		lines.add(new Biome());
		lines.add(new Date());
		lines.add(new FPS());

		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).pos = i;
		}

		HudRenderCallback.EVENT.register((matricies, tickDelta) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			Coords.updatePos(client);
			
			if (Config.INSTANCE.Hidden || updating)
				return;
			
			render(client, matricies);
		});
	}

	public static float getLineHeight() {
		return (9 * Config.INSTANCE.getScaleY());
	}

	// This is called to specifically re-arrange the lines, not the text within them
	// The width of the line changes every frame so we don't update that here, but the y scale does
	public static void update() {
		updating = true;
		if (Config.INSTANCE.SortWidth) {
			if (Config.INSTANCE.SortReverse) {
				lines.sort((a, b) -> (a.getWidth() <= b.getWidth()) ? -1 : 1);
			} else {
				lines.sort((a, b) -> (a.getWidth() >= b.getWidth()) ? -1 : 1);
			}
		} else {
			if (Config.INSTANCE.SortReverse) {
				lines.sort((a, b) -> (a.pos >= b.pos) ? -1 : 1);
			} else {
				lines.sort((a, b) -> (a.pos <= b.pos) ? -1 : 1);
			}
		}

		float y = 4;
		float ScaleY = Config.INSTANCE.getScaleY();
		float maxHeight = y + (lines.size() * getLineHeight());

		if (Config.INSTANCE.DownHUD)
			y = (MinecraftClient.getInstance().getWindow().getScaledHeight() - maxHeight * ScaleY);

		for (Line L : lines) {
			L.setY(y / ScaleY);
			y += L.getHeight() * ScaleY;
		}

		updating = false;
	}

	public static void rotate() {
		lines.forEach(L-> { L.pos = (L.pos + 1) % lines.size(); });
		update();
	}

	public static void render(MinecraftClient client, MatrixStack matrices) {
		lines.forEach(L -> {L.update(client); L.updateWidth(client); });

		matrices.push();
		if (!(Config.INSTANCE.getScaleX() == 1 && Config.INSTANCE.getScaleX() == 1)) // Check to see if they even need to be scaled
			matrices.scale(Config.INSTANCE.getScaleX(), Config.INSTANCE.getScaleY(), 1);

		lines.forEach(L -> L.draw(matrices, client.textRenderer));
		matrices.pop();
	}
}
