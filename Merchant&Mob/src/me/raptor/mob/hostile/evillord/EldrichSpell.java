package me.raptor.mob.hostile.evillord;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.main.ParticleUtils;

public class EldrichSpell {
	
	static Plugin plugin;
	static Random r = new Random();
	
	public EldrichSpell(Plugin plugin) {
		EldrichSpell.plugin = plugin;
	}
	
	//Activate the skill
	public static void activateEldrichSpell(Zombie z, int times) {
		new BukkitRunnable() {
			int count = 0;
			@Override
			public void run() {
				if (++count > times) {
					cancel();
					return;
				}
				LivingEntity l = z.getTarget();
				if (l == null) return;
				eldrichSpell(z, l);
				z.getLocation().getWorld().playSound(z.getLocation(), "spell", 5, 1);

			}
		}.runTaskTimer(plugin, 0, 15);
	}
	
	//Shoot a homing invisible snowball with particle
	private static void eldrichSpell(Zombie z, LivingEntity l) {
		Snowball s = spread(z, 0.5f);
		s.setCustomName("s");
		s.setItem(spellSnowball());
		s.setGravity(false);
		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {
				if (z.isDead() || z.getTarget() == null) {
					cancel();
					return;
				}
				Location l1 = s.getLocation();
				Location l2 = z.getTarget().getLocation().add(0, 1, 0);
				trail(s);
				if (i >= r.nextInt(9) + 10 && i <= r.nextInt(5) + 19) {
					s.setVelocity(l2.toVector().subtract(l1.toVector()).normalize().multiply(0.3));
				}
				i++;
			}
		}.runTaskTimer(plugin, 0, 1);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!s.isDead()) s.remove();
			}
		}.runTaskLater(plugin, 20 * 5);
	}
	
	//Particle effect
	private static void trail(Projectile p) {
		Location l = p.getLocation();
		if (p.isDead()) {
			return;
		}
		p.getWorld().spawnParticle(Particle.REDSTONE, l, 3, 0, 0, 0, 1, new DustOptions(Color.fromRGB(r.nextInt(100) + 155, 0, 0), (float) 1.5));
	}
	
	//Spread and return the involved snowball
	private static Snowball spread(Zombie z, float accuracy) {
		Snowball snowball = z.launchProjectile(Snowball.class);
		snowball.setVelocity(ParticleUtils.spread(z.getLocation(), accuracy));
		return snowball;
	}
	
	//Custom snowball with custom texture
	private static ItemStack spellSnowball() {
		ItemStack s = new ItemStack(Material.SNOWBALL);
		ItemMeta meta = s.getItemMeta();
		meta.setCustomModelData(1);
		s.setItemMeta(meta);
		return s;
	}
	
	//Negative effects when a spell hit a player
	public static void spellEffect(Snowball s, LivingEntity le) {
		le.damage(3);
		le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 3, 0));
		le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 0));
	}
}
