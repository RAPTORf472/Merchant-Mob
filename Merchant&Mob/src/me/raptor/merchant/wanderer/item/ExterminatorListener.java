package me.raptor.merchant.wanderer.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.raptor.merchant.AbstractEntityListener;
import me.raptor.mob.allies.LightSeeker;

public class ExterminatorListener implements Listener {
	
	Plugin plugin;
	Random r = new Random();
	
	public ExterminatorListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity) {
			Player p = (Player) e.getDamager();
			ItemStack i = p.getInventory().getItemInMainHand();
			if (Exterminator.checkItem(i, Exterminator.exterminator())) {
				LivingEntity l = (LivingEntity) e.getEntity();
				if (chooseTarget(l, 5) == null) return;
				zap(l, r.nextInt(11) + 3, e.getDamage(), 80, 3);
			}
		}
	}
	
	
	public void zap(LivingEntity l, int count, double damage, double percent, int interval) {
		new BukkitRunnable() {
			double damage1 = damage * percent / 100;
			int count1 = count;
			LivingEntity l1 = l;
			@Override
			public void run() {
				Monster m = chooseTarget(l1, 5);
				if (m == null) {
					cancel();
					return;
				}
				zapEffect(l1, m, 0.1);
				m.setNoDamageTicks(1);
				//the ammount of damage on each loop
				damage1 = damage1 * percent / 100;
				m.damage(damage1);
				//after a loop, the chooseTarget() method will be used for the previous target
				l1 = chooseTarget(m, 5);
				//number of zap left
				count1--;
				if (count1 == 0) {
					l1 = null;
					cancel();
					return;
				}
			}
		}.runTaskTimer(plugin, 0, interval);
	}
	
	public Monster chooseTarget(LivingEntity e, int radius) {
		//choose a target from a given range
		if (e == null) return null;
		List<Monster> target = new ArrayList<>();
		if (e.getNearbyEntities(radius, radius, radius).size() == 0) return null;
 		for (Entity entity : e.getNearbyEntities(radius, radius, radius)) {
			if (entity instanceof Monster) {
				//avoid duplication
				if (entity == e) continue;
				if (AbstractEntityListener.checkKey(e, "Lightseeker", LightSeeker.getKey())) continue;
				target.add((Monster) entity);
			}
		}
 		if (target.size() == 0) return null;
 		else {
 			return target.get(r.nextInt(target.size()));
 		}
	}
	
	public void zapEffect(LivingEntity e1, LivingEntity e2, double space) {
		if (e1 == null || e2 == null) return;
		//Algorithm for drawing a line of particle between 2 points
		//The random values used in 2 below declarations are for realistic electric effect (not so realistic)
		Location l1 = e1.getLocation().add(r.nextDouble() / 2, r.nextDouble() * 2 + 0.25, r.nextDouble() / 2);
		Location l2 = e2.getLocation().add(r.nextDouble() / 2, r.nextDouble() * 2 + 0.25, r.nextDouble() / 2);
	    World world = e1.getWorld();
	    double distance = l1.distance(l2);
	    Vector p1 = l1.toVector();
	    Vector p2 = l2.toVector();
	    Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
	    double length = 0;
	    //For the light effect when an entity is zap. Doesn't work for entity with fire resistance
	    e2.setFireTicks(5);
	    for (; length < distance; p1.add(vector)) {
	    	//light to dark aqua
	        world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0, new DustOptions(Color.fromRGB(r.nextInt(255), 255, 255), (float) 0.75));
	        world.spawnParticle(Particle.REDSTONE, l2, 1, 0.5, 1.0, 0.5, new DustOptions(Color.fromRGB(r.nextInt(255), 255, 255), (float) 0.75));
	        l2.getWorld().playSound(l2, Sound.ENTITY_CREEPER_HURT, 3, 3);
	        length += space;
	    }
	}
}
