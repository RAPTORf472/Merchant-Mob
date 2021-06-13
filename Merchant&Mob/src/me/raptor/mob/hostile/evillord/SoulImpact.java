package me.raptor.mob.hostile.evillord;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.main.ParticleUtils;
import me.raptor.merchant.AbstractEntityListener;

public class SoulImpact {
	static Plugin plugin;
	static Random r = new Random();
	
	public SoulImpact(Plugin plugin) {
		SoulImpact.plugin = plugin;
	}
	
	//Activate the skill
	public static void activateSoulImpact(Zombie z, int range, int times, int times1, int interval) {
		new BukkitRunnable() {
			int times = 0;
			@Override
			public void run() {
				if (times++ >= 20) {
					cancel();
					return;
				}
				for (int i = 0; i < times1; i++) {
					impact(pickOne(z.getLocation(), range));
				}
			}
		}.runTaskTimer(plugin, 0, interval);
	}
	
	//Particle, explosion and such
	public static void impact(Location l) {
		Location l1 = l.getWorld().getHighestBlockAt(l).getLocation();
		for (int i = 256; i > l1.getY(); i-=0.5) {
			l.setY(i);
			l.getWorld().spawnParticle(Particle.REDSTONE, l, 10, 0, 0, 0, 1, new DustOptions(Color.fromRGB(104, 255, 101), (float) 3.5), true);
		}
		ParticleUtils.spiralDown(l, 2f, 0.75f, 0.2f, 200f, new DustOptions(Color.fromRGB(132, 240, 166), (float) 1.5));
		ParticleUtils.createExpandingCircle(Particle.REDSTONE, l, 0, 0.5f, 4, 30, new DustOptions(Color.fromRGB(104, 255, 101), (float) 1.5));
		l1.getWorld().createExplosion(l1, 2, true, false);
		poisonCloud(l1);
		for (Entity e : l1.getWorld().getNearbyEntities(l1, 4, 4, 4)) {
			if (e instanceof LivingEntity) {
				//He can't be hurt by explosion, remember?
				if (AbstractEntityListener.checkKey(((LivingEntity) e), "Evillord", EvilLord.getKey())) continue;
				((LivingEntity) e).damage(5);
			}
		}
	}
	
	//Pick a random location
	public static Location pickOne(Location l, int radius) {
		return l.clone().add((1 - (r.nextInt(2) * 2)) * r.nextInt(radius), 0, (1 - (r.nextInt(2) * 2)) * r.nextInt(radius));
	}
	
	//Poisonous cloud where the soul impact
	public static AreaEffectCloud poisonCloud(Location l) {
		AreaEffectCloud a = (AreaEffectCloud) l.getWorld().spawnEntity(l, EntityType.AREA_EFFECT_CLOUD);
		a.setRadius(3);
		a.setDuration(20 * 3);
		a.setColor(Color.fromRGB(23, 252, 3));
		a.addCustomEffect(new PotionEffect(PotionEffectType.POISON,  20 * 4, 1), false);
		return a;
	}
}
