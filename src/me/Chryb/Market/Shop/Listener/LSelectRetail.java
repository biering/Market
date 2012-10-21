package me.Chryb.Market.Shop.Listener;

import me.Chryb.Market.Market;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.VectorUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LSelectRetail implements Listener{
	
	public static Market plugin;
	public LSelectRetail(Market instance){
		 plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onLeftClickSneaking(PlayerInteractEvent event){
		Block b = event.getClickedBlock();
		if (b == null) return;
		if (!(b.getTypeId() == 63) && !(b.getTypeId() == 68)) return;
		Action a = event.getAction();
		if (!(a.equals(Action.LEFT_CLICK_BLOCK))  || !(event.getPlayer().isSneaking())) return;
		
		    String world = b.getWorld().getName();
		    String location = VectorUtil.parseToString(b.getLocation().toVector());
		    ShopStore data = plugin.getDatabase().find(ShopStore.class).where().ieq("location", location).ieq("world", world).findUnique();
		    if (data == null) return;
		    
		    Player p = event.getPlayer();
		    MessageUtil message = new MessageUtil(MessageType.RETAIL_SELECTED);
		    message.send(p);
		    
		    Sign s = (Sign) event.getClickedBlock().getState();
		    if (Market.player_retail.containsKey(p)){
		    	Market.player_retail.remove(p);
		    	Market.player_retail.put(p, s);
		    	return;
		    }
		    Market.player_retail.put(p, s);
		    if (Market.player_purchase.containsKey(p)){
		    	Market.player_purchase.remove(p);
		    }
	}

}
