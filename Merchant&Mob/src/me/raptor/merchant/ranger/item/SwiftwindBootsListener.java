package me.raptor.merchant.ranger.item;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.raptor.merchant.abstractitem.AbstractItem;

public class SwiftwindBootsListener implements Listener {

	Plugin plugin;
	Random r = new Random();
	
	public SwiftwindBootsListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onArrowDodge(ProjectileHitEvent e) {
		if (e.getHitEntity() == null) return;
		if (e.getHitEntity() instanceof Player) {
			Player p = (Player) e.getHitEntity();
			Projectile projectile = e.getEntity();
			//the player can't dodge in the air
			if (!p.isOnGround()) return;
			ItemStack i = p.getInventory().getBoots();
			if (AbstractItem.checkItem(i, SwiftwindBoots.boots())) {
				//70%
				if (r.nextInt(101) <= 70) {
					parry(p, projectile);
					//make the projectile shoot through the player, creating a dodge montage
					e.setCancelled(true);
					reduceDurability(i);
				}
			}
		}
	}
	
	public void parry(Player p, Projectile projectile) {
		//An algorithm for creating a line between the arrow and the player
		projectile.getLocation().setY(p.getLocation().getY());
		Location l = projectile.getLocation().subtract(p.getLocation());
		//looking horizontally
		l.setPitch(0);
		//Rotate the vector 90 or -90 degrees (left or right)
		// (1 - 0 * 2) * 90 or (1 - 2) * 90
		l.setYaw(l.getYaw() + (1 - (r.nextInt(2) * 2 )) * 90);
        p.spawnParticle(Particle.REDSTONE, p.getLocation().add(0, 1, 0), 20, 0.5, 1.0, 0.5, new DustOptions(Color.WHITE, (float) 0.75));
        //dodge sound
        p.playSound(p.getLocation(), "dodge", 2, 4);
		p.setVelocity(l.getDirection().multiply(1.5));
	}
	
	//reduce the durability by one when the player parry
	public void reduceDurability(ItemStack i) {
		ItemMeta im = i.getItemMeta();
		if (im instanceof Damageable) {
			if (!((Damageable) im).hasDamage()) ((Damageable) im).setDamage(1);
			else ((Damageable) im).setDamage(((Damageable) im).getDamage() + 1);
			i.setItemMeta(im);
		}
	}
	
}
