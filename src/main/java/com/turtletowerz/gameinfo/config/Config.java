package com.turtletowerz.gameinfo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.turtletowerz.gameinfo.Render;
import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = "gameinfo")
public class Config implements ConfigData {
	// public final int version = 15;
	public static Config INSTANCE;

	public static void init() {
		INSTANCE = new Config();
		// TODO: above is where settings would be read from file
	}

	public static void save() {
		// TODO: save INSTANCE to the settings file
		// also maybe overload with a boolean to only update?

		INSTANCE.update();
		Render.update();
	}

	@Entry(Comment = "If true, hides the entire GUI, but not the death message")
	public boolean Hidden = false;

	@Entry(Comment = "If true, hides the death message, but not the GUI")
	public boolean HiddenDeathMessage = false;

	@Entry(Comment = "If true, hides the speed of the player")
	public boolean HiddenSpeed = true;

	@Entry(Comment = "If true, hides the biome of the player")
	public boolean HiddenBiome = false;

	@Entry(Comment = "If true, hides the FPS of the player")
	public boolean HiddenFPS = false;

	@Entry(Comment = "If true, hides the both block and sun light levels")
	public boolean HiddenLight = false;

	@Entry(Comment = "If true, hides the block light level")
	public boolean HiddenBlockLight = false;

	@Entry(Comment = "If true, hides the sun light level")
	public boolean HiddenSunLight = false;


	@Entry(Category = "UI", Comment = "If true, the GUI will appear on the right half of the screen")
	public boolean RightHUD = false;

	@Entry(Category = "UI", Comment = "If true, the GUI will appear on the bottom half of the screen")
	public boolean DownHUD = false;

	@Entry(Category = "UI", Comment = "If true, the GUI lines will be sorted by width")
	public boolean SortWidth = false;

	@Entry(Category = "UI", Comment = "If true, the GUI will be sorted in their reverse order")
	public boolean SortReverse = false;

	@Entry(Category = "UI", Max = 300, Type = "IntSlider", Comment = "The X scaling of the GUI")
	public int ScaleX = 100;

	@Entry(Category = "UI", Max = 300, Type = "IntSlider", Comment = "The Y scaling of the GUI")
	public int ScaleY = 100;

	//Entry(Excluded = true)
	private float _ScaleX = 0;

	//Entry(Excluded = true)
	private float _ScaleY = 0;

	private void update() {
		_ScaleX = ScaleX / 100f; //(0.5f + (ScaleX - 1) * 0.005f);
		_ScaleY = ScaleY / 100f; //(0.5f + (ScaleY - 1) * 0.005f);
	}

	public float getScaleX() {
		return _ScaleX;
	}

	public float getScaleY() {
		return _ScaleY;
	}


	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Entry {
		String Category() default "general";
		String Type()     default "BooleanToggle";
		String Comment()  default "N/A";
		int Min()         default 1;
		int Max()         default 1;
	}
}
