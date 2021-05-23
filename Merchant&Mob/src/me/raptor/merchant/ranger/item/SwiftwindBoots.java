package me.raptor.merchant.ranger.item;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.raptor.merchant.abstractitem.AbstractItem;

public class SwiftwindBoots extends AbstractItem {
	public static ItemStack boots() {	
		AttributeModifier armorValue = new AttributeModifier(UUID.randomUUID(), "armorValue", 1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		AttributeModifier durability = new AttributeModifier(UUID.randomUUID(), "armorValue", 481 - 65, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		ItemStack e =  new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta em = (LeatherArmorMeta) e.getItemMeta();
		em.setColor(Color.fromRGB(119, 179, 0));
		em.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Swiftwind Boots");
		em.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		em.setLore(Arrays.asList(ChatColor.GREEN + "A lifetime training of parrying in this pair of boots"));
		em.setLocalizedName("swiftwindboots");
		em.addAttributeModifier(Attribute.GENERIC_ARMOR, armorValue);
		em.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, durability);
		e.setItemMeta(em);
		e.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 6);
		return e;
	}
}
