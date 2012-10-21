package me.Chryb.Market.Shop;

import me.Chryb.Market.Market;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Shop.TValidation.TException;
import me.Chryb.Market.Util.ChestUtil;
import me.Chryb.Market.Util.MaterialUtil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Transaction {
	
	private boolean TCancelled;
	private TransactionType transactionType;
	private ShopType shopType;
	private Location itemFrame;
	private Player client;
	private Chest chest;
	private String owner;
	private int amount;
	private double price;
	
	private Shop shop;
	
	private int itemId;
	private int itemSubId;
	private ItemStack item;
	
	public Transaction(Location itemFrame, TransactionType transactionType, Player client, int amount){
		this.transactionType = transactionType;
		this.client = client;
		this.amount = amount;
		this.TCancelled = false;
		this.shop = new Shop(itemFrame);
		
		ShopStore data = Database.getEntry(shop);
		
		// SHOPTYPE //
		if (data.getMode().equalsIgnoreCase("Admin")){ shopType = ShopType.ADMIN; }
		if (data.getMode().equalsIgnoreCase("Normal")){ shopType = ShopType.NORMAL; }
		
		// PRICE, ItemMaterial, ItemData //
		if (transactionType.equals(TransactionType.PURCHASE)){ 
			price = data.getPurchase(); 
			itemId = MaterialUtil.getId(Market.player_purchase.get(client).getLine(1));
			itemSubId = MaterialUtil.getSubId(Market.player_purchase.get(client).getLine(1));
			item = new ItemStack(Material.getMaterial(itemId), 1);
			item.setDurability((short) itemSubId);
			this.itemFrame = Market.player_purchase.get(client).getLocation();
		}
		if (transactionType.equals(TransactionType.RETAIL)){ 
			price = data.getRetail(); 
			itemId = MaterialUtil.getId(Market.player_retail.get(client).getLine(1));
			itemSubId = MaterialUtil.getSubId(Market.player_retail.get(client).getLine(1));
			item = new ItemStack(Material.getMaterial(itemId), 1);
			item.setDurability((short) itemSubId);
			this.itemFrame = Market.player_retail.get(client).getLocation();
		}
		
		// CHEST, OWNER //
		if (shopType.equals(ShopType.NORMAL)){
			owner = data.getOwner();
		} else { chest = null; owner = null; }
		
		
	}
	
    /**
    * @param ------
    * @run starts the transaction
    */
	public void run(){
		
	    if (transactionType.equals(TransactionType.PURCHASE)){
		Location l = Market.player_purchase.get(client).getLocation();
		chest = ChestUtil.getShopChest(l);
	    }
            if (transactionType.equals(TransactionType.RETAIL)){
        	Location l = Market.player_retail.get(client).getLocation();
        	chest = ChestUtil.getShopChest(l);
	    }
		
    /** TransactionType - @PURCHASE */
	   if (transactionType.equals(TransactionType.PURCHASE)){
			
	    new TValidation(TException.PURCHASE_LOCKED, this);
            if (TCancelled == true) return;
			
       /** ShopType - @ADMIN_SHOP */
	   if (shopType.equals(ShopType.ADMIN)){
				
	    new TValidation(TException.CLIENT_HAS_NOT_ENOUGH_MONEY, this);
	    if (TCancelled == true) return;
				
			/** ----- @TRANSACTION ----- */
			Purchase.buy(Market.player_purchase.get(client), this);
			Market.player_purchase.remove(client);
			return;
			/** ----- ------------ ----- */
                    }
			   
       /** ShopType - @NORMAL_SHOP */	
	    new TValidation(TException.SHOP_HAS_NO_CHEST, this);
            if (TCancelled == true) return;
		   
            new TValidation(TException.SHOP_OUT_OF_STOCK, this);
            if (TCancelled == true) return;
			
            new TValidation(TException.CLIENT_HAS_NOT_ENOUGH_MONEY, this);
            if (TCancelled == true) return;
			
            new TValidation(TException.CLIENT_HAS_NOT_ENOUGH_SPACE, this);
            if (TCancelled == true) return;
			
			
                        /** ----- @TRANSACTION ----- */
			Purchase.buy(Market.player_purchase.get(client), this);
			Market.player_purchase.remove(client);
			return;
			/** ----- ------------ ----- */
			
		}
		
    /** TransactionType - @RETAIL */
        if (transactionType.equals(TransactionType.RETAIL)){
			
	    new TValidation(TException.RETAIL_LOCKED, this);
	    if (TCancelled == true) return;
			
       /** ShopType - @ADMIN_SHOP */
       if (shopType.equals(ShopType.ADMIN)){
				
	    new TValidation(TException.CLIENT_HAS_NOT_ENOUGH_ITEMS, this);
	    if (TCancelled == true) return;
				
		        /** ----- @TRANSACTION ----- */
			Retail.sell(Market.player_retail.get(client), this);
			Market.player_retail.remove(client);
			return;
		        /** ----- ------------ ----- */
	}
			
       /** ShopType - @NORMAL_SHOP */
	    new TValidation(TException.SHOP_HAS_NO_CHEST, this);
	    if (TCancelled == true) return;
			
	    new TValidation(TException.CLIENT_HAS_NOT_ENOUGH_ITEMS, this);
            if (TCancelled == true) return;
			
	    new TValidation(TException.SHOP_HAS_NOT_ENOUGH_SPACE, this);
	    if (TCancelled == true) return;
			
	    new TValidation(TException.SHOP_HAS_NOT_ENOUGH_MONEY, this);
	    if (TCancelled == true) return;
			
		        /** ----- @TRANSACTION ----- */
			Retail.sell(Market.player_retail.get(client), this);
			Market.player_retail.remove(client);
			return;
			/** ----- ------------ ----- */
			
		}
	}
	
    /**
    * @param ------
    * @return ::TransactionType - TransactionType (PURCHASE:RETAIL)
    */
	public TransactionType getTransactionType(){
		return transactionType;
	}
	
    /**
    * @param ------
    * @return ::ShopType - ShopType (ADMIN:NORMAL)
    */
	public ShopType getShopType(){
		return shopType;
	}
	
    /**
    * @param ------
    * @return ::Location - ItemFrame Loc
    */
	public Location getItemFrame(){
		return itemFrame;
	}
	
    /**
    * @param ------
    * @return ::Player - Client
    */
	public Player getClient(){
		return client;
	}
	
    /**
    * @param ------
    * @return ::Chest - ShopChest
    */
	public Chest getChest(){
		return chest;
	}
	
    /**
    * @param ------
    * @return ::String - ShopOwner
    */
	public String getOwner(){
		return owner;
	}
	
    /**
    * @param ------
    * @return ::int - ItemAmount
    */
	public int getAmount(){
		return amount;
	}
	
    /**
    * @param ------
    * @return ::double - Price
    */
	public double getPrice(){
		return price;
	}
	
    /**
    * @param ------
    * @return ::ItemStack - Item
    */
	public ItemStack getItem(){
		return item;
	}
	
    /**
    * @param ------
    * @return ::int - ItemId
    */
	public int getItemId(){
		return itemId;
	}
	
    /**
    * @param ------
    * @return ::int - ItemSubId
    */
	public int getItemSubId(){
		return itemSubId;
	}
	
    /**
    * @param ------
    * @return ::boolean - TCancelled Value
    */
	public boolean getTCancelled(){
		return TCancelled;
	}
	
    /**
    * @param value - boolean
    * @run TCancelled -> true:false
    */
	public void setTCancelled(boolean value){
		TCancelled = value;
	}
	
    /**
    * @param ------
    * @return ::Shop - Shop Object
    */
	public Shop getShop(){
		return shop;
	}
	
	public enum TransactionType{
		PURCHASE,
		RETAIL
	}
	
	public enum ShopType{
		ADMIN,
		NORMAL
	}

}
