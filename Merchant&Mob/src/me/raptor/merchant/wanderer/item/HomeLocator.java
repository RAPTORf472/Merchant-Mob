package me.raptor.merchant.wanderer.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class HomeLocator extends AbstractItem {
	
	public static ItemStack homeLocator() {
		ItemStack h =  new ItemStack(Material.COMPASS);
		ItemMeta hm = h.getItemMeta();
		hm.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Home Locator");
		hm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		hm.setLore(Arrays.asList(ChatColor.AQUA + "Right click to mark your house"));
		hm.setLocalizedName("Home");
		h.setItemMeta(hm);
		h.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		return h;
	}
}
