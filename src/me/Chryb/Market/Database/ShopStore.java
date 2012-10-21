package me.Chryb.Market.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "FrameShops")
public class ShopStore {
	
	//--- ID ---// 
	@Id private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    //--- Location ---// 
    @Column private String location;
    public String getLocation() { return location; }
    public void setLocation(String loaction) { this.location = loaction; }
    
    //--- World ---// 
    @Column private String world;
    public String getWorld() { return world; }
    public void setWorld(String world) { this.world = world; }
    
    //--- Owner ---// 
    @Column private String owner;
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    
    //--- Purchase ---// 
    // (Einkaufspreis)
    @Column private double purchase;
    public double getPurchase() { return purchase; }
    public void setPurchase(double purchase) { this.purchase = purchase; }
    
    //--- Retail ---// 
    // (Verkauspreis)
    @Column private double retail;
    public double getRetail() { return retail; }
    public void setRetail(double retail) { this.retail = retail; }
    
    //--- Mode ---// 
    // (Admin, Normal)
    @Column private String mode;
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

}
