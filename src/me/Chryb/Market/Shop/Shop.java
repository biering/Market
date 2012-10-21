package me.Chryb.Market.Shop;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Util.MessageUtil;
import me.Chryb.Market.Util.VectorUtil;
import me.Chryb.Market.Util.MessageUtil.MessageType;

public class Shop {
	
	private Location itemFrame;
	private ShopType shopType;
	private double purchase;
	private double retail;
	private Player owner;
	
	public Shop(Location itemFrame){
		this.itemFrame = itemFrame;
	}
	
	public void create(Player owner, ShopType shopType){
		this.owner = owner;
		String world = itemFrame.getWorld().getName();
		String location = VectorUtil.parseToString(itemFrame.toVector());
		ShopStore data = Database.getEntry(this);
		
		if (!(Database.hasEntry(this))){
			data = new ShopStore();
			data.setLocation(location);
			data.setWorld(world);
			data.setPurchase(0);
			data.setRetail(0);
			if (shopType.equals(ShopType.ADMIN)){
				data.setMode("Admin");
				data.setOwner("Admin");
			}
			if (shopType.equals(ShopType.NORMAL)){
				data.setMode("Normal");
				data.setOwner(owner.getName());
			}
			
			Database.saveEntry(data);
			
			MessageUtil message = new MessageUtil(MessageType.SHOP_CREATE);
			message.send(owner);
			
			return;
		}
		
		MessageUtil message = new MessageUtil(MessageType.SHOP_ALREADY_CREATED);
		message.send(owner);
		
	}
	
	public void delete(){
		ShopStore data = Database.getEntry(this);
		Database.deleteEntry(data);
	}
	
	public void setPurchase(double purchase){
		this.purchase = purchase;
		ShopStore data = Database.getEntry(this);
		if (!(Database.hasEntry(this))) return;
		data.setPurchase(purchase);
		Database.saveEntry(data);
	}
	
	public void setRetail(double retail){
		this.retail = retail;
		ShopStore data = Database.getEntry(this);
		if (!(Database.hasEntry(this))) return;
		data.setRetail(retail);
		Database.saveEntry(data);
	}
	
	public void setShopType(ShopType shopType){
		this.shopType = shopType;
		ShopStore data = Database.getEntry(this);
		if (!(Database.hasEntry(this))) return;
		if (shopType.equals(ShopType.ADMIN)){
			data.setMode("Admin");
		}
		if (shopType.equals(ShopType.NORMAL)){
			data.setMode("Normal");
		}
		Database.saveEntry(data);
	}
	
	public void setOwner(String p){
		ShopStore data = Database.getEntry(this);
		if (!(Database.hasEntry(this))) return;
		data.setOwner(p);
		Database.saveEntry(data);
	}
	
	public Location getItemFrame(){ return itemFrame; }
	public double getPurchase(){ return purchase; }
	public double getRetail(){ return retail; }
	public Player getOwner(){ return owner; }
	public ShopType getShopType(){ return shopType; }
	
	public enum ShopType{
		ADMIN,
		NORMAL
	}

}
