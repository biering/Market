package me.Chryb.Market;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MCommandHelper {
	
	public static void help(Player p, String site){
		if (site.equalsIgnoreCase("1")){
			p.sendMessage(ChatColor.WHITE + "==[ Market Help 1/1 ]== " + ChatColor.GRAY + "<> = secondary; [] = primary");
			p.sendMessage(ChatColor.GOLD + "/market help" + ChatColor.WHITE + " <site>" + ChatColor.GRAY + " - gives help about the M cmd's");
			p.sendMessage(ChatColor.GOLD + "/market reload" + ChatColor.GRAY + " - reload the Market Plugin");
		}
	}

}
