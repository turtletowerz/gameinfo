package com.turtletowerz.gameinfo;

import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
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

		DAY = new TranslatableText("text.gameinfo.day").formatted(Formatting.YELLOW);
		TIME = new TranslatableText("text.gameinfo.time");
		BIOME = new TranslatableText("text.gameinfo.biome");
		LIGHT = new TranslatableText("text.gameinfo.light");
		SPEED = new TranslatableText("text.gameinfo.speed");
	
		DISABLEDEATHMESSAGE = new TranslatableText("text.gameinfo.disabledeathmessage");
		ENABLEDEATHMESSAGE = new TranslatableText("text.gameinfo.enabledeathmessage");
		DISABLESHOWINFO = new TranslatableText("text.gameinfo.disableshowinfo");
		ENABLESHOWINFO = new TranslatableText("text.gameinfo.enableshowinfo");
	}
}
