package com.turtletowerz.gameinfo.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import java.lang.reflect.Field;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

// Integrates GameInfo with the ModMenu API
public class ModMenu implements ModMenuApi {
	private static final Config defaults = new Config();

	@SuppressWarnings("rawtypes")
	public static ConfigBuilder getConfigBuilderScreen() {
		ConfigBuilder builder = ConfigBuilder.create().transparentBackground().setTitle(new TranslatableText("config.gameinfo.main"));
		ConfigEntryBuilder eb = builder.entryBuilder();
		ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("config.gameinfo.main"));
		SubCategoryBuilder gui = eb.startSubCategory(new TranslatableText("config.gameinfo.ui"));

		try {
			for (Field f : Config.class.getFields()) {
				f.getAnnotation(Config.Entry.class);
				//Config.Entry e = (Config.Entry)Arrays.stream(f.getAnnotations()).filter(a -> a instanceof Config.Entry).findFirst().orElse(null);
				Config.Entry e = f.getAnnotation(Config.Entry.class);
				if (e == null)
					continue;

				AbstractConfigListEntry entry = null;

				String Key = "config.gameinfo." + f.getName().toLowerCase();
				switch (e.Type()) {
					case "BooleanToggle":
						entry = eb.startBooleanToggle(new TranslatableText(Key), f.getBoolean(Config.INSTANCE))
							.setDefaultValue(f.getBoolean(defaults))
							.setTooltip(new LiteralText(e.Comment()))
							.setSaveConsumer(v -> {
								try {
									f.set(Config.INSTANCE, v);
								} catch (IllegalAccessException ex) {
									ex.printStackTrace();
								}
							})
							.build();
						break;
					case "IntSlider":
						entry = eb.startIntSlider(new TranslatableText(Key), f.getInt(Config.INSTANCE), e.Min(),  e.Max())
							.setDefaultValue(f.getInt(defaults))
							.setTooltip(new LiteralText(e.Comment()))
							.setTextGetter(integer -> new LiteralText("Mode: " + integer))
							.setSaveConsumer(v -> {
								try {
									f.set(Config.INSTANCE, v);
								} catch (IllegalAccessException ex) {
									ex.printStackTrace();
								}
							})
							.build();
						break;
				}

				switch (e.Category()) { // TODO: this could probably be done in a better way
					case "UI":
						gui.add(entry);
						break;
					default:
						general.addEntry(entry);
				}
			}
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

		general.addEntry(gui.build());
		return builder.setSavingRunnable(Config::save);
	}

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> getConfigBuilderScreen().setParentScreen(parent).build();
	}
}
