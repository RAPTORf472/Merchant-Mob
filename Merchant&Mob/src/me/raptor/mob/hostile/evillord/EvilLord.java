package me.raptor.mob.hostile.evillord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.main.ParticleUtils;
import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;
import me.raptor.merchant.currency.Currency;

public class EvilLord extends AbstractEntityListener {
	//Here's our mighty lord
	static Plugin plugin;
	static NamespacedKey key;
	//skin file
	File file;
	static Random r = new Random();
	private static boolean exist = false;
	
	public EvilLord(Plugin plugin, String path) {
		EvilLord.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		key = new NamespacedKey(plugin, "Evillord");
		file = new File(path + "evillord.png");
	}
	
	//Prefix for the Evil Lord's dialogue
	public static String prefix() {
		return MerchantConversation.colored("&l&5[&cEvil Lord&5]&6: &r");
	}
	
	//His name
	public static String name() {
		return MerchantConversation.colored("&l&5[&cEvil Lord&5]&r");
	}
	
	public static NamespacedKey getKey() {
		return key;
	}
	
	//Like every other one
	@EventHandler
	public void onDarkKnightSpawn(MythicMobSpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (e.getEntity() instanceof Zombie) {
					Zombie z = (Zombie) e.getEntity(); 
					if (checkName(z, "Evillord")) {
						setKey(z, "Evillord", key);
						if (exist) {
							z.remove();
							return;
						}
						exist = true;
						z.teleport(z.getWorld().getHighestBlockAt(z.getLocation()).getLocation().add(0, 2, 0));
						z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10);
						z.setRemoveWhenFarAway(false);
						setSkin(z, file, ChatColor.RED + "" + ChatColor.BOLD + "Evil Lord");
						activateSkill(z);
						MerchantConversation.activateSpeakingAbility(z, "Evillord", 10, 30, prefix());
						String location = "X:" + ChatColor.YELLOW + z.getLocation().getX() + ChatColor.RED + " Y:" + ChatColor.YELLOW + z.getLocation().getY() + ChatColor.RED + " Z:" + ChatColor.YELLOW + z.getLocation().getZ() + ChatColor.RED;
						z.getWorld().strikeLightningEffect(z.getLocation());
						Bukkit.getServer().broadcastMessage(ChatColor.RED + "A Dark Lord has been spawned at " + location);
						//Sometimes they despawn enigmatically, has to change the boolean manually
						checkExistAnnually(z);
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	//Random skill every time
	public void activateSkill(Zombie z) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (z.getTarget() == null) return;
				if (z.isDead() || z.isInvulnerable()) {
					cancel();
					return;
				}
					switch (r.nextInt(5)) {
						case 0:
							ShadowWarp.activateShadowWarp(z, z.getTarget(), 2);
							return;
						case 1:
							EldrichSpell.activateEldrichSpell(z, 10);
							return;
							
						case 2:
							SoulImpact.activateSoulImpact(z, 13, 20, 2, 5);
							return;

						case 3:
							DemonGuard.activateDemonguard(z, z.getTarget(), 20, 8);
							return;
						case 4:
							DarkCorruption.activateDarkCorruption(z, 15);
							return;
					}
			}
		}.runTaskTimer(plugin, 0, 20 * 10);
	}
	
	//Random drops for 15 seconds
	public static void spawnDrops(Location l) {
		List<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(Material.DIAMOND, 1));
		drops.add(new ItemStack(Material.GOLD_INGOT, 1));
		drops.add(new ItemStack(Material.IRON_INGOT, 1));
		drops.add(new ItemStack(Material.NETHERITE_INGOT, 1));
		drops.add(new ItemStack(Material.EMERALD, 1));
		drops.add(Currency.silverCoin(1));
		drops.add(Currency.penny(1));
		l.getWorld().dropItemNaturally(l, Currency.credit(2));
		l.getWorld().dropItemNaturally(l, Currency.goldCoin(25));
		l.getWorld().spawn(l, ExperienceOrb.class).setExperience(2000);
		l.getWorld().playSound(l, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 13, 1);
		new BukkitRunnable() {
			int second = 0;
			@Override
			public void run() {
				if (second++ > 2 * 10 * 15) {
					cancel();
					return;
				}
				l.getWorld().playSound(l, Sound.ENTITY_ARROW_HIT_PLAYER, 13, 3);
				l.getWorld().spawnParticle(Particle.REDSTONE, l, 20, 2, 2, 2, new DustOptions(Color.fromRGB(255, 255, 0), 1));
				l.getWorld().dropItemNaturally(l, drops.get(r.nextInt(drops.size())));
			}
		}.runTaskTimer(plugin, 20 * 3, 2);
	}
	
	//Backstage stuff
	public static void soulEscape(Location l) {
		new BukkitRunnable() {
			int times = 0;
			@Override
			public void run() {
				if (times++ > 4 * 20) {
					cancel();
					return;
				}
				ParticleUtils.SoulSpiralUp(SoulImpact.pickOne(l, 5), r.nextFloat() / 10, 3, 0.01f, (float) (l.getY() + 50), 1, 1, new DustOptions(Color.fromRGB(104, 255, 101), (float) 6.5));
			}
		}.runTaskTimer(plugin, 0, 5);
	}
	
	//Backstage stuff
	public static void flash(Location l) {
		new BukkitRunnable() {
			int times = 0;
			@Override
			public void run() {
				if (times++ > 4 * 20) {
					cancel();
					return;
				}
				l.getWorld().spawnParticle(Particle.FLASH, l, 20, 5, 5, 5);
			}
		}.runTaskTimer(plugin, 0, 5);
	}
	
	//Backstage stuff
	public static void explosions(Location l) {
		new BukkitRunnable() {
			int times = 0;
			@Override
			public void run() {
				if (times++ > 4 * 20) {
					cancel();
					return;
				}
				l.getWorld().playSound(l, Sound.ENTITY_WITHER_SHOOT, 13, 4);
				l.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, l, 5, 3, 3, 3);
			}
		}.runTaskTimer(plugin, 0, 5);
	}
	
	//Backstage stuff
	public static void deathEffect(Zombie z) {
		Location l = z.getLocation();
		Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "The " + name() + ChatColor.GOLD + "" + ChatColor.BOLD + " has been defeated!");
		soulEscape(l);
		regenerationCloud(l);
		flash(z.getLocation());
		explosions(z.getLocation());
		z.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20 * 20, 0));
		z.setInvulnerable(true);
		z.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
		new BukkitRunnable() {
			@Override
			public void run() {
				z.remove();
			}
		}.runTaskLater(plugin, 20 * 20);
	}
	
	//Backstage stuff
	public static AreaEffectCloud regenerationCloud(Location l) {
		AreaEffectCloud a = (AreaEffectCloud) l.getWorld().spawnEntity(l, EntityType.AREA_EFFECT_CLOUD);
		a.setRadius(5);
		a.setDuration(20 * 3);
		a.setColor(Color.fromRGB(23, 252, 3));
		a.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION,  20 * 4, 1), false);
		return a;
	}

	public static boolean isExist() {
		return exist;
	}

	public static void setExist(boolean exist) {
		EvilLord.exist = exist;
	}
	
	//A runnable that check the zombie existance
	public void checkExistAnnually(Zombie z) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (z.isDead()) exist = false;
			}
		}.runTaskTimer(plugin, 0, 20 * 5);
	}
	

}
