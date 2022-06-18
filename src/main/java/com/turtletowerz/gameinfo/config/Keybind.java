package com.turtletowerz.gameinfo.config;

import com.turtletowerz.gameinfo.GameInfo;
import com.turtletowerz.gameinfo.Render;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class Keybind {
	private static KeyBinding KEYToggleGUI = new KeyBinding("key.gameinfo.toggleshow", GLFW.GLFW_KEY_I, "GameInfo");
	private static KeyBinding KEYToggleDeathMessage = new KeyBinding("key.gameinfo.deathmessage", GLFW.GLFW_KEY_UNKNOWN, "GameInfo");
	private static KeyBinding KEYOpenSettings = new KeyBinding("key.gameinfo.opensettings", GLFW.GLFW_KEY_P, "GameInfo");
	private static KeyBinding KEYRotateHud = new KeyBinding("key.gameinfo.rotatehud", GLFW.GLFW_KEY_UNKNOWN, "GameInfo");

	public static void check(MinecraftClient client) {
		if (KEYOpenSettings.wasPressed()) {
			client.setScreen(ModMenu.getConfigBuilderScreen().build());
		}

		if (KEYToggleDeathMessage.wasPressed()) {
			Config.INSTANCE.HiddenDeathMessage = !Config.INSTANCE.HiddenDeathMessage;
			Config.save();

			GameInfo.sendMessage(
					client,
					Text.translatable(Config.INSTANCE.Hidden ? "text.gameinfo.disabledeathmessage" : "text.gameinfo.enabledeathmessage")
					.formatted(Formatting.BOLD)
					.formatted(Formatting.YELLOW)
			);
		}

		if (KEYToggleGUI.wasPressed()) {
			Config.INSTANCE.Hidden = !Config.INSTANCE.Hidden;
			Config.save();
			
			GameInfo.sendMessage(
					client,
					Text.translatable(Config.INSTANCE.Hidden ? "text.gameinfo.disableshowinfo" : "text.gameinfo.enableshowinfo")
					.formatted(Formatting.BOLD)
					.formatted(Formatting.YELLOW)
			);
		}

		if (KEYRotateHud.wasPressed()) {
			Render.rotate();
		}
	}

	public static void init() {
		KeyBindingHelper.registerKeyBinding(KEYToggleGUI);
		KeyBindingHelper.registerKeyBinding(KEYToggleDeathMessage);
		KeyBindingHelper.registerKeyBinding(KEYOpenSettings);
		KeyBindingHelper.registerKeyBinding(KEYRotateHud);
	}
}
