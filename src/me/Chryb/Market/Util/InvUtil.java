package me.Chryb.Market.Util;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class InvUtil {
	
    // GET ITEM AMOUNT IN INVENTORY //
    public static int getItemAmount(Inventory inv, ItemStack item){
	int finalamount = 0;
	int itemId = item.getTypeId();
	short data = item.getDurability();
	
	for (ItemStack is : inv.getContents()) { 
	     if (is != null && is.getTypeId() == itemId && is.getDurability() == data){
	         finalamount = finalamount + is.getAmount();
	     }
	}
	
	return finalamount;
    }
    
    /*public static int getItemAmount(Inventory inv, Material m, short dura){
	int finalamount = 0;
	int index = 0;
	
	ItemStack test = inv.getItem(0);
	Bukkit.broadcastMessage("dura: " + test.getDurability());
		
	if (!(MaterialUtil.isMaterialStackable(m))){
		for (ItemStack is : inv.getContents()) {
		    
		    if (is != null && is.getType().equals(Material.POTION)){
			ItemStack item = inv.getItem(index);
			Bukkit.broadcastMessage("dura1: " + item.getDurability());
			if (item.getDurability() == dura){
			    finalamount++;
			}
		    }
		    
		     if (is != null && is.getType().equals(m) && is.getDurability() == dura){
			 finalamount = finalamount + 1;
		     }
		     index++;
		}
	   return finalamount;
	}
		
	for (ItemStack is : inv.getContents()) {
	     if (is != null && is.getType().equals(m) && is.getDurability() == dura){
		 finalamount = finalamount + is.getAmount();
	     }
	}
	return finalamount;
    }*/
    
    public static void removeItems(Inventory inv, ItemStack item){
	    inv.removeItem(item);
    }
    
    // ADD INVENTORY ITEMS //
    public static void addInvItems(Inventory inv, ItemStack item) {
        addInvItems(inv, item.getType(), item.getAmount(), item.getData(), item.getDurability());
    }
     
    public static void addInvItems(Inventory inv, Material type, int amount, MaterialData data, short dura) {
    	
    	if (!(MaterialUtil.isMaterialStackable(type))){
    		int emptySlots = getEmptySlots(inv);
    		if (emptySlots < amount) return;
    		for (ItemStack is : inv.getContents()) {
            	if (amount <= 0) break;
                if (is == null){
                	inv.addItem(new ItemStack(type, 1, dura));
                    amount--;
                }

            }
    		return;
    	}

        for (ItemStack is : inv.getContents()) {
        	if (amount <= 0) break;
            if (is == null){
            	if (amount > 64){
            		inv.addItem(new ItemStack(type, 64, (short) dura));
                	amount = amount - 64;
            	} else {
            		inv.addItem(new ItemStack(type, amount, (short) dura));
            		break;
            	}	
            }
            if (is != null && is.getType() == type && is.getData().equals(data) && dura == is.getDurability()) {
                if (is.getAmount() != 64) {
                	if ((is.getAmount() + amount) > 64){
                		amount = (amount + is.getAmount()) - 64;
                		is.setAmount(64);
                	} else {
                		int newamount = amount + is.getAmount();
                		is.setAmount(newamount);
                		break;
                	}
                }
            }
        }
    }
    

    public static boolean hasChestEnoughItems(Chest c, ItemStack is){
	Inventory i = c.getInventory();
	int amount = getItemAmount(i, is);
	if (amount >= is.getAmount()){
	    return true;
	}
	return false;
    }

    public static int getEmptySlots(Inventory inv){
	int amount = 0;
	for (ItemStack is : inv.getContents()){
	     if (is == null){
		 amount++;
	     }
	}
	return amount;
    }
	
    public static boolean hasItemsEnoughSpace(Inventory inv, Material m, short dura, int amount){
	if (MaterialUtil.isMaterialStackable(m)){
	    int emptySlots = getEmptySlots(inv);
	    int finalamount = emptySlots * 64;
            for (ItemStack is : inv.getContents()){
		 if (is != null && is.getType().equals(m) && is.getDurability() == dura){
		     if (is.getAmount() != 64){
		         finalamount = finalamount + (64 - is.getAmount());
		     }
		 }
            }
	    if (finalamount >= amount){
		return true;
	    }
            return false;
        }  
		
        int emptySlots = getEmptySlots(inv);
	if (emptySlots >= amount){
	    return true;
	}
	return false;
    }

}
