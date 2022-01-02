package com.turtletowerz.gameinfo.lines;

import com.turtletowerz.gameinfo.config.Config;
import com.turtletowerz.gameinfo.mixin.FPSClientMixin;
import net.minecraft.client.MinecraftClient;

public class FPS extends Line {
	@Override
	public void update(MinecraftClient client) {
		if (Config.INSTANCE.HiddenFPS && Config.INSTANCE.HiddenSpeed) {
			return;
		}

		this.text = "";
		if (!Config.INSTANCE.HiddenFPS) {
			this.text += "§eFPS: §f" + ((FPSClientMixin)client).getCurrentFPS();
		}

		if (!Config.INSTANCE.HiddenSpeed) {
			if (!Config.INSTANCE.HiddenFPS) {
				this.text += " §e- ";
			}
			this.text += "Speed: §f" + Coords.getVelocity();
		}
	}
}
