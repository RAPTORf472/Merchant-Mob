package me.raptor.merchant;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.main.Main;

public class MerchantConversation {
	
static Main main;
	
	public MerchantConversation(Main main) {
		MerchantConversation.main = main;
	}
	
	public String getDialogue(String path) {
		return main.getCustomConfig().get(path).toString();
	}
	
	public void setDialogue(String dialogue, String path) {
		main.getCustomConfig().set(path, dialogue);
		main.saveData();
	}
	
	//Make the entity chat when player is in range. Dialogue can be found in dialogue.yml
	public static void activateSpeakingAbility(LivingEntity v, String path, int interval, int range, String prefix) {
		if (chooseRandomMessage(path).equals("")) return; // cancel if no message is found
		new BukkitRunnable() {
			@Override
			public void run() {
				if (v == null || v.isDead()) {
					cancel();
					return;
				}
				for (Entity e : v.getNearbyEntities(range, range, range)) {
					if (e instanceof Player) {
						Player p = (Player) e;
						// If the player want to see the merchant's dialogue
						if (checkWantToHear(p, p.getName() + ".enableMerchantChat")) {
							p.sendMessage(prefix + chooseRandomMessage(path));

						}
					}
				}
			}
		}.runTaskTimer(main, 0, 20 * interval);
	}
	
	//choose random message from the given path in dialogue.yml
	public static String chooseRandomMessage(String path) {
		Random r = new Random();
		List<String> messages = main.getCustomConfig().getStringList(path);
		if (messages.size() == 0) return "";
		return colored(messages.get(r.nextInt(messages.size())));
	}
	
	public static String colored(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	//get the boolean in the given path. Toggle command: /merchant chat on/off
	public static boolean checkWantToHear(Player p, String path) {
		if (!main.getConfig().contains(p.getName() + ".enableMerchantChat")) {
			main.getConfig().set(p.getName() + ".enableMerchantChat", true);
			main.saveConfig();
			return true;
		}
		return main.getConfig().getBoolean(path);
	}
}
