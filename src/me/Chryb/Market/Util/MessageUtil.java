package me.Chryb.Market.Util;

import me.Chryb.Market.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {
	
	private MessageType type;
	
	public MessageUtil(MessageType type){
		this.type = type;
	}
	
	public void send(Player p){
		
		if (type.equals(MessageType.CREATIVE_MODE_PROT)){
			p.sendMessage(ChatColor.YELLOW + Config.getLangFile().getString("CREATIVE_MODE_PROT"));
			return;
		}
		
		if (type.equals(MessageType.NO_PERMISSION)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_PERMISSION"));
			return;
		}
		
		if (type.equals(MessageType.NO_SHOP_ACCESS)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_SHOP_ACCESS"));
			return;
		}
		
		if (type.equals(MessageType.NO_PURCHASES_RETAILS)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_PURCHASES_RETAILS"));
            return;
		}
		
		if (type.equals(MessageType.TYPE_AN_AMOUNT)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("TYPE_AN_AMOUNT"));
	    	return;
		}
		
		if (type.equals(MessageType.TYPE_A_PRICE)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("TYPE_A_PRICE"));
			return;
		}
		
		if (type.equals(MessageType.YOU_CANNOT_TYPE_AMOUNT_ALL)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("YOU_CANNOT_TYPE_AMOUNT_ALL"));
			return;
		}
		
		if (type.equals(MessageType.NO_VALID_SHOP)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_VALID_SHOP"));
			return;
		}
		
		if (type.equals(MessageType.CMD_USAGE)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CMD_USAGE"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_SET_ADMIN)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_SET_ADMIN"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_SET_NORMAL)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_SET_NORMAL"));
			return;
		}
		
		if (type.equals(MessageType.CMD_NOT_RECOGNIZED)){
			p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("CMD_NOT_RECOGNIZED"));
			p.sendMessage(ChatColor.GRAY + Config.getLangFile().getString("SEE_SHOP_HELP"));
			return;
		}
		
		if (type.equals(MessageType.NO_ITEMFRAME_SELECTED)){
			p.sendMessage(ChatColor.YELLOW + Config.getLangFile().getString("NO_ITEMFRAME_SELECTED"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_DELETE)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_DELETE"));
			return;
		}
		
		if (type.equals(MessageType.TYPE_ADMIN_NORMAL)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("TYPE_ADMIN_NORMAL"));
			return;
		}
		
		if (type.equals(MessageType.PURCHASE_LOCKED)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("PURCHASE_LOCKED"));
			return;
		}
		
		if (type.equals(MessageType.RETAIL_LOCKED)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("RETAIL_LOCKED"));
			return;
		}
		
		if (type.equals(MessageType.CLIENT_HAS_NOT_ENOUGH_MONEY)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CLIENT_HAS_NOT_ENOUGH_MONEY"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_HAS_NO_CHEST)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("SHOP_HAS_NO_CHEST"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_HAS_NOT_ENOUGH_STOCK)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("SHOP_HAS_NOT_ENOUGH_STOCK"));
			return;
		}
		
		if (type.equals(MessageType.CLIENT_HAS_NOT_ENOUGH_SPACE)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CLIENT_HAS_NOT_ENOUGH_SPACE"));
			return;
		}
		
		if (type.equals(MessageType.CLIENT_HAS_NOT_ENOUGH_ITEMS)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CLIENT_HAS_NOT_ENOUGH_ITEMS"));
			return;
		}
		
		if (type.equals(MessageType.CHEST_HAS_NOT_ENOUGH_SPACE)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CHEST_HAS_NOT_ENOUGH_SPACE"));
			return;
		}
		
		if (type.equals(MessageType.ITEMFRAME_ALREADY_SELECTED)){
			p.sendMessage(ChatColor.YELLOW + Config.getLangFile().getString("ITEMFRAME_ALREADY_SELECTED"));
			return;
		}
		
		if (type.equals(MessageType.ITEMFRAME_SELECTED)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("ITEMFRAME_SELECTED"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_CREATE)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_CREATE"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_ALREADY_CREATED)){
			p.sendMessage(Config.getLangFile().getString("SHOP_ALREADY_CREATED"));
			return;
		}
		
		if (type.equals(MessageType.PURCHASE_SELECTED)){
			p.sendMessage(Config.getLangFile().getString("PURCHASE_SELECTED"));
			return;
		}
		
		if (type.equals(MessageType.RETAIL_SELECTED)){
			p.sendMessage(Config.getLangFile().getString("RETAIL_SELECTED"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_ADMIN)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_ADMIN"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_NORMAL)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_NORMAL"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_NORMAL_AMOUNT)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_NORMAL_AMOUNT"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_PURCHASE)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_PURCHASE"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_PURCHASE_LOCKED)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_PURCHASE_LOCKED"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_RETAIL)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_RETAIL"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_RETAIL_LOCKED)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_RETAIL_LOCKED"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_INFO_OWNER)){
			p.sendMessage(Config.getLangFile().getString("SHOP_INFO_OWNER"));
			return;
		}
		
		if (type.equals(MessageType.CHEST_FULL)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("CHEST_FULL"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_SET_PURCHASE)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_SET_PURCHASE"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_SET_RETAIL)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_SET_RETAIL"));
			return;
		}
		
		if (type.equals(MessageType.SHOP_SET_OWNER)){
			p.sendMessage(ChatColor.GOLD + Config.getLangFile().getString("SHOP_SET_OWNER"));
			return;
		}
		
		if (type.equals(MessageType.OWNER_HAS_NOT_ENOUGH_MONEY)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("OWNER_HAS_NOT_ENOUGH_MONEY"));
			return;
		}
		
		if (type.equals(MessageType.NO_SHOP_PERM)){
			p.sendMessage(ChatColor.RED + Config.getLangFile().getString("NO_SHOP_PERM"));
			return;
		}
		
		
	}
	
	public enum MessageType{
		CREATIVE_MODE_PROT,
		NO_SHOP_PERM,
		OWNER_HAS_NOT_ENOUGH_MONEY,
		CMD_USAGE,
		NO_PERMISSION,
		SHOP_SET_PURCHASE,
		SHOP_SET_RETAIL,
		SHOP_SET_OWNER,
		SHOP_INFO_ADMIN,
		SHOP_INFO_NORMAL,
		SHOP_INFO_NORMAL_AMOUNT,
		SHOP_INFO_PURCHASE,
		SHOP_INFO_PURCHASE_LOCKED,
		SHOP_INFO_RETAIL,
		SHOP_INFO_RETAIL_LOCKED,
		SHOP_INFO_OWNER,
		CLIENT_RETAIL,
		CLIENT_PURCHASE,
		CHEST_FULL,
		RETAIL_SELECTED,
		PURCHASE_SELECTED,
		PLAYER_PURCHASE_FROM_YOU,
		NO_SHOP_ACCESS,
		NO_PURCHASES_RETAILS,
		TYPE_AN_AMOUNT,
		TYPE_A_PRICE,
		TYPE_ADMIN_NORMAL,
		YOU_CANNOT_TYPE_AMOUNT_ALL,
		NO_VALID_SHOP,
		SHOP_SET_ADMIN,
		SHOP_SET_NORMAL,
		CMD_NOT_RECOGNIZED,
		NO_ITEMFRAME_SELECTED,
		SHOP_DELETE,
		SHOP_CREATE,
		SHOP_ALREADY_CREATED,
		
		PURCHASE_LOCKED,
		RETAIL_LOCKED,
		CLIENT_HAS_NOT_ENOUGH_MONEY,
		CLIENT_HAS_NOT_ENOUGH_ITEMS,
		SHOP_HAS_NO_CHEST,
		SHOP_HAS_NOT_ENOUGH_STOCK,
		CLIENT_HAS_NOT_ENOUGH_SPACE,
		CHEST_HAS_NOT_ENOUGH_SPACE,
		
		ITEMFRAME_ALREADY_SELECTED,
		ITEMFRAME_SELECTED
	}

}
