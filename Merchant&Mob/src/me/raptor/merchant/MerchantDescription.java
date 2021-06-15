package me.raptor.merchant;

import org.bukkit.command.CommandSender;

import me.raptor.main.ColorLogging;

public class MerchantDescription {
	
	public static void getDescription(CommandSender c, String s) {
		//plain
		if (s.equalsIgnoreCase("longbow")) {
			c.sendMessage(getLongbowDescription());
		} else if (s.equalsIgnoreCase("swiftwindboots")) {
			c.sendMessage(getSwiftwindBootsDescription());
		} else if (s.equalsIgnoreCase("exterminator")) {
			c.sendMessage(getExterminatorDescription());
		} else if (s.equalsIgnoreCase("locator")) {
			c.sendMessage(getHomeLocatorDescription());
		}  else if (s.equalsIgnoreCase("wanderer")) {
			c.sendMessage(getWandererDescription());
		} else if (s.equalsIgnoreCase("lightseeker")) {
			c.sendMessage(getLightSeekerDescription());
		} else if (s.equalsIgnoreCase("werewolf")) {
			c.sendMessage(getWerewolfDescription());
		} else if (s.equalsIgnoreCase("abomination")) {
			c.sendMessage(getAbominationDescription());
		} else if (s.equalsIgnoreCase("darkknight")) {
			c.sendMessage(getDarkKnightDescription());
		} else if (s.equalsIgnoreCase("evillord")) {
			c.sendMessage(getEvilLordDescription());
		}
		
		//forest
		 else if (s.equalsIgnoreCase("ranger")) {
				c.sendMessage(getRangerDescription());
		}
	}
	
	//Item
    public static String getLongbowDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&4&lWooden Longbow&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lBow\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Longbow:\n");
		builder.append("\n");
		builder.append("&6Double the power, make the arrow go light speed!\n");
		builder.append("&3&l- Crimson Sentence:\n");
		builder.append("&6Have a 5% chance of instakill any mob with health less than 120 (Excluding bosses)\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oWooden Longbow are an accurate, dangerous bow that deal death from far far away\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getSwiftwindBootsDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&2&lSwiftwind Boots&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lBoots\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Swift:\n");
		builder.append("\n");
		builder.append("&6Have a 70% chance to dodge incoming projectile\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oOnce wore by a great elven warrior, Swiftwind Boots now continue to grant its power to the next user\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getExterminatorDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&b&lExterminator&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lSword\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Zap:\n");
		builder.append("\n");
		builder.append("&6Zap 3 - 14 nearby monsters (will ignore player and other mobs), dealing 80% damage each zap\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&o100% bugs and zombies free\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getHomeLocatorDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&f&lHome Locator&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lUltilities Item\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Marking:\n");
		builder.append("\n");
		builder.append("&6Mark current location\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oAfraid of losing your house? One right click and everything is set\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    //Mob
    //Hostile
    public static String getWerewolfDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&1&lWerewolf&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lHostile Mob\n");
		builder.append("&6&lHealth: &c&l40\n");
		builder.append("&6&lDamage: &c&l8\n");
		builder.append("&6&lSpeed: &c&l0.3 (Average)\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Huge jaw:\n");
		builder.append("\n");
		builder.append("&6Can tear a chunk of your flesh\n");
		builder.append("&3&l- Blood curse:\n");
		builder.append("\n");
		builder.append("&6Spawn a new werewolf at its victim's corpse\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oOnce formidable warriors, now crazed with bloodlust.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getAbominationDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&4&lAbomination&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lHostile Mob\n");
		builder.append("&6&lHealth: &c&l100\n");
		builder.append("&6&lDamage: &c&l3\n");
		builder.append("&6&lSpeed: &c&l0.2 (Slow)\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Kamikaze:\n");
		builder.append("\n");
		builder.append("&6Explode upon death, dealing huge damage to nearby entities\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oGiant flesh golems made out of mutilated limbs and bodies of many creatures.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getDarkKnightDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&d&lDark Knight&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lHostile Mob\n");
		builder.append("&6&lHealth: &c&l70\n");
		builder.append("&6&lDamage: &c&l10\n");
		builder.append("&6&lSpeed: &c&l0.2 (Slow)\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Fully Armored:\n");
		builder.append("&6Covered with full set of netherite armor, signifincantly increase Dark Knight's endurance\n");
		builder.append("&3&l- Death Aura:\n");
		builder.append("&6Emit a death aura which deals 3 damage every second\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oTainted knights who follow a dark path of blood and slaughter.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getEvilLordDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&4&lEvil Lord&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lBoss\n");
		builder.append("&6&lHealth: &c&l400\n");
		builder.append("&6&lDamage: &c&l6\n");
		builder.append("&6&lSpeed: &c&l0.2 (Slow)\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Dark Corruption:\n");
		builder.append("&6Evil Lord use a fraction of his dark power to corrupt nearby player's mind, breaking their fighting will and levitate them\n");
		builder.append("&3&l- Demon Guard:\n");
		builder.append("&6Summon an army of Vexes (20) protect Evil Lord and damage any nearby enemies\n");
		builder.append("&3&l- Eldrich Spell:\n");
		builder.append("&6Cast 10 evil spell aiming at his enemies, making them rot from the inside\n");
		builder.append("&3&l- Shadow Warp:\n");
		builder.append("&6Disappear then teleport to his opponents location after 3 seconds, causing a huge blast which burns everything nearby\n");
		builder.append("&3&l- Soul Impact:\n");
		builder.append("&6Summon an army of falling soul, which create poisonous explosion after their impact\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oThe most evil being you will encounter. Watch out for his evil bad jokes!\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    // Neutral
    public static String getLightSeekerDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&e&lLightseekers&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lNeutral Mob\n");
		builder.append("&6&lHealth: &c&l70\n");
		builder.append("&6&lDamage: &c&l8\n");
		builder.append("&6&lSpeed: &c&l0.4 (Fast)\n");
		builder.append("&7&m===============================&r &l&8[&2&lAbilities&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Well Armored:\n");
		builder.append("&Wear iron armors, increase Lightseeker's endurance\n");
		builder.append("&3&l- Shield of Retribution:\n");
		builder.append("&6Have a 30% chance to deflect 100% damage and redirect 50% of that damage to the attacker\n");
		builder.append("&3&l- Holy strike:\n");
		builder.append("&6Lightseeker's attacks have 30% chance of becoming a Holy Strike, dealing physical damage in a small area.\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oLightseekers, an order of Holy Warriors. They are paragons of divine protection and heavenly defense.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    //Traders
    public static String getWandererDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&a&lWanderer&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lTrader\n");
		builder.append("&6&lHealth: &c&l20\n");
		builder.append("&6&lSpeed: &c&l0.2 (Slow)\n");
		builder.append("&7&m===============================&r &l&8[&2&lTrades&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Home Locator\n");
		builder.append("&3&l- Exterminator\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oA mere trader with no outstanding abilities.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
    
    public static String getRangerDescription() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n");
		builder.append("&a&m-----------------------------&r &l&8[&a&lMerchant Wiki&l&8] &r&a&m---------------------------------\n");
		builder.append("&6&m++++++++++++++++++++++++++++&r &l&8[&1&lRanger&l&8] &r&6&m++++++++++++++++++++++++++++++++\n");
		builder.append("&7&m=================================&r &l&8[&2&lStats&l&8] &r&7&m=====================================\n");
		builder.append("&6&lType: &c&lTrader\n");
		builder.append("&6&lHealth: &c&l20\n");
		builder.append("&6&lSpeed: &c&l0.2 (Slow)\n");
		builder.append("&7&m===============================&r &l&8[&2&lTrades&l&8] &r&7&m===================================\n");
		builder.append("\n");
		builder.append("&3&l- Wooden Longbow\n");
		builder.append("&3&l- Swiftwind Boots\n");
		builder.append("&7&m===============================&r &l&8[&2&lDescription&l&8] &r&7&m=================================\n");
		builder.append("&r \n");
		builder.append("&7&oA mere trader with no outstanding abilities.\n");
		builder.append(" \n");
		builder.append("&a&m-------------------------------------------------------------------------------\n");
		return ColorLogging.formatted(builder.toString());
    }
}
