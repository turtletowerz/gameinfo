package com.turtletowerz.gameinfo;

import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
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

		DAY = new TranslatableTextContent("text.gameinfo.day").formatted(Formatting.YELLOW);
		TIME = new TranslatableTextContent("text.gameinfo.time");
		BIOME = new TranslatableTextContent("text.gameinfo.biome");
		LIGHT = new TranslatableTextContent("text.gameinfo.light");
		SPEED = new TranslatableTextContent("text.gameinfo.speed");
	
		DISABLEDEATHMESSAGE = new TranslatableTextContent("text.gameinfo.disabledeathmessage");
		ENABLEDEATHMESSAGE = new TranslatableTextContent("text.gameinfo.enabledeathmessage");
		DISABLESHOWINFO = new TranslatableTextContent("text.gameinfo.disableshowinfo");
		ENABLESHOWINFO = new TranslatableTextContent("text.gameinfo.enableshowinfo");
	}
}
