package me.raptor.merchant.wanderer.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class Exterminator extends AbstractItem {
	
	public static ItemStack exterminator() {	
		ItemStack e =  new ItemStack(Material.IRON_SWORD);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Exterminator");
		em.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		em.setLore(Arrays.asList(ChatColor.AQUA + "Zap nearby monsters"));
		em.setLocalizedName("exterminator");
		e.setItemMeta(em);
		e.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		return e;
	}
	
}
