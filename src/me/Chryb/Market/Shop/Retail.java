package me.Chryb.Market.Shop;

import me.Chryb.Market.Config;
import me.Chryb.Market.Market;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.InvUtil;
import me.Chryb.Market.Util.MaterialUtil;
import me.Chryb.Market.Util.ValidationUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Retail {
	
	public static void sell(Sign s, Transaction obj){
		Player client = obj.getClient();
		Shop shop = obj.getShop();
		ShopStore data = Database.getEntry(shop);
		Integer amount = obj.getAmount();
		Double cost = data.getRetail() * amount;
		
		ItemStack item = new ItemStack(obj.getItem().getType(), amount, (short) 0, (byte) obj.getItemSubId());
		String itemName = MaterialUtil.getMaterialName(s.getLine(1));
		
		// ----- Client Transfer ----- //
		if (!(ValidationUtil.isAdminShop(s.getLocation()))){
		      String owner = data.getOwner();
		      Owner.withdraw(owner, cost);
		      Player o = Bukkit.getServer().getPlayer(owner);
		      if (o != null){
			      
		    	  String client_retail = Config.getLangFile().getString("CLIENT_RETAIL");
		    	  client_retail = client_retail.replace("%player", client.getName());
		    	  client_retail = client_retail.replace("%amount", amount.toString());
		    	  client_retail = client_retail.replace("%item", itemName);
		    	  client_retail = client_retail.replace("%cost", cost.toString());
		    	  
		    	  o.sendMessage(ChatColor.GREEN + client_retail);
		    	  
		      }
			  InvUtil.addInvItems(ChestUtil.getShopChest(Market.player_retail.get(client).getLocation()).getInventory(), item);
		}
		client.getInventory().removeItem(item);
		Market.econ.depositPlayer(client.getName(), cost);
		
		String on_retail = Config.getLangFile().getString("ON_RETAIL");
		on_retail = on_retail.replace("%amount", amount.toString());
		on_retail = on_retail.replace("%cost", cost.toString());
		on_retail = on_retail.replace("%item", itemName);
		
		client.sendMessage(on_retail);
	}

}
