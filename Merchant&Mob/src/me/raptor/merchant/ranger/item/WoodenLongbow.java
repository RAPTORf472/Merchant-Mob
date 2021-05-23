package me.raptor.merchant.ranger.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class WoodenLongbow extends AbstractItem {
	
	public static ItemStack longbow() {	
		ItemStack e =  new ItemStack(Material.BOW);
		ItemMeta em = e.getItemMeta();
		em.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wooden Longbow");
		em.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		em.setLore(Arrays.asList(ChatColor.RED + "Legendary bow whose power is doubled"));
		em.setLocalizedName("longbow");
		e.setItemMeta(em);
		e.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		return e;
	}

}
