package me.raptor.merchant;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import me.raptor.merchant.ranger.RangerRecipe;

public class ExampleMob extends AbstractEntityListener {
	
	//Cách để tạo ra 1 con Mob
	
	//1 plugin instance, dùng để đăng ký events và làm tham số cho các BukkitRunnable
	Plugin plugin;
	
	//1 NamespacedKey để phân biệt mob
	NamespacedKey key;

	//File skin
	File file;

	
	//Constructor: Đăng ký events, tạo namespacedkey, tạo file skin
	public ExampleMob(Plugin plugin, String path) {
		this.plugin = plugin;
		//Đăng ký event vs server. Nếu ko các event trong class sẽ ko hoạt động
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		//Tạo 1 namespacedkey mới có tên là Example Namespaced Key (Tên của namespacedKey nên là tên Mob)
		key = new NamespacedKey(plugin, "Example Namespaced Key");
		//Tạo skin file. Tham số string là tên của file .png chứa skin. Skin có thể được tìm thấy trong thư mục Skins của plugin
		file = new File(path + "ExampleSkinFile.png");
		//Một số khởi tạo cần thiết, vd như set recipe cho trader
//		RangerRecipe.setRecipe();
	}
	
	//Tiền tố của Mob, được chèn vào trước lời thoại
	//Vd : [SomeShittyName]: Cái gì đó
	public String prefix() {
		return MerchantConversation.colored("&5[&2Some Shitty Name&e]&5: &r");
	}
	
	//Tạo ra 1 hệ thống spawn riêng mất rất nhiều thời gian, vì vậy nên ta dùng hệ thống spawn ngẫu nhiên của MythicMobs
	@EventHandler
	public void onCustomMobSpawn(MythicMobSpawnEvent e) {
		//Runnable nên được chạy sau 1 tick. Lúc đó LibsDisguise, Bukkit và MythicMobs mới hoàn thành xong việc chỉnh sửa mob
		new BukkitRunnable() {
			@Override
			public void run() {
				//Check mob type. Có thể là zombie hoặc cái gì đó khác
				if (e.getEntity() instanceof AbstractVillager) {
					AbstractVillager v = (AbstractVillager) e.getEntity(); 
					/**Condition outside của MythicMob làm giảm đáng kể tỉ lệ spawn. Bằng cách teleport mob mới spawn lên block 
					 * (không phải air) cao nhất, ta có thể dễ dàng kiểm soát tỉ lệ spawn hơn
					 */
					//Lúc này mob chưa có key nên không thể dùng key để nhận biết. Phải dùng tên
					//Vì player không được dùng spawn egg nên phương pháp này khá an toàn
					if (checkName(v, "Ranger")) {
						//set key cho Mob, dùng để nhận diện về sau
						setKey(v, "Ranger", key);
						//Teleport tới block cao nhất
						v.teleport(v.getWorld().getHighestBlockAt(v.getLocation()).getLocation().add(0, 2, 0));
						//Kích hoạt khả năng nói chuyện của Mob.
						//Tham số String là Path của mob trong file dialogue.yml (Thường là tên mob), dùng để lấy dữ liệu
						//Tham số int đầu tiên là tần suất giao tiếp (Tính bằng giây) và tham số còn lại là khoảng cách để player xung quanh nghe được mob nói gì
						//Tham số prefix cho ta biết mob nào đang nói
						//Để biết thêm thông tin về class MerchantConversation hãy đọc comment của class
						MerchantConversation.activateSpeakingAbility(v, "Some path", 10, 30, prefix());
						//Nếu bạn không muốn mob nói chuyện nhưng vẫn kêu, thì dùng hàm activateSoundAbility của class MerchantConversation
						MerchantConversation.activateSoundAbility(v, "Somepath", 10);
						//Hàm setSkin có thể không hoạt đông được (Do trục trặc mạng, skinname sai...) nên ta cần đặt tên cho mob trước.
						v.setCustomName(ChatColor.DARK_GREEN + "Some name");
						v.setCustomNameVisible(true);
						//Set skin cho mob. Tham số đầu tiên là mob cần set skin, thứ 2 là file skin và còn lại là tên Mob
						//Nếu để trống tên mob thì mob sẽ không có tên, phải đặt đúng theo tên ở trên
						setSkin(v, file, ChatColor.DARK_GREEN + "That name again");
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}
	
	//Ở phần dưới bạn có thể tự tạo skill cho mob hoặc tùy chình mob
	
	//VD: khiến mob trade được
	@EventHandler
	public void onRangerClick(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof AbstractVillager)) return;
		AbstractVillager v = (AbstractVillager) e.getRightClicked();
		if (checkKey(v, "Ranger", key)) 
			RangerRecipe.openTradeMenu(e.getPlayer(), RangerRecipe.getMerchant());
	}
	
	//VD: tạo ra 1 vùng chết xung quanh mob
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
}
