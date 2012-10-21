package me.Chryb.Market.Shop;

import me.Chryb.Market.Market;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;

import org.bukkit.Location;

public class Owner {
	
	public static boolean is(String p, Shop shop){
		return is(p, shop.getItemFrame());
	}
	
	public static boolean is(String p, Location l){
		
		Shop shop = new Shop(l);
		ShopStore data = Database.getEntry(shop);
		
		if (p.equalsIgnoreCase(data.getOwner())){
			return true;
		}
		return false;
	}
	
	public static void deposit(String owner, double amount){
		Market.econ.depositPlayer(owner, amount);
	}
	
	public static void withdraw(String owner, double amount){
		Market.econ.withdrawPlayer(owner, amount);
	}
	
	public static double getBalance(String player){
		return Market.econ.getBalance(player);
	}

}
