package me.raptor.mob.hostile;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.mob.hostile.item.DarkChestplate;
import me.raptor.mob.hostile.item.DarkFootwear;
import me.raptor.mob.hostile.item.DarkLeggings;
import me.raptor.mob.hostile.item.DarkSword;

public class DarkKnight extends AbstractEntityListener {

	Plugin plugin;
	NamespacedKey key;
	//skin file
	File file;
	Random r = new Random();
	public DarkKnight(Plugin plugin, String path) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Darkknight");
		file = new File(path + "darkknight.png");
		}
	
	@EventHandler
	public void onDarkKnightSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Zombie) {
					Zombie z = (Zombie) e.getEntity(); 
					if (checkName(z, "Darkknight")) {
						setKey(z, "Darkknight", key);
						z.teleport(z.getWorld().getHighestBlockAt(z.getLocation()).getLocation().add(0, 2, 0));
						z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(12);
						z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
						setSkin(z, file, ChatColor.LIGHT_PURPLE + "Dark Knight");
						deathAura(z, 1, 3, 1);
						DarkKnightEquipments(z);
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	public void deathAura(LivingEntity l, int interval, int range, int damage) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (l.isDead()) {
					cancel();
					return;
				}
				l.getWorld().spawnParticle(Particle.REDSTONE, l.getLocation().add(0, 1, 0), 100, range, 1, range, new DustOptions(Color.fromRGB(171, 52, 235), 1));
				for (Entity e : l.getNearbyEntities(range, range, range)) {
					if (e instanceof LivingEntity) {
						if (e == l) continue;
						((LivingEntity) e).damage(damage);
					}
				}
			}
		}.runTaskTimer(plugin, 0, 20 * interval);
	}
	
	public void DarkKnightEquipments(Zombie z) {
		z.setCanPickupItems(false);
		z.getEquipment().setItemInMainHand(DarkSword.sword());
		z.getEquipment().setItemInMainHandDropChance((float) 0.1);
		z.getEquipment().setChestplate(DarkChestplate.chestplate());
		z.getEquipment().setBoots(DarkFootwear.boot());
		z.getEquipment().setLeggings(DarkLeggings.leg());

	}

}
