package me.raptor.merchant.ranger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import me.raptor.merchant.AbstractMerchantRecipe;
import me.raptor.merchant.currency.Currency;
import me.raptor.merchant.ranger.item.SwiftwindBoots;
import me.raptor.merchant.ranger.item.WoodenLongbow;

public class RangerRecipe extends AbstractMerchantRecipe {
	
	static private boolean hasRecipe = false;

	static Merchant merchant = Bukkit.createMerchant("Ranger");
	static List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
	
	public static Merchant getMerchant() {
		return merchant;
	}
	
	public static void setRecipe() {
		if (hasRecipe) return;
		//basic currency
		addBasicCurrencyRecipe(merchantRecipes);
		addRecipe(Currency.penny(1), new ItemStack(Material.ANDESITE, 3), 10000, true , merchantRecipes);
		//vanilla items
		//this list is copy-pasted from wandererRecipe. Item should be changed later
		addRecipe(new ItemStack(Material.SCAFFOLDING, 2), new ItemStack(Material.EMERALD, 1), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.GREEN_BED, 1), Currency.silverCoin(1), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.HONEY_BOTTLE, 1), Currency.penny(15), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.IRON_AXE, 1), Currency.silverCoin(4), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.COOKED_RABBIT, 3), Currency.silverCoin(2), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.BONE), Currency.penny(7), 3, true , merchantRecipes);
		//custom items
		addRecipe(SwiftwindBoots.boots(), Currency.goldCoin(2), 3, true , merchantRecipes);
		addRecipe(WoodenLongbow.longbow(), Currency.goldCoin(3), 3, true , merchantRecipes);
		//
		setRecipe(merchant, merchantRecipes);
		hasRecipe = true;
	}
	
	
	

}
