package me.Chryb.Market;

import me.Chryb.Market.Util.ChatUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MarketCommands implements CommandExecutor{
	
	public static Market plugin;
	public MarketCommands(Market instance){
		 plugin = instance;
		}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("market") || cmd.getName().equalsIgnoreCase("m")){
			if (!(sender instanceof Player)) { return false; }
			Player p = (Player)sender;
			
			if (args.length == 0){
				p.sendMessage(ChatColor.GRAY + "The command was not recognized.");
	            p.sendMessage(ChatColor.GRAY + "See " + ChatColor.DARK_PURPLE + "/market help" + ChatColor.GRAY + " for a list of commands.");
	            return true;
			}
			
			// HELP //
			if (args[0].equalsIgnoreCase("help")){
				/* --------------
				 * Command: help (market)
				 * Usage: /market help
				 * Description: Shows the market global help.
				 * Permission: Market.help
				 --------------*/
				if (!(p.hasPermission("Market.cmd.help") && p.hasPermission("Market.cmd.*"))) {
					ChatUtil.noPermissionMessage(p);
					return true;
				}
				if (args.length == 1){
					MCommandHelper.help(p, "1");
					return true;
				}
				if (args.length == 2){
					if (args[1].equalsIgnoreCase("1")){
						MCommandHelper.help(p, args[1]);
						return true;
					} else {
						p.sendMessage(ChatColor.RED + "You have to type a site!");
						return true;
					}
				}
				if (args.length > 2){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/market help <site>");
					return true;
				}
				
			}			
			
			// RELOAD //
			if (args[0].equalsIgnoreCase("reload")){
				
				/* --------------
				 * Command: reload (market)
				 * Usage: /market reload
				 * Description: Reload the Market Plugin.
				 * Permission: Market.reload
				 --------------*/
				
				if (!(p.hasPermission("Market.cmd.reload") && p.hasPermission("Market.cmd.*"))) {
					ChatUtil.noPermissionMessage(p);
					return true;
				}
				if (args.length == 1){
					plugin.reloadPlugin();
					p.sendMessage(ChatColor.GRAY + "Plugin " + ChatColor.DARK_PURPLE + "[Market]" + ChatColor.GRAY + " reloaded!");
					return true;
				}
				if (args.length > 1){
					p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE") + "/market reload");
					return true;
				}
			}
	}
  return false;
  }
}
