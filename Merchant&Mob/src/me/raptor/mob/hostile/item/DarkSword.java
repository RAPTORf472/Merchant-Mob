package me.raptor.mob.hostile.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class DarkSword extends AbstractItem {
	
	public static ItemStack sword() {
		ItemStack s =  new ItemStack(Material.IRON_SWORD);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "Dark Sword");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Darksword");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		return s;
	}

}
