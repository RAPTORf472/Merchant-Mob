package me.raptor.mob.hostile;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;

public class Werewolf extends AbstractEntityListener {
	//One of the highest damage monsters in the plain
	Plugin plugin;
	NamespacedKey key;
	File file = new File("C:\\Users\\admin\\Desktop\\sv\\Summertime\\plugins\\LibsDisguises\\Skins\\werewolf.png");
	Random r = new Random();
	public Werewolf(Plugin plugin, String path) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Werewolf");
		file = new File(path + "werewolf.png");
		}
	
	@EventHandler
	public void onWerewolfSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Spider) {
					Spider z = (Spider) e.getEntity(); 
					if (checkName(z, "Werewolf")) {
						setKey(z, "Werewolf", key);
						z.teleport(z.getWorld().getHighestBlockAt(z.getLocation()).getLocation().add(0, 2, 0));
						z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
						z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
						setSkin(z, file, ChatColor.DARK_BLUE + "Werewolf");
						
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
}


