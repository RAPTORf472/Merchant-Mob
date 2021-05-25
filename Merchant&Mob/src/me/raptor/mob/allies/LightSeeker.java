package me.raptor.mob.allies;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;
import me.raptor.mob.allies.item.LightSeekerChestplate;
import me.raptor.mob.allies.item.LightSeekerFootwear;
import me.raptor.mob.allies.item.LightSeekerLeggings;
import me.raptor.mob.allies.item.LightSeekerSword;

public class LightSeeker extends AbstractEntityListener {

	Plugin plugin;
	private static NamespacedKey key;
	//skin file
	File file;
	Random r = new Random();
	public LightSeeker(Plugin plugin, String path) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Lightseeker");
		file = new File(path + "lightseeker.png");
		}
	
	public String prefix() {
		return MerchantConversation.colored("&3[&bLightseeker&1]&3: &r");
	}
	
	public static NamespacedKey getKey() {
		return key;
	}
	
	@EventHandler
	public void onLightseekerSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Zombie) {
					Zombie z = (Zombie) e.getEntity(); 
					if (checkName(z, "Lightseeker")) {
						setKey(z, "Lightseeker", key);
						lightseekerEquipments(z);
						MerchantConversation.activateSpeakingAbility(z, "Lightseeker.dialogue", 10, 30, prefix());
						z.teleport(z.getWorld().getHighestBlockAt(z.getLocation()).getLocation().add(0, 2, 0));
						z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
						setSkin(z, file, ChatColor.AQUA + "Lightseeker");
						
					}
				}
			}
		}.runTaskLater(plugin, 1);
		
	}
	
	@EventHandler
	public void onLightseekerBlock(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Zombie && e.getDamager() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) e.getDamager();
			Zombie z = (Zombie) e.getEntity();
			if (checkKey(z, "Lightseeker", key)) {
				//30%
					if (r.nextInt(101) <= 30) {
					le.damage(e.getDamage() * 50 / 100);
					e.setDamage(0);
					block(e);
				}
			}
		}
	}
	
	@EventHandler
	public void onLightseekerHolyStrike(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Zombie && e.getEntity() instanceof LivingEntity) {
			Zombie z = (Zombie) e.getDamager();
			if (checkKey(z, "Lightseeker", key)) {
					//30%
					if (r.nextInt(101) <= 30) {
					strike(e, 3, 15);
				}
			}
		}
	}
	
	public void block(EntityDamageByEntityEvent e) {
		//Lightseeker takes no damage when hit and return 50% of that damage to the attacker
		Zombie z = (Zombie) e.getEntity();
		Location l = z.getLocation();
		z.swingOffHand();
		z.getWorld().playSound(z.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, 5, 3);
		z.getWorld().spawnParticle(Particle.REDSTONE, l.add(0, 1, 0), 20, 1, 1, 1, new DustOptions(Color.fromRGB(255, 153, 0), (float) 0.75));
	}
	
	public void strike(EntityDamageByEntityEvent e, int range, double damage) {
		//An area attack. Deal 15 damage to nearby monsters
		Zombie z = (Zombie) e.getDamager();
		LivingEntity le = (LivingEntity) e.getEntity();
		Location l = z.getLocation();
		z.getWorld().playSound(z.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 5, 3);
		z.getWorld().spawnParticle(Particle.REDSTONE, l.add(0, 1, 0), 100, range, 1, range, new DustOptions(Color.fromRGB(255, 255, r.nextInt(255)), (float) 0.75));
		for (Entity e1 : le.getNearbyEntities(range, range, range)) {
			if (e1 instanceof Monster) {
				Monster m = (Monster) e1;
				if (m instanceof Zombie) {
					if (checkKey(m, "Lightseeker", key)) continue;
					m.damage(damage);
				}
			}
		}
	}
	
	public void lightseekerEquipments(Zombie z) {
		z.setCanPickupItems(false);
		z.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
		z.getEquipment().setItemInMainHand(LightSeekerSword.sword());
		z.getEquipment().setItemInMainHandDropChance((float) 0.1);
		z.getEquipment().setChestplate(LightSeekerChestplate.chestplate());
		z.getEquipment().setBoots(LightSeekerFootwear.boot());
		z.getEquipment().setLeggings(LightSeekerLeggings.leg());

	}
}
