package me.Chryb.Market.Util;

import me.Chryb.Market.Market;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Shop.Shop;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class ValidationUtil {
	
	public static boolean isValidItemFrame(Sign s){
		if (s.getLine(0).contentEquals("[ItemFrame]")){
			if (!(s.getLine(1).isEmpty())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isItemFrameSelected(Player p){
		if (Market.selected_itemframe.containsKey(p)){
			return true;
		}
		return false;
	}
	
	public static boolean hasPurchase_Retail(Player p){
		if (Market.player_purchase.containsKey(p) || Market.player_retail.containsKey(p)){
			return true;
		}
		return false;
	}
	
	public static boolean isPurchase(Player p){
		if (Market.player_purchase.containsKey(p)){
			return true;
		}
		return false;
	}
	
	public static boolean isRetail(Player p){
		if (Market.player_retail.containsKey(p)){
			return true;
		}
		return false;
	}
	
	public static boolean isAdminShop(Location l){
		Shop shop = new Shop(l);
		ShopStore data = Database.getEntry(shop);
		if (data == null) return false;
		if (data.getMode().equalsIgnoreCase("Admin")){
	    	return true;
	    }
		return false;
	}
	
	public static boolean isPurchaseLocked(Location l){
		Shop shop = new Shop(l);
		ShopStore data = Database.getEntry(shop);
	    if (data.getPurchase() < 0){
	    	return true;
	    }
	    return false;
	}
	
	public static boolean isRetailLocked(Location l){
		Shop shop = new Shop(l);
		ShopStore data = Database.getEntry(shop);
	    if (data.getRetail() < 0){
	    	return true;
	    }
	    return false;
	}

}
