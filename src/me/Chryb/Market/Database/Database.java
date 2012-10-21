package me.Chryb.Market.Database;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;

import me.Chryb.Market.Market;
import me.Chryb.Market.Shop.Shop;
import me.Chryb.Market.Util.VectorUtil;

public class Database {
	
	public static Market plugin;
	public Database(Market instance){
		 plugin = instance;
	}
	
	public static void setup(){
	        try {
	            plugin.getDatabase().find(ShopStore.class).findRowCount();
	        } catch (PersistenceException ex) {
	            Bukkit.getConsoleSender().sendMessage("Installing database for " + plugin.getDescription().getName() + " due to first time usage");
	            plugin.installDDLFile();
	        }
	}
	
	public static boolean hasEntry(Shop shop){
		String location = VectorUtil.parseToString(shop.getItemFrame().toVector());
		String world = shop.getItemFrame().getWorld().getName();
		ShopStore data = plugin.getDatabase().find(ShopStore.class).where().ieq("location", location).ieq("world", world).findUnique();
	    if (data == null) return false;
		return true;
	}
	
	public static ShopStore getEntry(Shop shop){
		String world = shop.getItemFrame().getWorld().getName();
		String location = VectorUtil.parseToString(shop.getItemFrame().toVector());
		return plugin.getDatabase().find(ShopStore.class).where().ieq("location", location).ieq("world", world).findUnique();
	}
	
	public static void deleteEntry(ShopStore data){
		plugin.getDatabase().delete(data);
	}
	
	public static void saveEntry(ShopStore data){
		plugin.getDatabase().save(data);
	}

}
