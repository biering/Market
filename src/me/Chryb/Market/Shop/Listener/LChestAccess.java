package me.Chryb.Market.Shop.Listener;

import me.Chryb.Market.Market;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LChestAccess implements Listener{
	
	public static Market plugin;
	public LChestAccess(Market instance){
		 plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onChestInteract(PlayerInteractEvent event){
		if (!(plugin.getConfig().getBoolean("Shop.ChestProtection.Enabled"))) return;
		
	    Player p = event.getPlayer();
	    if (Market.perm.has(p, "Market.shop.chest.access")){ event.setCancelled(false); return; }
		if (Market.perm.has(p, "Market.shop.package.admin")){ event.setCancelled(false); return; }
		
		Block b = event.getClickedBlock();
		if (b == null) return;
		
		if (!(b.getTypeId() == 54) && !(b instanceof Chest) && !(b instanceof DoubleChest)) return;
		Action a = event.getAction();
		if (!(a.equals(Action.RIGHT_CLICK_BLOCK))) return;
		    Chest c = (Chest) b.getState();
		    Location l = c.getLocation();
		    l.setY(l.getY() + 1);
		    
		    if (!(ChestUtil.isShopChest(c))) return;
		    if (ChestUtil.hasPlayerAccess(p, l)){
		    	event.setCancelled(false);
		    } else {
		    	
		    	event.setCancelled(true);
		    	MessageUtil message = new MessageUtil(MessageType.NO_SHOP_ACCESS);
		    	message.send(p);
		  
		    }
		    
	}

}
