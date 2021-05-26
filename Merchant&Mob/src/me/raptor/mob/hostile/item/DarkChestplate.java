package me.raptor.mob.hostile.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class DarkChestplate extends AbstractItem {
	
	public static ItemStack chestplate() {
		ItemStack s =  new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "Dark Chestplate");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Darkchest");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		return s;
	}

}
