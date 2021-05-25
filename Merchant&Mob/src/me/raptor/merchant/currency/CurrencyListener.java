package me.raptor.merchant.currency;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.raptor.main.Main;

public class CurrencyListener implements Listener {
	
	Main plugin;
	
	public CurrencyListener(Main plugin) {
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	//penny place event
	@EventHandler
	public void onCurrencyUsedWrongly(BlockPlaceEvent e) {
		ItemStack i = e.getItemInHand();
		if (Currency.checkCurrencyName(i, "penny")) {
			e.setCancelled(true);
		}
	}
	
	//crafting recipe using currency
	@EventHandler
	public void onCurrencyUsedWrongly(CraftItemEvent e) {
		for (ItemStack i : e.getInventory().getMatrix()) {
			if (Currency.checkCurrencyName(i)) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	public void onCurrencyUsedWrongly(PlayerInteractEntityEvent e) {
		ItemStack right = e.getPlayer().getInventory().getItemInMainHand();
		ItemStack left = e.getPlayer().getInventory().getItemInOffHand();
		if (left != null && right != null) {
			if (Currency.checkCurrencyName(right) ||
				Currency.checkCurrencyName(left)) {
				e.setCancelled(true);
			}
		}
	}
	
	
	
}
