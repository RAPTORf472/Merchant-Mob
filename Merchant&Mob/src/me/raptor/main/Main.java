package me.raptor.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.raptor.merchant.MerchantConversation;
import me.raptor.merchant.currency.Currency;
import me.raptor.merchant.ranger.RangerListener;
import me.raptor.merchant.ranger.item.SwiftwindBootsListener;
import me.raptor.merchant.ranger.item.WoodenLongbowListener;
import me.raptor.merchant.wanderer.WandererListener;
import me.raptor.merchant.wanderer.item.ExterminatorListener;
import me.raptor.merchant.wanderer.item.HomeLocatorListener;
import me.raptor.mob.allies.Eridan;

public class Main extends JavaPlugin {

	private File customConfigFile;
    private FileConfiguration customConfig;
	
	@Override
	public void onEnable() {
		ColorLogging.Logging("&4&lMerchant Plugin has been enabled");
		createCustomConfig();
		new Currency(this);
		new HomeLocatorListener(this);
		new ExterminatorListener(this);
		new WandererListener(this);
		new MerchantConversation(this);
		new RangerListener(this);
		new WoodenLongbowListener(this);
		new SwiftwindBootsListener(this);
		new Eridan(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }
	
	public File getCustomConfigFile() {
        return customConfigFile;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "dialogue.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("dialogue.yml", false);
         }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public void saveData() {
        if (this.customConfigFile == null || this.customConfig == null)
            return;

        try {
            this.getConfig().save(this.customConfigFile);
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "Could not save config to " + this.customConfigFile, e);
        }
    }
	
	@Override
	public boolean onCommand(CommandSender sd, Command cmd, String commandLabel, String[] args) {
		if (!(sd instanceof Player)) {
			sd.sendMessage("no");
		}
		Player p = (Player) sd;
		if (cmd.getName().equalsIgnoreCase("merchant")) {
			if (args.length == 0) return true;
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("chat")) {
					if (args[1].equalsIgnoreCase("on")) {
						getConfig().set(p.getName() + ".enableMerchantChat", true);
						saveConfig();
						p.sendMessage(ChatColor.GREEN + "You can now listen to the merchants");
					} else if (args[1].equalsIgnoreCase("off")) {
						getConfig().set(p.getName() + ".enableMerchantChat", false);
						saveConfig();
						p.sendMessage(ChatColor.GREEN + "You can no longer listen to the merchants");
					}
				}
			}
		}

		return true;
	}
	
}
