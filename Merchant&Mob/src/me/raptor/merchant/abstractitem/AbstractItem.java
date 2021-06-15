package me.raptor.merchant.abstractitem;

import org.bukkit.inventory.ItemStack;

public class AbstractItem {
	//Consist of item related methods;
	
	public static void setLocalizedName(ItemStack i, String name) {
		i.getItemMeta().setLocalizedName(name);
	}
	
	//Check item by using localized name
	public static boolean checkItem(ItemStack i, ItemStack check) {
		if (i == null || check == null) return false;
		if (i.getItemMeta() == null) return false;
		if (i.getItemMeta().hasLocalizedName() && check.getItemMeta().hasLocalizedName()) {
			String s1 = i.getItemMeta().getLocalizedName();
			String s2 = check.getItemMeta().getLocalizedName();
			return (s1.equals(s2));
		} else return false;
	}
}
