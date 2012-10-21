package me.Chryb.Market.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TransactionEvent extends Event{

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private static final HandlerList handlerList = new HandlerList();
	private final TransactionType type;
	
	private final Inventory buyerInv;
	private final Inventory chestInv;
	
	private final Player buyer;
	private final OfflinePlayer owner;
	
	private final ItemStack[] stock;
	private final double price;
	
	private final Sign sign;
	
	public TransactionEvent(PreTransactionEvent event, Sign sign){
		this.type = event.getTransactionType();
		
		this.buyerInv = event.getBuyerInv();
		this.chestInv = event.getChestInv();
	}
	
	
	public TransactionType getTransactionType(){
		return type;
	}
	
	public Inventory getBuyerInv(){
		return buyerInv;
	}
	
	public Inventory getChestInv(){
		return chestInv;
	}
	
	public Player getBuyer(){
		return buyer;
	}
	
	public OfflinePlayer getOwner(){
		return owner;
	}
	
	public double getPrice(){
		return price;
	}
	
	public ItemStack[] getStock(){
		return stock;
	}
	
	public Sign getSign(){
		return sign;
	}
	
	public HandlerList getHandlerList(){
		return handlerList;
	}
	
	public enum TransactionType{
		BUY,
		SELL
	}*/
	
	

}
