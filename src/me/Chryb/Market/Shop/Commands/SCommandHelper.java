package me.Chryb.Market.Shop.Commands;

import me.Chryb.Market.Config;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SCommandHelper {
	
	public static void help(Player p){
  		p.sendMessage(ChatColor.WHITE + "// MarketShop Help 1/1 // " + ChatColor.GRAY + "() = secondary; [] = primary");
  		p.sendMessage(ChatColor.YELLOW + "  /shop create (Admin:Normal)" + ChatColor.GRAY + " - " + Config.getLangFile().getString("SHOP_HELP_CREATE"));
  		p.sendMessage(ChatColor.YELLOW + "   /shop delete" + ChatColor.GRAY + " - " + Config.getLangFile().getString("SHOP_HELP_DELETE"));
  		p.sendMessage(ChatColor.YELLOW + "  /shop set purchase [x]" + ChatColor.GRAY + " - " + Config.getLangFile().getString("SHOP_HELP_PURCHASE"));
  		p.sendMessage(ChatColor.YELLOW + "  /shop set retail [x]" + ChatColor.GRAY + " - " + Config.getLangFile().getString("SHOP_HELP_RETAIL"));
  		p.sendMessage(ChatColor.YELLOW + "  /shop set type [Admin:Normal]" + ChatColor.GRAY + " - " + Config.getLangFile().getString("SHOP_HELP_TYPE"));
	}

}
