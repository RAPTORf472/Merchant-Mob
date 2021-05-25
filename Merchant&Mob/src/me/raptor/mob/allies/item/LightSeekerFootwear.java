package me.raptor.mob.allies.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class LightSeekerFootwear extends AbstractItem {
	
	public static ItemStack boot() {
		ItemStack s =  new ItemStack(Material.IRON_BOOTS);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lightseeker Footwear");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Lightfoot");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
		return s;
	}

}
