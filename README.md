# GameInfo Minecraft Mod
An updated version of ifcifc's mod found [here](https://gitlab.com/ifcifc94/gameinfo-mod), reworked from the ground-up to be more performant and have a cleaner structure so that it is easier to update in future versions of Minecraft, as well as potentially support previous versions in the future.

## The changes
This fork makes a significant number of removals of (what I found to be) unnecessary pieces of the original mod. These are including but not limited to:
- Fixing up the whitespace and general styling inconsistency.
- Removing the ability to toggle certain modules when the toggle for them is already in the settings.
- Removing the json file death points. If the death point is sent as a chat message then it shows up in game logs anyways, so this redundancy is unnecessary
- A general cleanup of the configuration as well as how it is saved.
- The unusual approach of rendering using nashorn and script objects?
    - I don't really understand why this was done this way. If the original author or any third party would like to clarify please do.
- Disabled the speed by default, it rarely has a use but is left in because why not
- Removed the convoluted line formatting and only kept the same format. Users who want to re-arrange can take advantage of the right/down as well as the options to sort by width and in reverse orders to easily configure the GUI beyond the presets provided
- Removed some configuratioin settings in fabric.mod.json that are deprecated (such as the clientsideOnly cusotm modmenu attribute)

### Current known issues
- The configuration does not save yet, this will be in the next update
- The speed setting does not work, so it's disabled by default. I'll fix it in the near future
- Most translations for th e settings and system messages work, but not the UI itself. This will be addressed when I find a better way to render text
- This probably works on previous versions of Minecraft, but I have yet to test them
- Slime chunk notification doesn't seem to appear

## Compiling
- git clone https://github.com/turtletowerz/gameinfo.git
- cd gameinfo
- ./gradlew build
