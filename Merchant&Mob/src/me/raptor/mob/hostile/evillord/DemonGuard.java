package me.raptor.mob.hostile.evillord;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DemonGuard {
	static Plugin plugin;
	static Random r = new Random();
	
	public DemonGuard(Plugin plugin) {
		DemonGuard.plugin = plugin;
	}
	
	//Activate the skill
	public static void activateDemonguard(Zombie z, LivingEntity target, int amount, int lifetime) {
		summonGuard(z, target, amount, lifetime);
	}
	
	//Summon a bunch of Vex (They are not very smart, though)
	public static void summonGuard(Zombie z, LivingEntity target, int amount, int lifetime) {
		for (int i = 0; i < amount; i++) {
			Vex v = (Vex) z.getWorld().spawnEntity(z.getLocation().add(0, 1, 0), EntityType.VEX);
			customize(v, target);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (v.isDead()) {
						cancel();
						return;
					}
					v.damage(v.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() / lifetime);
				}
			}.runTaskTimer(plugin, 0, 20);
		}
	}
	
	//Customize the vex
	public static void customize(Vex v, LivingEntity target) {
		v.setCustomName(ChatColor.LIGHT_PURPLE + "Demon Guard");
		v.setCustomNameVisible(true);
		v.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.7);
		v.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
		v.setTarget(target);
	}
}
