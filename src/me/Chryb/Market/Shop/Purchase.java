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

public class Purchase {
	
	public static void buy(Sign s, Transaction obj){
		Player client = obj.getClient();
		Shop shop = obj.getShop();
		ShopStore data = Database.getEntry(shop);
		Integer amount = obj.getAmount();
		Double cost = data.getPurchase() * amount;
		
		ItemStack item = new ItemStack(obj.getItem().getType(), amount, (short) 0, (byte) obj.getItemSubId());
		String itemName = MaterialUtil.getMaterialName(s.getLine(1));
		
		// ----- Client Transfer ----- //
		if (!(ValidationUtil.isAdminShop(s.getLocation()))){
		      String owner = data.getOwner();
		      Owner.deposit(owner, cost);
		      Player o = Bukkit.getServer().getPlayer(owner);
		      if (o != null){
		    	  
		    	  String client_purchase = Config.getLangFile().getString("CLIENT_PURCHASE");
		    	  client_purchase = client_purchase.replace("%player", client.getName());
		    	  client_purchase = client_purchase.replace("%amount", amount.toString());
		    	  client_purchase = client_purchase.replace("%item", itemName);
		    	  client_purchase = client_purchase.replace("%cost", cost.toString());
		    	  
		    	  o.sendMessage(ChatColor.GREEN + client_purchase);
			      
		      }
		      ChestUtil.getShopChest(Market.player_purchase.get(client).getLocation()).getInventory().removeItem(item);
		}
		InvUtil.addInvItems(client.getInventory(), item);
		Market.econ.withdrawPlayer(client.getName(), cost);
		
		String on_purchase = Config.getLangFile().getString("ON_PURCHASE");
		on_purchase = on_purchase.replace("%amount", amount.toString());
		on_purchase = on_purchase.replace("%cost", cost.toString());
		on_purchase = on_purchase.replace("%item", itemName);
		
		client.sendMessage(on_purchase);
		
	}

}
