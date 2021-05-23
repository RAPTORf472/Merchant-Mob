package me.raptor.merchant.wanderer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;



public class WandererListener extends AbstractEntityListener {
	
	Plugin plugin;
	NamespacedKey key;
	File file = new File("C:\\Users\\admin\\Desktop\\sv\\Summertime\\plugins\\LibsDisguises\\Skins\\wanderer.png");
	//File file = new File("Root:plugins\\LibsDisguises\\Skins\\wanderer.png");
	
	public WandererListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Wanderer");
		WandererRecipe.setRecipe();
	}
	
	public String prefix() {
		return MerchantConversation.colored("&1[&eWanderer&1]&6: &r");
	}
	
	@EventHandler
	public void onWandererSpawn(CreatureSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof AbstractVillager) {
					AbstractVillager v = (AbstractVillager) e.getEntity(); 
					if (checkName(v, "Wanderer")) {
						setKey(v, "Wanderer", key);
						v.teleport(v.getWorld().getHighestBlockAt(v.getLocation()).getLocation().add(0, 2, 0));
						MerchantConversation.activateSpeakingAbility(v, "Wanderer.dialogue", 10, 30, prefix());
						setSkin(v, file, ChatColor.YELLOW + "Wanderer");
					}
				}
			}
			
		}.runTaskLater(plugin, 1);
	}
	
	@EventHandler
	public void onWandererClick(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof AbstractVillager)) return;
		AbstractVillager v = (AbstractVillager) e.getRightClicked();
		if (checkKey(v, "Wanderer", key)) 
			WandererRecipe.openTradeMenu(e.getPlayer(), WandererRecipe.getMerchant());
	}
	
}
