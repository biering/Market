package me.Chryb.Market.Shop.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Chryb.Market.Config;
import me.Chryb.Market.Market;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.InvUtil;
import me.Chryb.Market.Util.MaterialUtil;
import me.Chryb.Market.Util.VectorUtil;

public class LShopInformation implements Listener{
	
	public static Market plugin;
	public LShopInformation(Market instance){
		 plugin = instance;
		}
	
        @EventHandler(priority = EventPriority.NORMAL)
        public void onRightClick(PlayerInteractEvent event){
		Block b = event.getClickedBlock();
		if (b == null) return;
		if (!(b.getTypeId() == 63) && !(b.getTypeId() == 68)) return;
		Action a = event.getAction();
		if (!(a.equals(Action.RIGHT_CLICK_BLOCK))  || event.getPlayer().isSneaking()) return;
		    String world = b.getWorld().getName();
		    String location = VectorUtil.parseToString(b.getLocation().toVector());
		    Sign s = (Sign)b.getState();
		    ShopStore data = plugin.getDatabase().find(ShopStore.class).where().ieq("location", location).ieq("world", world).findUnique();
		    if (data == null) return;
		    Player p = event.getPlayer();
		    
		    ItemStack item = new ItemStack(Material.getMaterial(MaterialUtil.getId(s.getLine(1))), 1, (short) MaterialUtil.getSubId(s.getLine(1)));
		    String itemName = MaterialUtil.getMaterialName(s.getLine(1));
		    
		    if (itemName.contentEquals("")){ p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_VALID_ITEM")); return; }
		   
		    if (data.getMode().equalsIgnoreCase("Admin")){
		    	String shop_info_admin = Config.getLangFile().getString("SHOP_INFO_ADMIN");
		    	shop_info_admin = shop_info_admin.replace("%item", itemName);
	    	        p.sendMessage(shop_info_admin);
		    }
		    
		    if (data.getMode().equalsIgnoreCase("Normal")){
			Chest c = ChestUtil.getShopChest(b.getLocation());
			if (c != null){
		            String shop_info_normal_amount = Config.getLangFile().getString("SHOP_INFO_NORMAL_AMOUNT");
			    Integer itemamount = InvUtil.getItemAmount(c.getInventory(), item);
			    shop_info_normal_amount = shop_info_normal_amount.replace("%item", itemName);
			    shop_info_normal_amount = shop_info_normal_amount.replace("%amount", itemamount.toString());
			    p.sendMessage(shop_info_normal_amount);
			} else {
			    String shop_info_normal = Config.getLangFile().getString("SHOP_INFO_NORMAL");
			    shop_info_normal = shop_info_normal.replace("%item", itemName);
			    p.sendMessage(shop_info_normal);
			}
		    }
		    
		    double purchase = data.getPurchase();
		    if (purchase < 0){
			p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SHOP_INFO_PURCHASE") + ChatColor.RED + Config.getLangFile().getString("SHOP_INFO_PURCHASE_LOCKED"));
		    } else {
			p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SHOP_INFO_PURCHASE") + ChatColor.GOLD + data.getPurchase());
		    }
		    
		    double retail = data.getRetail();
		    if (retail < 0){
		    	p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SHOP_INFO_RETAIL") + ChatColor.RED + Config.getLangFile().getString("SHOP_INFO_RETAIL_LOCKED"));
		    } else {
		    	p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SHOP_INFO_RETAIL") + ChatColor.GOLD + data.getRetail());
		    }
		    
		    if (data.getMode().equalsIgnoreCase("Normal")){
		    	p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SHOP_INFO_OWNER") + ChatColor.AQUA + data.getOwner());
		    }
		    
		    	Chest c = ChestUtil.getShopChest(b.getLocation());
		    	if (c != null){
		    	    if (!(InvUtil.hasItemsEnoughSpace(c.getInventory(), item.getType(), item.getDurability(), 1))){
		    	    	p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CHEST_FULL"));
		    	    }
		    	}
		    	
	}

}
