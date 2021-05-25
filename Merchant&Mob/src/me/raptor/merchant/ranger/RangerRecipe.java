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
	
	static Merchant merchant = Bukkit.createMerchant("Ranger");
	static List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
	
	public static Merchant getMerchant() {
		return merchant;
	}
	
	public static void setRecipe() {
		//basic currency
		addBasicCurrencyRecipe(merchantRecipes);
		//vanilla items
		//this list is copy-pasted from wandererRecipe. Item should be changed later
		addRecipe(new ItemStack(Material.COOKED_BEEF, 5), new ItemStack(Material.EMERALD, 4), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.COBBLESTONE, 64), Currency.penny(23), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.STONE, 64), Currency.penny(35), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.OAK_PLANKS, 64), Currency.silverCoin(1), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.TORCH, 64), Currency.penny(52), 3, true , merchantRecipes);
		addRecipe(new ItemStack(Material.SADDLE), Currency.silverCoin(2), 3, true , merchantRecipes);
		//custom items
		addRecipe(SwiftwindBoots.boots(), Currency.goldCoin(2), 3, true , merchantRecipes);
		addRecipe(WoodenLongbow.longbow(), Currency.goldCoin(3), 3, true , merchantRecipes);
		//
		setRecipe(merchant, merchantRecipes);
	}
	
	
	

}
