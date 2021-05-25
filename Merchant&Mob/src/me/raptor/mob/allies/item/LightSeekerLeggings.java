package me.raptor.mob.allies.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class LightSeekerLeggings extends AbstractItem {
	
	public static ItemStack leg() {
		ItemStack s =  new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lightseeker Leggings");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Lightleg");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);
		return s;
	}

}
