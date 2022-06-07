package com.turtletowerz.gameinfo;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;

public class TranslatedText {
	private static Language languageCache;

	public static MutableText DAY;
	public static MutableText TIME;
	public static MutableText BIOME;
	public static MutableText LIGHT;
	public static MutableText SPEED;

	public static MutableText DISABLEDEATHMESSAGE;
	public static MutableText ENABLEDEATHMESSAGE;
	public static MutableText DISABLESHOWINFO;
	public static MutableText ENABLESHOWINFO;

	public static void init() { // TODO: call this every tick?
		Language language = Language.getInstance();
		if (language == languageCache)
			return;
	
		init(language);
	}

	public static void init(Language lang) {
		languageCache = lang;

		DAY = Text.translatable("text.gameinfo.day").formatted(Formatting.YELLOW);
		TIME = Text.translatable("text.gameinfo.time");
		BIOME = Text.translatable("text.gameinfo.biome");
		LIGHT = Text.translatable("text.gameinfo.light");
		SPEED = Text.translatable("text.gameinfo.speed");
	
		DISABLEDEATHMESSAGE = Text.translatable("text.gameinfo.disabledeathmessage");
		ENABLEDEATHMESSAGE = Text.translatable("text.gameinfo.enabledeathmessage");
		DISABLESHOWINFO = Text.translatable("text.gameinfo.disableshowinfo");
		ENABLESHOWINFO = Text.translatable("text.gameinfo.enableshowinfo");
	}
}
