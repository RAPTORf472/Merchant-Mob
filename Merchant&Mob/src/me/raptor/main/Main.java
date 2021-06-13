package me.raptor.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import me.raptor.merchant.currency.CurrencyListener;
import me.raptor.merchant.ranger.RangerListener;
import me.raptor.merchant.ranger.item.SwiftwindBootsListener;
import me.raptor.merchant.ranger.item.WoodenLongbowListener;
import me.raptor.merchant.wanderer.WandererListener;
import me.raptor.merchant.wanderer.item.ExterminatorListener;
import me.raptor.merchant.wanderer.item.HomeLocatorListener;
import me.raptor.mob.allies.Eridan;
import me.raptor.mob.allies.LightSeeker;
import me.raptor.mob.hostile.Abomination;
import me.raptor.mob.hostile.DarkKnight;
import me.raptor.mob.hostile.Werewolf;
import me.raptor.mob.hostile.evillord.DarkCorruption;
import me.raptor.mob.hostile.evillord.DemonGuard;
import me.raptor.mob.hostile.evillord.EldrichSpell;
import me.raptor.mob.hostile.evillord.EvilLord;
import me.raptor.mob.hostile.evillord.EvillordListener;
import me.raptor.mob.hostile.evillord.ShadowWarp;
import me.raptor.mob.hostile.evillord.SoulImpact;

public class Main extends JavaPlugin {

	static Main plugin;
	private File customDialogueFile, customNameFile;
    private FileConfiguration customDialogueConfig, customNameConfig;
    //Path to skin folder(server)
    String skinpath = "/home/container/plugins/LibsDisguises/Skins/";
    //Path to skin folder(local)
//    String skinpath = "C:\\Users\\admin\\Desktop\\sv\\Summertime\\plugins\\LibsDisguises\\Skins\\";
	
	@Override
	public void onEnable() {
		//register events and give skin folder directory to mobs
		ColorLogging.onEnableLog();
		createDialogueFile();
		createNameFile();
		//give plugin instance to classes
		new Currency(this);
		new CurrencyListener(this);
		new HomeLocatorListener(this);
		new ExterminatorListener(this);
		new MerchantConversation(this);
		new WoodenLongbowListener(this);
		new SwiftwindBootsListener(this);
		new Eridan(this);
		new EvillordListener(this);
		new EldrichSpell(this);
		new SoulImpact(this);
		new ShadowWarp(this);
		new DemonGuard(this);
		new DarkCorruption(this);
		new RangerListener(this, skinpath);
		new WandererListener(this, skinpath);
		new LightSeeker(this, skinpath);
		new Werewolf(this, skinpath);
		new Abomination(this, skinpath);
		new DarkKnight(this, skinpath);
		new EvilLord(this, skinpath);
		new ParticleUtils(this);
		getCommand("merchant").setTabCompleter(new CustomTabCompleter());
	}
	
	@Override
	public void onDisable() {
		ColorLogging.onDisableLog();
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public FileConfiguration getCustomDialogueConfig() {
        return this.customDialogueConfig;
    }
	
	public File getCustomDialogueFile() {
        return customDialogueFile;
    }
    
    public FileConfiguration getCustomNameConfig() {
        return this.customNameConfig;
    }
	
	public File getCustomNameFile() {
        return customNameFile;
    }

	//create a new dialogue file if there isn't one
    private void createDialogueFile() {
    	customDialogueFile = new File(getDataFolder(), "dialogue.yml");
        if (!customDialogueFile.exists()) {
        	customDialogueFile.getParentFile().mkdirs();
            saveResource("dialogue.yml", false);
         }

        customDialogueConfig= new YamlConfiguration();
        try {
        	customDialogueConfig.load(customDialogueFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    private void createNameFile() {
    	customNameFile = new File(getDataFolder(), "name.yml");
        if (!customNameFile.exists()) {
        	customNameFile.getParentFile().mkdirs();
            saveResource("name.yml", false);
         }

        customNameConfig= new YamlConfiguration();
        try {
        	customNameConfig.load(customNameFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public void saveData() {
        if (this.customNameFile == null || this.customNameConfig == null)
            return;

        try {
            this.getConfig().save(this.customNameFile);
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "Could not save config to " + this.customNameFile, e);
        }
    }
    
    //reload file using command
    public void reloadNameConfig() {
        if (this.customNameFile == null || this.customNameConfig == null)
            return;
        try {
        	customNameConfig.load(customNameFile);
        } catch (Exception e) {
        	this.getLogger().log(Level.SEVERE, "Something went wrong while reloading name configuration, please check the console!");
        	e.printStackTrace();
        }
    }
    
    public void reloadDialogueConfig() {
        if (this.customDialogueFile == null || this.customDialogueConfig == null)
            return;
        try {
        	customDialogueConfig.load(customDialogueFile);
        } catch (Exception e) {
        	this.getLogger().log(Level.SEVERE, "Something went wrong while reloading dialogue configuration, please check the console!");
            e.printStackTrace();
        }
    }
    
    //Not used
    public static InputStream getFileFromInputAsStream(String file) throws FileNotFoundException {
    	ClassLoader classloader = Main.class.getClassLoader();
    	InputStream stream = classloader.getResourceAsStream(file);
    	if (stream == null) throw new FileNotFoundException("File not found: " + file);
    	else return stream;
    }
    
	@Override
	public boolean onCommand(CommandSender sd, Command cmd, String commandLabel, String[] args) {
		if (!(sd instanceof Player)) {
		if (args[0].equalsIgnoreCase("reload")) {
			//reload name config
			if (args[1].equalsIgnoreCase("name")) {
				reloadNameConfig();
				sd.sendMessage(ChatColor.GREEN + "Name Config reloaded");
			//reload dialogue config
			} else if (args[1].equalsIgnoreCase("dialogue")) {
				reloadDialogueConfig();
				sd.sendMessage(ChatColor.GREEN + "Dialogue Config reloaded");
			} else {
				sd.sendMessage(ColorLogging.getHelpMessage());
			}
		} else if (args[0].equalsIgnoreCase("adminPrecaution")) {
			sd.sendMessage(ColorLogging.getAdminPrecaution());
		} else {
			sd.sendMessage(ColorLogging.getHelpMessage());
		}
			return true;
		}
		Player p = (Player) sd;
		if (cmd.getName().equalsIgnoreCase("merchant")) {
			if (args.length == 0) {
				p.sendMessage(ColorLogging.getHelpMessage());
				return true;
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("chat")) {
					//Enable mob chatting
					if (args[1].equalsIgnoreCase("on")) {
						getConfig().set(p.getName() + ".enableMerchantChat", true);
						saveConfig();
						p.sendMessage(ChatColor.GREEN + "You can now listen to the merchants");
					//Disable mob chatting
					} else if (args[1].equalsIgnoreCase("off")) {
						getConfig().set(p.getName() + ".enableMerchantChat", false);
						saveConfig();
						p.sendMessage(ChatColor.GREEN + "You can no longer listen to the merchants");
					} else {
						p.sendMessage(ColorLogging.getHelpMessage());
					}
				} else if (args[0].equalsIgnoreCase("reload")) {
					//reload name config
					if (args[1].equalsIgnoreCase("name")) {
						reloadNameConfig();
						p.sendMessage(ChatColor.GREEN + "Name Config reloaded");
					//reload dialogue config
					} else if (args[1].equalsIgnoreCase("dialogue")) {
						reloadDialogueConfig();
						p.sendMessage(ChatColor.GREEN + "Dialogue Config reloaded");
					} else {
						p.sendMessage(ColorLogging.getHelpMessage());
					}
				} else if (args[0].equalsIgnoreCase("adminPrecaution")) {
					p.sendMessage(ColorLogging.getAdminPrecaution());
				} else {
					p.sendMessage(ColorLogging.getHelpMessage());
				}
			}
		}
		return true;
	}
	
}
