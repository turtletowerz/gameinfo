package com.turtletowerz.gameinfo;

import com.turtletowerz.gameinfo.config.Config;
import com.turtletowerz.gameinfo.config.Keybind;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

// TODO: all the modules need to be updated to support translations
// TODO: check GameOptions class to see if you can hook when the langauge changes
public class GameInfo implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TranslatedText.init();
		Config.init();
		Keybind.init();
		Render.init();
		Config.save();
	}

	public static void sendMessage(MinecraftClient client, Text text) {
		client.player.sendSystemMessage(text, Util.NIL_UUID);
	}
}
