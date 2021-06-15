package me.raptor.merchant.wanderer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;



public class WandererListener extends AbstractEntityListener {
	
	//Called when a Wanderer is spawn
	
	Plugin plugin;
	NamespacedKey key;
	//skin file
	File file;
	
	public WandererListener(Plugin plugin, String path) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Wanderer");
		file = new File(path + "wanderer.png");
		WandererRecipe.setRecipe();
	}
	
	//Prefix for the Wanderer's dialogue
	public String prefix() {
		return MerchantConversation.colored("&1[&eWanderer&1]&6: &r");
	}
	
	@EventHandler
	public void onWandererSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof AbstractVillager) {
					AbstractVillager v = (AbstractVillager) e.getEntity(); 
					if (checkName(v, "Wanderer")) {
						setKey(v, "Wanderer", key);
						/**MythicMobs outside option makes spawn rate drops significantly. So the Wanderer will
						 * be pushed on the ground when it's spawned.
						 * Note that the getHighestBlockAt() method may push the Ranger too high if there is 
						 * a higher non-air block at the given location, which results in the Ranger's disappearance
						 */
						v.teleport(v.getWorld().getHighestBlockAt(v.getLocation()).getLocation().add(0, 2, 0));
						v.setCustomName(ChatColor.DARK_GREEN + "Ranger");
						v.setCustomNameVisible(true);
						MerchantConversation.activateSpeakingAbility(v, "Wanderer", 10, 30, prefix());
						setSkin(v, file, ChatColor.YELLOW + "Wanderer");
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	//open trade gui when the player right click
	@EventHandler
	public void onWandererClick(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof AbstractVillager)) return;
		AbstractVillager v = (AbstractVillager) e.getRightClicked();
		if (checkKey(v, "Wanderer", key)) 
			WandererRecipe.openTradeMenu(e.getPlayer(), WandererRecipe.getMerchant());
	}
	
}
