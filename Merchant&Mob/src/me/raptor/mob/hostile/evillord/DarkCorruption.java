package me.raptor.mob.hostile.evillord;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.main.ParticleUtils;
import me.raptor.merchant.AbstractEntityListener;

public class DarkCorruption {
	
	static Plugin plugin;
	static Random r = new Random();
	
	public DarkCorruption(Plugin plugin) {
		DarkCorruption.plugin = plugin;
	}
	
	//Activate the skill
	public static void activateDarkCorruption(Zombie z, int range) {
		corrupt(z.getLocation(), range);
	}
	
	//Give negative effects to players within the given radius
	public static void corrupt(Location l, float radius) {
		ParticleUtils.createExpandingCircle(Particle.REDSTONE, l, 0, 1, 15, 70, new DustOptions(Color.BLACK, (float) 1.5));
		l.getWorld().playSound(l, "corruption", 5, 1);
		for (Entity e : l.getWorld().getNearbyEntities(l, radius, radius, radius)) {
			if (e instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) e;
				if (AbstractEntityListener.checkKey(le, "Evillord", EvilLord.getKey())) continue;
				le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 8, 3));
				le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 8, 0));
				if (e instanceof Player) {
					new BukkitRunnable() {
						@Override
						public void run() {
							Player p = (Player) e;
							p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 5, 0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 0));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * 5, 3));
							p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 5, 0));
						}
					}.runTaskLater(plugin, 20 * 3);

				}
			}
		}
	}
	
}
