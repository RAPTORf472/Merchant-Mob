package me.raptor.mob.hostile.evillord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.raptor.merchant.AbstractEntityListener;
import me.raptor.merchant.MerchantConversation;
import me.raptor.merchant.wanderer.WandererRecipe;

public class EvillordListener extends AbstractEntityListener {
	
	Plugin plugin;
	
	public EvillordListener(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		WandererRecipe.setRecipe();
	}
	
	//Prevent sunburn (Skin cancer is a real thing)
	@EventHandler
	public void onEvilKingCombust(EntityCombustEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) e.getEntity();
			if (AbstractEntityListener.checkKey(le, "Evillord", EvilLord.getKey())) {
				if (e instanceof EntityCombustByBlockEvent || e instanceof EntityCombustByEntityEvent) return;
				e.setCancelled(true);
			}
		}
	}
	
	//Eldrich Spell
	@EventHandler
	public void onSpellHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Snowball && e.getEntity() instanceof LivingEntity) {
			Snowball s = (Snowball) e.getDamager();
			LivingEntity le = (LivingEntity) e.getEntity();
			if (checkProjectile(s, "s")) {
				if (le instanceof Player) {
					if (((Player) le).isBlocking()) {
						e.setDamage(1);
						return;
					}
				}
				EldrichSpell.spellEffect(s, le);
			}
			
		}
	}
	
	//DarkBlast && Shadow Warp (Explosion damage prevent)
	@EventHandler
	public void onEvilLordExplode(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Zombie) {
			Zombie z = (Zombie) e.getEntity();
			if (e.getCause() == DamageCause.CUSTOM) {
				 if (AbstractEntityListener.checkKey(z, "Evillord", EvilLord.getKey())) {
					 e.setCancelled(true);
				 }
			}
		}		
	}
	
	//Death and Drops
	@EventHandler
	public void onEvilLordDie(EntityDamageEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) e.getEntity();
			if (AbstractEntityListener.checkKey(le, "Evillord", EvilLord.getKey())) {
				le.getWorld().playSound(le.getLocation(), Sound.ENTITY_ZOMBIE_HURT, 5, -1);
				if (e.getDamage() < le.getHealth()) return;
				Zombie z = (Zombie) le;
				if (z.isInvulnerable()) return;
				EvilLord.setExist(false);
				MerchantConversation.speak(z, 20, EvilLord.prefix(), "Evillord.death");
				e.setCancelled(true);
				EvilLord.deathEffect(z);
				z.getWorld().playSound(z.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 5, 1);
				new BukkitRunnable() {
					@Override
					public void run() {
						EvilLord.spawnDrops(z.getLocation());
					}
				}.runTaskLater(plugin, 20 * 30);
			}
		}
	}
	
	//Kills
	//Will add a customizable kill message
	@EventHandler
	public void onEvilLordKill(PlayerDeathEvent e) {
		if (e.getDeathMessage().contains("Evil Lord")) {
			Player p = e.getEntity();
			e.setDeathMessage(ChatColor.YELLOW + p.getName() + " was too weak to challenge the mighty Evil Lord");
		}
	}
	
	
	
	

}
