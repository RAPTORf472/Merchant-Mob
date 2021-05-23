package me.raptor.merchant.ranger;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;



public class RangerListener extends AbstractEntityListener {
	
	//Called when a Ranger is spawn
	
	Plugin plugin;
	NamespacedKey key;
	//link to the skin, should be changed when uploaded to server or used at your computer
	File file = new File("C:\\Users\\admin\\Desktop\\sv\\Summertime\\plugins\\LibsDisguises\\Skins\\ranger.png");

	public RangerListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Ranger");
		RangerRecipe.setRecipe();
	}
	
	//Prefix for the Ranger's dialogue
	public String prefix() {
		return MerchantConversation.colored("&5[&2Ranger&e]&5: &r");
	}
	
	@EventHandler
	public void onRangerSpawn(MythicMobSpawnEvent e) {
		if (e.getEntity() instanceof AbstractVillager) {
			AbstractVillager v = (AbstractVillager) e.getEntity(); 
			/**MythicMobs outside option makes spawn rate drops significantly. So the Ranger will
			 * be pushed on the ground when it's spawned.
			 * Note that the getHighestBlockAt() method may push the Ranger too high if there is 
			 * a higher non-air block at the given location, which results in the Ranger's disappearance
			 */
			
			if (checkName(v, "Ranger")) {
				setKey(v, "Ranger", key);
				v.teleport(v.getWorld().getHighestBlockAt(v.getLocation()).getLocation().add(0, 2, 0));
				MerchantConversation.activateSpeakingAbility(v, "Ranger.dialogue", 10, 30, prefix());
				setSkin(v, file, ChatColor.DARK_GREEN + "Ranger");
			}
		}
	}
	
	//Open the trade menu when the player right click the Ranger
	@EventHandler
	public void onRangerClick(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof AbstractVillager)) return;
		AbstractVillager v = (AbstractVillager) e.getRightClicked();
		if (checkKey(v, "Ranger", key)) 
			RangerRecipe.openTradeMenu(e.getPlayer(), RangerRecipe.getMerchant());
	}
	
	
}
