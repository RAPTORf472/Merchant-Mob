package me.raptor.merchant.currency;

import java.util.Arrays;
import java.util.List;

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
	private static NamespacedKey key;
	private static List<String> currencies = Arrays.asList("credit", "goldcoin", "silvercoin", "penny");
	public Currency(Plugin plugin) {
		key = new NamespacedKey(plugin, "currency");
	}
	
	public static NamespacedKey getKey() {
		return key;
	}
	
	// a tool to check currency later
	public static void setTrademark(ItemStack i, NamespacedKey key, String trademark) {
		i.getItemMeta().getPersistentDataContainer().set(key, PersistentDataType.STRING, trademark);
	}
	//doesn't seem to work
	public static boolean checkCurrencyKey(ItemStack c, NamespacedKey key, String check) {
		String trademark = c.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
		if (trademark == null) return false;
		else return (check.equals(trademark));
	}
	//doesn't seem to work
	public static boolean checkCurrencyKey(ItemStack i, NamespacedKey key) {
		if (i.getItemMeta() == null) return false;
		String trademark = i.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
		if (trademark == null) return false;
		else return (currencies.contains(trademark));
	}
	public static boolean checkCurrencyName(ItemStack i, String name) {
		if (i.getItemMeta() == null) return false;
		if (i.getItemMeta().getLocalizedName() == null) return false;
		else return (i.getItemMeta().getLocalizedName().equals(name));
	}
	public static boolean checkCurrencyName(ItemStack i) {
		if (i == null) return false;
		if (i.getItemMeta() == null) return false;
		if (i.getItemMeta().getLocalizedName() == null) return false;
		else return (currencies.contains(i.getItemMeta().getLocalizedName()));
	}
	
	public static ItemStack createCurrency(Material m, int count, String displayName, String trademark) {
		ItemStack gc = new ItemStack(m, count);
		ItemMeta gcm = gc.getItemMeta();
		gcm.setDisplayName(displayName);
		gcm.setLocalizedName(trademark);
		setTrademark(gc, key, trademark);
		gcm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gcm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gc.setItemMeta(gcm);
		gc.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		return gc;
	}
	// 1 credit = 20 gold coins
	public static ItemStack credit(int count) {
		return createCurrency(Material.PAPER, count, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Credit", "credit");
	}
	// 1 gold coin = 10 silver coins
	public static ItemStack goldCoin(int count) {
		return createCurrency(Material.GOLD_NUGGET, count, ChatColor.GOLD + "" + ChatColor.BOLD + "Gold Coin", "goldcoin");
	}
	// 1 silver coins = 10 penny
	public static ItemStack silverCoin(int count) {
		return createCurrency(Material.IRON_NUGGET, count, ChatColor.WHITE + "" + ChatColor.BOLD + "Silvercoin", "silvercoin");
	}
	// 1 penny = 1 poisonous potato?
	public static ItemStack penny(int count) {
		return createCurrency(Material.SUNFLOWER, count, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Penny", "penny");
	}

	
}
