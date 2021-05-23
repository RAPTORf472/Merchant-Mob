package me.raptor.merchant.currency;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class Currency {
	// Consist of money related method
	int worth;
	static NamespacedKey key;
	
	public Currency(Plugin plugin) {
		key = new NamespacedKey(plugin, "currency");
	}

	public int getWorth(Currency currency) {
		return currency.worth;
	}
	
	// a tool to check currency later
	public static void setTrademark(ItemStack p, NamespacedKey key, String trademark) {
		p.getItemMeta().getPersistentDataContainer().set(key, PersistentDataType.STRING, trademark);
	}
	
	public static boolean checkCurrency(ItemStack c, NamespacedKey key, String check) {
		String trademark = c.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
		if (check == null) return false;
		else return (check.equals(trademark));
	}
	
	public static ItemStack createCurrency(Material m, int count, String displayName, String trademark) {
		ItemStack gc = new ItemStack(m, count);
		ItemMeta gcm = gc.getItemMeta();
		gcm.setDisplayName(displayName);
		setTrademark(gc, key, trademark);
		gcm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gcm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gc.setItemMeta(gcm);
		gc.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		return gc;
	}
	// 1 credit = 20 gold coins
	public static ItemStack credit(int count) {
		return createCurrency(Material.PAPER, count, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Credit", "goldcoin");
	}
	// 1 gold coin = 10 silver coins
	public static ItemStack goldCoin(int count) {
		return createCurrency(Material.GOLD_NUGGET, count, ChatColor.GOLD + "" + ChatColor.BOLD + "Gold Coin", "goldcoin");
	}
	// 1 silver coins = 10 penny
	public static ItemStack silverCoin(int count) {
		return createCurrency(Material.IRON_NUGGET, count, ChatColor.WHITE + "" + ChatColor.BOLD + "Silvercoin", "goldcoin");
	}
	// 1 penny = 1 poisonous potato?
	public static ItemStack penny(int count) {
		return createCurrency(Material.SUNFLOWER, count, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Penny", "goldcoin");
	}
	
}
