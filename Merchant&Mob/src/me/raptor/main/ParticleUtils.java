package me.raptor.main;


import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleUtils {
	
	static Plugin plugin;
	static Random r = new Random();
	
	public ParticleUtils(Plugin plugin) {
		ParticleUtils.plugin = plugin;
	}
	
	//create a circle with given radius
	public static void createCircle(org.bukkit.Particle p, Location l, int radius, int amount) {
		//higher amount increase circle's density
		for (int i = 0; i < amount; i++) {
			float x = (float) (radius * Math.cos(i));
			float z = (float) (radius * Math.sin(i));
			l.getWorld().spawnParticle(p, l.clone().add(x, 1, z), 0);
		}
	}
	
	//create a circle with given radius (for redstone particle)
	public static void createCircle(org.bukkit.Particle p, Location l, int radius, int amount, DustOptions option) {
		//higher amount increase circle's density
		for (int i = 0; i < amount; i++) {
			float x = (float) (radius * Math.cos(i));
			float z = (float) (radius * Math.sin(i));
			l.getWorld().spawnParticle(p, l.clone().add(x, 1, z), 0, option);
		}
	}
	
	//An animated circle with starting and ending radius, density, speed...
	public static void createExpandingCircle(org.bukkit.Particle p, Location l, float radius, float increase, float max, int amount) {
		new BukkitRunnable() {
			float range = radius;
			@Override
			public void run() {
				if (range > max) {
					cancel();
					return;
				}
				for (int i = 0; i < amount; i+=1) {
					float x = (float) (range * Math.cos(i));
					float z = (float) (range * Math.sin(i));
					l.getWorld().spawnParticle(p, l.add(x, 0, z), 0, 0, 0, 0, 0, true);
					l.subtract(x, 0, z);
				}
				range += increase;
			}
		}.runTaskTimer(plugin, 0, 1);
	}

	//An animated circle with starting and ending radius, density, speed... (for redstone particle)
	public static void createExpandingCircle(Particle p, Location l, float radius, float increase, float max, int amount, DustOptions option) {
		new BukkitRunnable() {
			float range = radius;
			@Override
			public void run() {
				if (range > max) {
					cancel();
					return;
				}
				for (int i = 0; i < amount; i+=1) {
					float x = (float) (range * Math.cos(i));
					float z = (float) (range * Math.sin(i));
					l.getWorld().spawnParticle(p, l.add(x, 0, z), 0, option);
					l.subtract(x, 0, z);
				}
				range += increase;
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	
	//Create a spiral that strike down to the player's location
	public static void spiralDown(Location l, float curve, float radius, float dense, float maxHeight, DustOptions option) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (float i = maxHeight * curve; i / curve > l.getY(); i-=dense) {
					float x = (float) (radius * Math.cos(i));
					float z = (float) (radius * Math.sin(i));
					l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(x, i / curve - l.getY(), z), 0, 0, 0, 0, 0.5, option, true);
				}
			}
		}.runTask(plugin);
	}
	
	//Create a spiral that goes up to the sky from the player's location
	public static void spiralUp(Location l, float curve, float radius, float dense, float maxHeight, DustOptions option) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (double i = l.getY() * curve; i < maxHeight * curve; i+=dense) {
					float x = (float) (radius * Math.cos(i));
					float z = (float) (radius * Math.sin(i));
					l.setY(i/ curve);
					l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(x, 0, z), 0, 0, 0, 0, 0.5, option, true);
				}
			}
		}.runTask(plugin);
	}
	
	//Similar to the spiralUp method but with a timer, which will make the particle fade out after a short time,
	//leaving a soul-like effect
	public static void SoulSpiralUp(Location l, float curve, float radius, float dense, float maxHeight, int speed, float fade, DustOptions option) {
		new BukkitRunnable() {
			double i = l.getY() * curve;
			@Override
			public void run() {
				if (i > maxHeight * curve) {
					cancel();
					return;
				}
				float x = (float) (radius * Math.cos(i));
				float z = (float) (radius * Math.sin(i));
				l.setY(i/ curve);
				l.getWorld().spawnParticle(Particle.REDSTONE, l.clone().add(x, 0, z), 0, 0, 0, 0, fade, option, true);	
				i+=dense;
			}
		}.runTaskTimer(plugin, 0, speed);
	}
	
	//Algorithm for spreading vector with given accuracy (spread value)
	public static Vector spread(Location l, float accuracy) {
		Vector v = l.getDirection();
		v.add(new Vector(Math.random() * accuracy - accuracy / 2, 0 ,Math.random() * accuracy - accuracy / 2));
		return v;
	}
	
}