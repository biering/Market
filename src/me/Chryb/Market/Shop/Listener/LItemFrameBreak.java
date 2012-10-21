package me.Chryb.Market.Shop.Listener;

import me.Chryb.Market.Market;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Shop.Owner;
import me.Chryb.Market.Shop.Shop;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LItemFrameBreak implements Listener{
	
	public static Market plugin;
	public LItemFrameBreak(Market instance){
		 plugin = instance;
		}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemFrameBreak(BlockBreakEvent event){
		Block b = event.getBlock();
		if (b == null) return;
		if (!(b.getTypeId() == 63) && !(b.getTypeId() == 68)) return;
		
		    Shop shop = new Shop(b.getLocation());
		    if (!(Database.hasEntry(shop))) return;
		
		    Player p = event.getPlayer();
		    
		    if (p.getGameMode().getValue() == 1){
		    	event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
		    	return;
		    }
		    
		    if (!(event.isCancelled())){
		    	if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), b.getLocation())){
		            shop.delete();
		    
			        MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
			        message.send(p);
		    	}
		    }
		    
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBehindItemFrameBreak(BlockBreakEvent event){
		Block b = event.getBlock();
		if (b == null) return;
		
		Location l = b.getLocation();
		World w = b.getWorld();
	        Player p = event.getPlayer();
		
	        // X + 1 //
		l.setX(l.getX() + 1);
		Block block_a = w.getBlockAt(l);
		if (!(block_a.getTypeId() == 63 && !(block_a.getTypeId() == 68))) return;
		Shop shop_a = new Shop(block_a.getLocation());
		if (Database.hasEntry(shop_a)){
			if (p.getGameMode().getValue() == 1){
				event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
			}
			if (!(event.isCancelled())){
			    if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), shop_a)){
	                        shop_a.delete();
	    
		                MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
		                message.send(p);
	    	            }
		        }
		}
		l = b.getLocation();
		
		// X - 1 //
		l.setX(l.getX() - 1);
		Block block_b = w.getBlockAt(l);
		if (!(block_b.getTypeId() == 63 && !(block_b.getTypeId() == 68))) return;
		Shop shop_b = new Shop(block_b.getLocation());
		if (Database.hasEntry(shop_b)){
			if (p.getGameMode().getValue() == 1){
				event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
			}
			if (!(event.isCancelled())){
			    if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), shop_b)){
	                        shop_b.delete();
	    
		                MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
		                message.send(p);
	    	            }
		        }
		}
		l = b.getLocation();
		
		// Z + 1 //
		l.setZ(l.getZ() + 1);
		Block block_c = w.getBlockAt(l);
		if (!(block_c.getTypeId() == 63 && !(block_c.getTypeId() == 68))) return;
		Shop shop_c = new Shop(block_c.getLocation());
		if (Database.hasEntry(shop_c)){
			if (p.getGameMode().getValue() == 1){
				event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
			}
			if (!(event.isCancelled())){
			    if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), shop_c)){
	                        shop_c.delete();
	    
		                MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
		                message.send(p);
	    	            }
		        }
		}
		l = b.getLocation();
		
		// Z - 1 //
		l.setZ(l.getZ() - 1);
		Block block_d = w.getBlockAt(l);
		if (!(block_d.getTypeId() == 63 && !(block_d.getTypeId() == 68))) return;
		Shop shop_d = new Shop(block_d.getLocation());
		if (Database.hasEntry(shop_d)){
			if (p.getGameMode().getValue() == 1){
				event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
			}
			if (!(event.isCancelled())){
			    if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), shop_d)){
	                        shop_d.delete();
	    
		                MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
		                message.send(p);
	    	            }
		        }
		}
		l = b.getLocation();
		
		// Y + 1 //
		l.setY(l.getY() + 1);
		Block block_e = w.getBlockAt(l);
		if (!(block_e.getTypeId() == 63 && !(block_e.getTypeId() == 68))) return;
		Shop shop_e = new Shop(block_e.getLocation());
		if (Database.hasEntry(shop_e)){
			if (p.getGameMode().getValue() == 1){
				event.setCancelled(true);
				MessageUtil message = new MessageUtil(MessageType.CREATIVE_MODE_PROT);
				message.send(p);
			}
			if (!(event.isCancelled())){
			    if (Market.perm.has(p, "Market.shop.package.admin") || Owner.is(p.getName(), shop_e)){
	                        shop_e.delete();
	    
		                MessageUtil message = new MessageUtil(MessageType.SHOP_DELETE);
		                message.send(p);
	    	            }
		        }
		}
		    
	}

}
