package me.Chryb.Market.Shop.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.Chryb.Market.Market;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.ValidationUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

public class LItemFrameSelecter implements Listener{
	
	public static Market plugin;
	public LItemFrameSelecter(Market instance){
		 plugin = instance;
		}
	
	 @EventHandler(priority = EventPriority.HIGHEST)
	 public void onSelecterLMB(PlayerInteractEvent event){
       Action action = event.getAction();
	   Player p = event.getPlayer();
	   if (p.getItemInHand().getTypeId() != 0){
	   ItemStack i = event.getItem();
	   if (action.equals(Action.RIGHT_CLICK_BLOCK) && !p.isSneaking()){
		if (i.getTypeId() == 369){
			if (p.hasPermission("Market.tools.itemFrameSelection") || 
					p.hasPermission("Market.shop.package.user") ||
					p.hasPermission("Market.shop.package.admin")){
			Location l = event.getClickedBlock().getLocation();
			if (l.getBlock().getTypeId() == 63 || l.getBlock().getTypeId() == 68){
				Sign s = (Sign) l.getBlock().getState();
				// Sign only //
				if (!(ValidationUtil.isValidItemFrame(s))){ p.sendMessage(ChatColor.RED + "No Valid ItemFrame"); return; }
				// Sign only //
				if (Market.selected_itemframe.containsKey(p)){
					if (s.equals(Market.selected_itemframe.get(p))){
						 
						MessageUtil message = new MessageUtil(MessageType.ITEMFRAME_ALREADY_SELECTED);
						message.send(p);
				    	  
						return;
					}
					Market.selected_itemframe.remove(p);
					Market.selected_itemframe.put(p, s);
					
					MessageUtil message = new MessageUtil(MessageType.ITEMFRAME_SELECTED);
					message.send(p);
			    	  
					return;
				}
				Market.selected_itemframe.put(p, s);
				
				MessageUtil message = new MessageUtil(MessageType.ITEMFRAME_SELECTED);
				message.send(p);
		    	  
			}
		}
		}
	 }
	 }
	}
	
	 @EventHandler(priority = EventPriority.HIGHEST)
	 public void onSelecterBreak(BlockBreakEvent event){
		 Player player = event.getPlayer();
		 if (player.getItemInHand().getTypeId() != 0){
			 if (player.hasPermission("Market.tools.itemFrameSelection") || 
						player.hasPermission("Market.shop.package.user") ||
						player.hasPermission("Market.shop.package.admin")){
			     ItemStack i = event.getPlayer().getItemInHand();
				 if (i.getTypeId() == 369){
					event.setCancelled(true);
					}
				 }
		     }
		 }

}
