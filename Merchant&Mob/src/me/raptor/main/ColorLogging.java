package me.raptor.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ColorLogging {
	
	//More features should be added
	
	public static String formatted(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static void Logging(String s) {
		Bukkit.getServer().getConsoleSender().sendMessage(formatted(s));
	}
	
    public static String prefix() {
        return formatted("&8&l[&a&lMerchant&8&l] &e");
    }
	
    public static String getHelpMessage() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&e&m-----------------------------&r &l&8[&a&lMerchant&l&8] &r&e&m-----------------------------\n");
		builder.append("&e&l/&2merchant help &e- Open this help page.\n");
		builder.append("&e&l/&2merchant reload &e- Reload files\n");
		builder.append("&e&l/&2merchant chat &e- Toggle merchant chat\n");
		builder.append("&e&m-----------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getAdminPrecaution() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&e&m-----------------------------&r &l&8[&a&lMerchant&l&8] &r&e&m-----------------------------\n");
		builder.append("&e&m-----------------------&r &l&8[&4&lAdmin Precautions&l&8] &r&e&m---------------------------\n");
		builder.append("&e&l/&cDo not use /reload - Will cause LibDisguises to break down.\n");
		builder.append("&e&l/&cSkin might not load as fast as you wish (or not load at all). Be patient.\n");
		builder.append("&e&l/&cIf there's a bug, report immediately to the server owners (RAPTOR, SthWentWrong)\n");
		builder.append("&e&m-----------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static void onEnableLog() {
        ColorLogging.Logging(prefix() + "&2Plugin enabled!");
        ColorLogging.Logging(prefix() + "&2Feedback to me if you found a bug!");
        ColorLogging.Logging(prefix() + "&2Author: &6RAPTOR");
    }
    
    public static void onDisableLog() {
    	ColorLogging.Logging(prefix() + "&3Plugin disabled!");
    	ColorLogging.Logging(prefix() + "&3Feedback to me if you found a bug!");
    	ColorLogging.Logging(prefix() + "&3Author: &6RAPTOR");
    }
	
}
