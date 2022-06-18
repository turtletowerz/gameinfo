package com.turtletowerz.gameinfo;

import com.turtletowerz.gameinfo.config.Config;
import com.turtletowerz.gameinfo.config.Keybind;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class GameInfo implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Config.init();
		Keybind.init();
		Render.init();
		Config.save();
	}

	public static void sendMessage(MinecraftClient client, Text text) {
		client.player.sendMessage(text);
	}
}
