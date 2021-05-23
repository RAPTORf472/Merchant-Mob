package me.raptor.merchant.ranger.item;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.abstractitem.AbstractItem;

public class WoodenLongbowListener implements Listener {
	
	Plugin plugin;
	Random r = new Random();
	
	public WoodenLongbowListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerShootbow(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			ItemStack i = p.getInventory().getItemInMainHand();
			if (AbstractItem.checkItem(i, WoodenLongbow.longbow())) {
				if (!(e.getProjectile() instanceof Arrow)) return;
				Arrow a = (Arrow) e.getProjectile();
				//double the force -> double the damage
				a.setVelocity(a.getVelocity().multiply(2));
				//5 percent chance
				if (a.isCritical()) {
					if (r.nextInt(101) <= 5) {
						crimsonSentenceShot(a);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerCrimsonSentence(EntityDamageByEntityEvent e) {
		//called when the crimson arrow hit an entity
		if (e.getEntity() == null) return;
		if (e.getDamager() instanceof AbstractArrow && e.getEntity() instanceof LivingEntity) {
			AbstractArrow a = (AbstractArrow) e.getDamager();
			if (a.getCustomName() == null) return;
			//check trademark
			if (a.getCustomName().equals("crimsonsentence")) {
				crimsonSentenceHit(e);
			}
		}
	}
	
	@EventHandler
	public void antiRepairLongbow(PrepareAnvilEvent e) {
		//cancel the result when a long bow is used on an anvil
		if (AbstractItem.checkItem(WoodenLongbow.longbow(), e.getInventory().getItem(0)) ||
				AbstractItem.checkItem(WoodenLongbow.longbow(), e.getInventory().getItem(1))) {
			e.setResult(null);
		}
	}
	
	public void crimsonSentenceShot(Arrow a) {
		//dark red
        a.getWorld().spawnParticle(Particle.REDSTONE, a.getLocation(), 50, 1, 1, 1, new DustOptions(Color.fromRGB(128, 0, 0), 1));
		new BukkitRunnable() {
			public void run() {
				if (a.getCustomName() == null || a.isDead()) {
					cancel();
					return;
				}
				//light orange
				a.getWorld().spawnParticle(Particle.REDSTONE, a.getLocation() , 3, new DustOptions(Color.fromRGB(245, 141, 66), 1));
			}
		}.runTaskTimer(plugin, 0, 1);
		//curse arrow effect
		a.getWorld().playSound(a.getLocation(), Sound.ENTITY_WITHER_DEATH, 3, 2);
		//set trademark
		a.setCustomName("crimsonsentence");
	}
	
	public void crimsonSentenceHit(EntityDamageByEntityEvent e) {
		LivingEntity l = (LivingEntity) e.getEntity();
		//instant kill requirement
		if (l.getHealth() > 120) {
			e.setDamage(e.getDamage() * 3);
			return;
		}
		l.setHealth(0);
		//dark red
		l.getWorld().spawnParticle(Particle.REDSTONE, l.getLocation().add(0, 1, 0) , 50, 1, 2, 1, new DustOptions(Color.fromRGB(153, 0, 0), 1));
	}
	
	

}
