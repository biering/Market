package me.Chryb.Market.Money;

import me.Chryb.Market.Market;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class EmeraldListener implements Listener{
	
	public static Market plugin;
	public EmeraldListener(Market plugin) {
		EmeraldListener.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEmeraldPickup(PlayerPickupItemEvent event) {
		if (!(plugin.getConfig().getBoolean("EMERALD_CONVERTER.Enabled"))) return;
		
		if (event.getItem().getItemStack().getTypeId() == 388){
			Player p = event.getPlayer();
			ItemStack item = event.getItem().getItemStack();
			Market.econ.depositPlayer(p.getName(), item.getAmount());
		
			p.sendMessage(ChatColor.AQUA + "+ " + item.getAmount() + "c");
			
			event.getItem().remove();
			event.setCancelled(true);
		
		}
	}

}
