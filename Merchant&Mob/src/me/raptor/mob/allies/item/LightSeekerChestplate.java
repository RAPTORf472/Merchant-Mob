package me.raptor.mob.allies.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class LightSeekerChestplate extends AbstractItem {
	
	public static ItemStack chestplate() {
		ItemStack s =  new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lightseeker Chestplate");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Lightchest");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		return s;
	}

}
