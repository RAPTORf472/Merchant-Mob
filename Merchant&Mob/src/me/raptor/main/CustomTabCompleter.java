package me.raptor.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CustomTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sd, Command cmd, String alias, String[] args) {
		// TODO Auto-generated method stub
		//Tab completion
		if (cmd.getName().equalsIgnoreCase("merchant")) {
			if (sd instanceof Player) {
				if (args.length == 1) return getCmdArg1Player();
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("chat")) {
						 return getCmdArg2Chat();
					}
					if (args[0].equalsIgnoreCase("reload")) {
						 return getCmdArg2Reload();
					}
					if (args[0].equalsIgnoreCase("wiki")) {
						 return getCmdArg2Wiki();
					}
				}
				if (args.length > 2) return null;
			} else {
				if (args.length == 1) return getCmdArg1Console();
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("reload")) {
						 return getCmdArg2Reload();
					}
				}
			}
			return null;
		}
		return null;
	}
	
	public List<String> getCmdArg1Player() {
		List<String> cmdArg1 = new ArrayList<>();
		cmdArg1.add("reload");
		cmdArg1.add("chat");
		cmdArg1.add("help");
		cmdArg1.add("adminPrecaution");
		cmdArg1.add("wiki");
		Collections.sort(cmdArg1);
		return cmdArg1;
	}
	
	public List<String> getCmdArg1Console() {
		List<String> cmdArg1 = new ArrayList<>();
		cmdArg1.add("reload");
		cmdArg1.add("help");
		cmdArg1.add("adminPrecaution");
		Collections.sort(cmdArg1);
		return cmdArg1;
	}
	
	public List<String> getCmdArg2Reload() {
		List<String> cmdArg2 = new ArrayList<>();
		cmdArg2.add("name");
		cmdArg2.add("dialogue");
		Collections.sort(cmdArg2);
		return cmdArg2;
	}
	
	public List<String> getCmdArg2Chat() {
		List<String> cmdArg2 = new ArrayList<>();
		cmdArg2.add("on");
		cmdArg2.add("off");
		Collections.sort(cmdArg2);
		return cmdArg2;
	}
	
	public List<String> getCmdArg2Wiki() {
		List<String> cmdArg2 = new ArrayList<>();
		cmdArg2.add("swiftwindboots");
		cmdArg2.add("exterminator");
		cmdArg2.add("locator");
		cmdArg2.add("longbow");
		cmdArg2.add("lightseeker");
		cmdArg2.add("werewolf");
		cmdArg2.add("abomination");
		cmdArg2.add("darkknight");
		cmdArg2.add("evillord");
		Collections.sort(cmdArg2);
		return cmdArg2;
	}
}
