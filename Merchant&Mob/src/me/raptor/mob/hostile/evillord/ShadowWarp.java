package me.raptor.mob.hostile.evillord;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.AbstractEntityListener;

public class ShadowWarp {
	
	static Plugin plugin;
	static Random r = new Random();
	
	public ShadowWarp(Plugin plugin) {
		ShadowWarp.plugin = plugin;
	}
	
	//activate the skill
	public static void activateShadowWarp(Zombie z, LivingEntity l, int time) {
		warp(z, l, time);
	}
	
	//teleport to the player's location
	public static void warp(Zombie z, LivingEntity l, int seconds) {
		Location loc1 = z.getLocation().add(0, 1, 0);
		Location loc2 = l.getLocation().add(0, 1, 0);
		//He's gone
		z.setInvisible(true);
		z.setInvulnerable(true);
		z.setGravity(false);
		z.setAI(false);
		z.setCollidable(false);
		z.getWorld().playSound(z.getLocation(), "portal", 13, 1);
		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {
				if (++i > seconds || z.isDead()) {
					cancel();
					return;
				}
				z.getWorld().spawnParticle(Particle.REDSTONE, loc1, 50, 0.5, 1, 0.5, 1, new DustOptions(Color.fromRGB(89, 20, 20), (float) 3));
				l.getWorld().spawnParticle(Particle.REDSTONE, loc2, 50, 0.5, 1, 0.5, 1, new DustOptions(Color.RED, (float) 3));
			}
		}.runTaskTimer(plugin, 0, 20);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (z.isDead()) {
					cancel();
					return;
				}
				//He's back
				z.teleport(loc2.subtract(0, 1, 0));
				z.getWorld().playSound(z.getLocation(), "portal", 13, 1);
				z.setInvisible(false);
				z.setInvulnerable(false);
				z.setGravity(true);
				z.setAI(true);
				z.setCollidable(true);
				infernalCombustion(z, 5);
			}
		}.runTaskLater(plugin, 20 * seconds + 1);
	}
	
	//Explode
	public static void infernalCombustion(Zombie z, int range) {
		z.getWorld().createExplosion(z.getLocation(), 7, false, false, z);
		for (Entity e : z.getNearbyEntities(range, range, range)) {
			if (e instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) e;
				if (le.getCustomName() != null) {
					if (AbstractEntityListener.getPlainMobName().contains(le.getCustomName())) {
						continue;
					}
				}
				le.setFireTicks(20 * 8);
			}
		}
	}
	
}
