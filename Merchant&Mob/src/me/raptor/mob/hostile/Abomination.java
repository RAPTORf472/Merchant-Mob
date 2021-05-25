package me.raptor.mob.hostile;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;

public class Abomination extends AbstractEntityListener {

	Plugin plugin;
	NamespacedKey key;
	//skin file
	File file;
	Random r = new Random();
	public Abomination(Plugin plugin, String path) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Abomination");
		file = new File(path + "abomination.png");
		}
	
	@EventHandler
	public void onAbominationSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Zombie) {
					Zombie z = (Zombie) e.getEntity(); 
					if (checkName(z, "Abomination")) {
						setKey(z, "Abomination", key);
						z.teleport(z.getWorld().getHighestBlockAt(z.getLocation()).getLocation().add(0, 2, 0));
						z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
						setSkin(z, file, ChatColor.DARK_RED + "Abomination");
						
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	@EventHandler
	public void onAbominationPreExplode(EntityDamageByEntityEvent e) {
		//Stop the abomination's death, prepare for the explosion
		if (e.getEntity() instanceof Zombie) {
			Zombie z = (Zombie) e.getEntity();
			if (e.getDamage() >= z.getHealth()) {
				if (AbstractEntityListener.checkKey(z, "Abomination", key)) {
					e.setCancelled(true);
					z.setHealth(0.1);
					z.setInvulnerable(true);
					z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
					explode(z);
				}
			}
		}
	}
	
	public void explode(Zombie z) {
		//Explode, deal damage to all entity with a 4F explosion
		if (AbstractEntityListener.checkKey(z, "Abomination", key)) {
			new BukkitRunnable() {
				int i = 0;
				@Override
				public void run() {
					z.getWorld().spawnParticle(Particle.REDSTONE, z.getLocation().add(0, 1, 0), 20, 1, 2, 1, new DustOptions(Color.fromRGB(255, 51, 0), 1));
					z.getWorld().playSound(z.getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 3, 3);
					if (i == 5) {
						i = 0;
						z.remove();
						z.getWorld().createExplosion(z.getLocation().add(0, 1, 0), 10, false, false);
						z.getWorld().spawnParticle(Particle.REDSTONE, z.getLocation().add(0, 1, 0), 100, 7, 7, 7, new DustOptions(Color.fromRGB(102, 0, 51), 1));	
						cancel();
						return;
					}
					i++;
				}
			}.runTaskTimer(plugin, 0, 20);

		}
		
	}
}
