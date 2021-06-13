package me.raptor.mob.hostile;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;
import me.raptor.merchant.currency.Currency;

public class Werewolf extends AbstractEntityListener {
	static //One of the highest damage monsters in the plain
	Plugin plugin;
	NamespacedKey key;
	File file = new File("C:\\Users\\admin\\Desktop\\sv\\Summertime\\plugins\\LibsDisguises\\Skins\\werewolf.png");
	Random r = new Random();
	public Werewolf(Plugin plugin, String path) {
		Werewolf.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Werewolf");
		file = new File(path + "werewolf.png");
	}
	
	//like every other mob's spawn event
	@EventHandler
	public void onWerewolfSpawn(EntitySpawnEvent e) {
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
						MerchantConversation.activateSoundAbility(z, "Werewolf", 10);
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	//Attack sound and spawn a new one at the prey's corpse
	@EventHandler
	public void onWerewolfBite(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Spider && e.getEntity() instanceof LivingEntity) {
			Spider s = (Spider) e.getDamager();
			LivingEntity le = (LivingEntity) e.getEntity();
			if (checkKey(s, "Werewolf", key)) {
				s.getWorld().playSound(s.getLocation(), MerchantConversation.getData("Werewolf.attack", 0), 5, 1);
				if (e.getDamage() > le.getHealth()) spawnNewWerewolf(le.getLocation(), s);
			}
		}
	}
	
	//Hurt sound
	@EventHandler
	public void onWerewolfHurt(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Spider) {
			Spider s = (Spider) e.getEntity();
			if (checkKey(s, "Werewolf", key)) {
				s.getWorld().playSound(s.getLocation(), Sound.ENTITY_WOLF_HURT, 5, 1);
			}
		}
	}
	
	//Spawn drops
	@EventHandler
	public void onWerewolfDie(EntityDeathEvent e) {
		if (e.getEntity() instanceof Spider) {
			Spider s = (Spider) e.getEntity();
			if (checkKey(s, "Werewolf", key)) {
				spawnDrops(s.getLocation());
			}
		}
	}
	
	//Spawn a new one, yay
	public void spawnNewWerewolf(Location l, Spider s) {
		double x = l.getX();
		double y = l.getY();
		double z = l.getZ();
		String location = x + "," + y + "," + z + ",";
		//idk how to do this with the api, so using console command is the only method
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawnmob Werewolf:1 1 world," + location + "90,90");

	}
	
	//Drops.
	public static void spawnDrops(Location l) {
		l.getWorld().dropItemNaturally(l, Currency.silverCoin(7));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 8));
		l.getWorld().playSound(l, Sound.ENTITY_ARROW_HIT_PLAYER, 13, 3);
	}
	
}


