package me.raptor.merchant.wanderer.item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.raptor.merchant.abstractitem.AbstractItem;

public class HomeLocatorListener implements Listener {
	
	Plugin plugin;
	
	public HomeLocatorListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	//Save the player's location in the compass. Suitable for cave marking and such.
	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent e) {
		//similar to set home function
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getHand() == EquipmentSlot.HAND) {
				Player p = e.getPlayer();
				ItemStack i = p.getInventory().getItemInMainHand();
				if (AbstractItem.checkItem(i, HomeLocator.homeLocator())) {					
					p.setCompassTarget(p.getLocation());
					p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Home set!");
				}
			}
		}
	}
	
	
}
