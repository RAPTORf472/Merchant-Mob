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
	
	
}
