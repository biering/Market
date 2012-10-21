package me.Chryb.Market.Util;

import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Shop.Shop;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;

public class ChestUtil {
	
	public static boolean hasChest(Location l){
		Location loc = l;
		World world = loc.getWorld();
		loc.setY(loc.getY() - 1);
		Block b = world.getBlockAt(loc);
		if (b instanceof Chest || b instanceof DoubleChest || b.getTypeId() == 54){
			return true;
		}
		return false;
	}
	
	public static Chest getShopChest(Location l){
		Location loc = l;
		World world = loc.getWorld();
		loc.setY(loc.getY() - 1);
		Block b = world.getBlockAt(loc);
		if (b instanceof Chest || b instanceof DoubleChest || b.getTypeId() == 54){
			return (Chest) b.getState();
		}
		return null;
	}
	
	public static boolean isShopChest(Chest c){
		Location l = c.getLocation();
		l.setY(l.getY() + 1);
		Shop shop = new Shop(c.getLocation());
		ShopStore data = Database.getEntry(shop);
		if (data != null){
			if (data.getMode().equalsIgnoreCase("Normal")){
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasPlayerAccess(Player p, Location l){
		Shop shop = new Shop(l);
		ShopStore data = Database.getEntry(shop);
	    if (data != null){
	    	if (data.getMode().equalsIgnoreCase("Normal")){
				if (data.getOwner().equalsIgnoreCase(p.getName())){
					return true;
				}
			}
	    }
	    return false;
	}

}
