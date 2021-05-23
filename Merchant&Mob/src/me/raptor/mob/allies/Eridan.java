package me.raptor.mob.allies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;

public class Eridan extends AbstractEntityListener {
	//Incomplete
	Plugin plugin;
	NamespacedKey key;
	
	public Eridan(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Wanderer");
		}
	
	public String prefix() {
		return MerchantConversation.colored("&1[&dEridan&1]&6: &r");
	}
	
	@EventHandler
	public void onEridanSpawn(CreatureSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Skeleton) {
					Skeleton s = (Skeleton) e.getEntity(); 
					if (checkName(s, "Eridan")) {
						setKey(s, "Eridan", key);
						disguisePlayer(s, ChatColor.GREEN + "Eridan");
						MerchantConversation.activateSpeakingAbility(s, "Eridan.dialogue", 10, 30, prefix());
					}
				}
			}
			
		}.runTaskLater(plugin, 1);
	}
}
