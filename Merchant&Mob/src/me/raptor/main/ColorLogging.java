package me.raptor.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ColorLogging {
	
	public static String formatted(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static void Logging(String s) {
		Bukkit.getServer().getConsoleSender().sendMessage(formatted(s));
	}
	
	
}
