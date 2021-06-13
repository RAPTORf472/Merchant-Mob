package me.raptor.mob.hostile;

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
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;
import me.raptor.merchant.currency.Currency;
import me.raptor.mob.hostile.item.DarkChestplate;
import me.raptor.mob.hostile.item.DarkFootwear;
import me.raptor.mob.hostile.item.DarkHeadpiece;
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
	
	//Like every other mob's spawn event
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
	
	//Play random sound when attack
	@EventHandler
	public void onDarkKnightAttack(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Zombie && e.getEntity() instanceof LivingEntity) {
			Zombie z = (Zombie) e.getDamager();
			if (checkKey(z, "Darkknight", key)) {
				z.getWorld().playSound(z.getLocation(), MerchantConversation.getData("Darkknight.attack", r.nextInt(3)), 5, 1);
			}
		}
	}
	
	//Play random sound when hurt
	@EventHandler
	public void onDarkKnightHurt(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Zombie) {
			Zombie z = (Zombie) e.getEntity();
			if (checkKey(z, "Darkknight", key)) {
				z.getWorld().playSound(z.getLocation(), MerchantConversation.getData("Darkknight.damaged", r.nextInt(3)), 5, 1);
			}
		}
	}
	
	//Spawn drops
	@EventHandler
	public void onDarkKnightDie(EntityDeathEvent e) {
		if (e.getEntity() instanceof Zombie) {
			Zombie z = (Zombie) e.getEntity();
			if (checkKey(z, "Darkknight", key)) {
				spawnDrops(z.getLocation());
			}
		}
	}
	
	//Do damage to entity within the given radius
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
	
	//Darkness has its power
	public void DarkKnightEquipments(Zombie z) {
		z.setCanPickupItems(false);
		z.getEquipment().setItemInMainHand(DarkSword.sword());
		z.getEquipment().setItemInMainHandDropChance((float) 0.1);
		z.getEquipment().setChestplate(DarkChestplate.chestplate());
		z.getEquipment().setBoots(DarkFootwear.boot());
		z.getEquipment().setLeggings(DarkLeggings.leg());
		z.getEquipment().setHelmet(DarkHeadpiece.helmet());
	}
	
	//Valuable drops. They are hard to kill
	public static void spawnDrops(Location l) {
		l.getWorld().dropItemNaturally(l, Currency.goldCoin(3));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLD_INGOT, 12));
		l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLDEN_APPLE, 2));
		l.getWorld().playSound(l, Sound.ENTITY_ARROW_HIT_PLAYER, 13, 3);
	}

}
