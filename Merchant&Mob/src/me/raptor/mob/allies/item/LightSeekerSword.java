package me.raptor.mob.allies.item;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class LightSeekerSword extends AbstractItem {
	
	public static ItemStack sword() {
		ItemStack s =  new ItemStack(Material.IRON_SWORD);
		ItemMeta sm = s.getItemMeta();
		sm.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Lightseeker Sword");
		sm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sm.setLore(Arrays.asList(ChatColor.AQUA + "For the King!"));
		sm.setLocalizedName("Lightsword");
		s.setItemMeta(sm);
		s.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		s.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 13);
		return s;
	}

}
