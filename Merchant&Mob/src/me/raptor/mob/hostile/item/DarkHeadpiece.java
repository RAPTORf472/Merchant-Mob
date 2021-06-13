package me.raptor.mob.hostile.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class DarkHeadpiece extends AbstractItem {
	
	public static ItemStack helmet() {
		ItemStack s =  new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "Dark Headpiece");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Darkhead");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
		return s;
	}

}
