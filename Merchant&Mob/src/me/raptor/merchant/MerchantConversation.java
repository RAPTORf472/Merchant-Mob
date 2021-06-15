package me.raptor.merchant;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.main.Main;

public class MerchantConversation {
	
	static Main main;
	static Random r = new Random();
	
	public MerchantConversation(Main main) {
		MerchantConversation.main = main;
	}
	
	public String getDialogue(String path) {
		return main.getCustomDialogueConfig().get(path).toString();
	}
	
	public void setDialogue(String dialogue, String path) {
		main.getCustomDialogueConfig().set(path, dialogue);
		main.saveData();
	}
	
	//Make the entity chat when player is in range. Dialogues and sounds can be found in dialogue.yml.
	//Warning: The amount of sounds and dialogues must be the same
	public static void activateSpeakingAbility(LivingEntity v, String path, int interval, int range, String prefix) {
		if (randomIndex(path + ".dialogue") == - 1) return; // cancel if no message was found
		new BukkitRunnable() {
			@Override
			public void run() {
				if (v == null || v.isDead()) {
					cancel();
					return;
				}
				if (v.isSleeping()) return;
				try {
				speakDialogue(v, range, prefix, path);
				} catch (IndexOutOfBoundsException e) {
					Bukkit.getServer().broadcastMessage("Error: " + path);
					e.printStackTrace();
				}
			}
		}.runTaskTimer(main, 0, 20 * interval);
	}
	
	//Make the entity play sound when player is in range. Sounds can be found in dialogue.yml
	public static void activateSoundAbility(LivingEntity v, String path, int interval) {
		if (randomIndex(path + ".sounds") == - 1) return; // cancel if no sound was found
		new BukkitRunnable() {
			@Override
			public void run() {
				if (v == null || v.isDead()) {
					cancel();
					return;
				}
				v.getWorld().playSound(v.getLocation(), getData(path + ".sounds", randomIndex(path + ".sounds")), 3, 1);
			}
		}.runTaskTimer(main, 0, 20 * interval);
	}
	
	//Send a message to all players in range
	public static void speakDialogue(LivingEntity v, int range, String prefix, String path) {
		for (Entity e : v.getNearbyEntities(range, range, range)) {
			if (e instanceof Player) {
				Player p = (Player) e;
				int index = randomIndex(path + ".dialogue");
				if (index == -1) return;
				// If the player want to see the merchant's dialogue
				if (checkWantToHear(p, p.getName() + ".enableMerchantChat")) {
					p.sendMessage(prefix + getData(path + ".dialogue", index));
					if (getData(path + ".sounds", index) == null) return; 
					p.playSound(p.getLocation(), getData(path + ".sounds", index), 7, 1);
				}
			}
		}
	}
	
	//Send a message and sound to all player in range
	public static void speak(LivingEntity v, int range, String prefix, String path) {
		for (Entity e : v.getNearbyEntities(range, range, range)) {
			if (e instanceof Player) {
				Player p = (Player) e;
				int index = randomIndex(path);
				if (index == -1) return;
				p.sendMessage(prefix + getData(path, index));
			}
		}
	}
	
	//choose random message from the given path in dialogue.yml
	public static int randomIndex(String path) {
		List<String> messages = main.getCustomDialogueConfig().getStringList(path);
		if (messages.size() == 0) return -1;
		return r.nextInt(messages.size());
	}
	
	//get a data from the dialogue config with the given path and index
	public static String getData(String path, int index) {
		List<String> messages = main.getCustomDialogueConfig().getStringList(path);
		if (index > messages.size() - 1) return null;
		return colored(messages.get(index));
	}
	
	//choose a random name from name.yml with given path
	public static String chooseRandomName(String path) {
		List<String> messages = main.getCustomNameConfig().getStringList(path);
		if (messages.size() == 0) return "";
		return colored(messages.get(r.nextInt(messages.size())));
	}
	
	//transform '&' into color codes
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
