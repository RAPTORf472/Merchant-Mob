package me.raptor.merchant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import me.raptor.merchant.currency.Currency;

public abstract class AbstractMerchantRecipe {
	
	static List<MerchantRecipe> merchantRecipes = new ArrayList<MerchantRecipe>();
	
	//Add a recipe with 1 ingredient
	public static void addRecipe(ItemStack item, ItemStack ingredient1, ItemStack ingredient2, int maxUse, boolean expReward, List<MerchantRecipe> merchantRecipes) {
		MerchantRecipe recipe = new MerchantRecipe(item, maxUse);
		recipe.setExperienceReward(expReward);
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		merchantRecipes.add(recipe);
	}

	//Add a recipe with 2 ingredients
	public static void addRecipe(ItemStack item, ItemStack ingredient1, int maxUse, boolean expReward, List<MerchantRecipe> merchantRecipes) {
		MerchantRecipe recipe = new MerchantRecipe(item, maxUse);
		recipe.setExperienceReward(expReward);
		recipe.addIngredient(ingredient1);
		merchantRecipes.add(recipe);
	}
	
	//set merchant's recipe list
	public static void setRecipe(Merchant merchant, List<MerchantRecipe> recipe) {
		merchant.setRecipes(recipe);
	}
	
	public static void openTradeMenu(Player p, Merchant merchant) {
		p.openMerchant(merchant, true);
	}
	
	public static void addBasicCurrencyRecipe() {
		addRecipe(Currency.penny(1), new ItemStack(Material.POISONOUS_POTATO, 1), 10000, true , merchantRecipes);
		addRecipe(Currency.penny(10), new ItemStack(Material.EMERALD, 1), 10000, true , merchantRecipes);
		addRecipe(Currency.silverCoin(1), Currency.penny(10), 10000, true , merchantRecipes);
		addRecipe(Currency.silverCoin(7), Currency.penny(64), 10000, true , merchantRecipes);
		addRecipe(Currency.silverCoin(15), Currency.penny(64), Currency.penny(64), 10000, true , merchantRecipes);
		addRecipe(Currency.goldCoin(1), Currency.silverCoin(10), 10000, true , merchantRecipes);
		addRecipe(Currency.goldCoin(7), Currency.silverCoin(64), 10000, true , merchantRecipes);
		addRecipe(Currency.credit(1), Currency.goldCoin(20), 10000, true , merchantRecipes);
	}
}
