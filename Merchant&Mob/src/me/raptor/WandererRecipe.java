package me.raptor.merchant.wanderer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import me.raptor.merchant.AbstractMerchantRecipe;
import me.raptor.merchant.currency.Currency;
import me.raptor.merchant.wanderer.item.Exterminator;
import me.raptor.merchant.wanderer.item.HomeLocator;

public class WandererRecipe extends AbstractMerchantRecipe {
	
	static private boolean hasRecipe = false;
	
	static Merchant merchant = Bukkit.createMerchant("Wanderer");
	static List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
	
	public static Merchant getMerchant() {
		return merchant;
	}
	
	public static void setRecipe() {
		if (hasRecipe) return;
		//basic currency
		addBasicCurrencyRecipe(merchantRecipes);
		addRecipe(Currency.penny(1), new ItemStack(Material.POISONOUS_POTATO, 1), 10000, true , WandererRecipe.merchantRecipes);
		//vanilla items
		addRecipe(new ItemStack(Material.COOKED_BEEF, 5), new ItemStack(Material.EMERALD, 4), 10000, true , merchantRecipes);
		addRecipe(new ItemStack(Material.COBBLESTONE, 64), Currency.penny(23), 10000, true , merchantRecipes);
		addRecipe(new ItemStack(Material.STONE, 64), Currency.penny(35), 10000, true , merchantRecipes);
		addRecipe(new ItemStack(Material.OAK_PLANKS, 64), Currency.silverCoin(1), 10000, true , merchantRecipes);
		addRecipe(new ItemStack(Material.TORCH, 64), Currency.penny(52), 10000, true , merchantRecipes);
		addRecipe(new ItemStack(Material.SADDLE), Currency.silverCoin(2), 10000, true , merchantRecipes);
		//custom items
		addRecipe(HomeLocator.homeLocator(), Currency.penny(32), 10000, true , merchantRecipes);
		addRecipe(Exterminator.exterminator(), Currency.goldCoin(2), 10000, true , merchantRecipes);
		setRecipe(merchant, merchantRecipes);
		hasRecipe = true;
	}
	
	
	

}
