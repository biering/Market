package me.Chryb.Market.Shop;

import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Chryb.Market.Market;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.InvUtil;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

public class TValidation {
	
	private Player client;
	private Transaction obj;
	
	public TValidation(TException tExcep, Transaction obj){
		this.obj = obj;
		this.client = obj.getClient();
		if (tExcep.equals(TException.RETAIL_LOCKED)){
			retail_locked_excep();
		}
		if (tExcep.equals(TException.PURCHASE_LOCKED)){
			purchase_locked_excep();
		}
		if (tExcep.equals(TException.CLIENT_HAS_NOT_ENOUGH_MONEY)){
			client_has_not_enough_money_excep();
		}
		if (tExcep.equals(TException.CLIENT_HAS_NOT_ENOUGH_ITEMS)){
			client_has_not_enough_items_excep();
		}
		if (tExcep.equals(TException.CLIENT_HAS_NOT_ENOUGH_SPACE)){
			client_has_not_enough_space_excep();
		}
		if (tExcep.equals(TException.SHOP_HAS_NOT_ENOUGH_SPACE)){
			shop_has_not_enough_space_excep();
		}
		if (tExcep.equals(TException.SHOP_HAS_NOT_ENOUGH_MONEY)){
			shop_has_not_enough_money_excep();
		}
		if (tExcep.equals(TException.SHOP_HAS_NO_CHEST)){
			shop_has_no_chest_excep();
		}
		if (tExcep.equals(TException.SHOP_OUT_OF_STOCK)){
			shop_out_of_stock_excep();
		}
	}
	
	public void retail_locked_excep(){
		double price = obj.getPrice();
		if (price < 0){
			
			MessageUtil message = new MessageUtil(MessageType.RETAIL_LOCKED);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void purchase_locked_excep(){
		double price = obj.getPrice();
		if (price < 0){
			
			MessageUtil message = new MessageUtil(MessageType.PURCHASE_LOCKED);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void client_has_not_enough_money_excep(){
		double a = Market.econ.getBalance(client.getName());
		double cost = obj.getPrice() * obj.getAmount();
		if (!(a >= cost)){ 
			
			MessageUtil message = new MessageUtil(MessageType.CLIENT_HAS_NOT_ENOUGH_MONEY);
			message.send(client);
		
		    obj.setTCancelled(true);
		}
	}
	
	public void client_has_not_enough_items_excep(){
		if (!(InvUtil.getItemAmount(client.getInventory(), obj.getItem()) >= obj.getAmount())){
			
			MessageUtil message = new MessageUtil(MessageType.CLIENT_HAS_NOT_ENOUGH_ITEMS);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void client_has_not_enough_space_excep(){
		if (!(InvUtil.hasItemsEnoughSpace(client.getInventory(), obj.getItem().getType(), obj.getItem().getDurability(), obj.getAmount()))){
			
			MessageUtil message = new MessageUtil(MessageType.CLIENT_HAS_NOT_ENOUGH_SPACE);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void shop_has_not_enough_space_excep(){
		if (!(InvUtil.hasItemsEnoughSpace(ChestUtil.getShopChest(Market.player_retail.get(client).getLocation()).getInventory(), obj.getItem().getType(), obj.getItem().getDurability(), obj.getAmount()))){
			
			MessageUtil message = new MessageUtil(MessageType.CHEST_HAS_NOT_ENOUGH_SPACE);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void shop_has_not_enough_money_excep(){
		double a = Market.econ.getBalance(client.getName());
		double cost = obj.getPrice() * obj.getAmount();
		if (!(a >= cost)){ 
			
			MessageUtil message = new MessageUtil(MessageType.OWNER_HAS_NOT_ENOUGH_MONEY);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void shop_has_no_chest_excep(){
		if (obj.getChest() == null){
			
			MessageUtil message = new MessageUtil(MessageType.SHOP_HAS_NO_CHEST);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public void shop_out_of_stock_excep(){
		Chest c = obj.getChest();
		if (!(InvUtil.hasChestEnoughItems(c, new ItemStack(obj.getItem().getType(), obj.getAmount(), (short) obj.getItem().getData().getData())))){
			
			MessageUtil message = new MessageUtil(MessageType.SHOP_HAS_NOT_ENOUGH_STOCK);
			message.send(client);
			
			obj.setTCancelled(true);
		}
	}
	
	public enum TException{
		RETAIL_LOCKED,
		PURCHASE_LOCKED,
		CLIENT_HAS_NOT_ENOUGH_MONEY,
		CLIENT_HAS_NOT_ENOUGH_ITEMS,
		CLIENT_HAS_NOT_ENOUGH_SPACE,
		SHOP_HAS_NOT_ENOUGH_SPACE,
		SHOP_HAS_NOT_ENOUGH_MONEY,
		SHOP_HAS_NO_CHEST,
		SHOP_OUT_OF_STOCK
	}

}
