package me.Chryb.Market.Util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.Material;

public class MaterialUtil {
    
    public static void main(String[] args){
	System.out.println("MaterialUtil Test$ >");
	
	System.out.println("  .getMaterialName(String):");
	System.out.println("     (5:2) - " + getMaterialName("5:2"));
	System.out.println("     (2) - " + getMaterialName("2"));
	
	ItemStack is = new ItemStack(Material.getMaterial(5));
	is.setAmount(1);
	System.out.println("ItemStack: " + is.getDurability());
	is.setDurability((short) 2);
	System.out.println("ItemStack: " + is.getTypeId());
	System.out.println("ItemStack: " + is.getType());
	System.out.println("ItemStack: " + is.getDurability());
	System.out.println("ItemStack: " + is.getData());
	
	
    }
	
	    /**
	    * Parses a block specified for a material
	    * @param blockName Name of a block
	    * @return Block material if it exists, null if it does not.
	    */
	    public static MaterialData getBlock(String blockName) {
	        try
	        {
	            String[] parts = blockName.split(":");
	            if(parts.length > 2) return null;
	         
	            MaterialData block;
	            if(isNumeric(parts[0])) block = new MaterialData(Material.getMaterial(Integer.parseInt(parts[0])));
	            else {
	                block = new MaterialData(Material.getMaterial(parts[0].toUpperCase()));
	            }
	         
	            parts[0] = block.getItemTypeId() + "";
	         
	            if(parts.length == 2) {
	                block.setData(Byte.parseByte(parts[1]));
	            }
	         
	            return block;
	         
	        }
	        catch(NumberFormatException nfe) { return null; }
	        catch(NullPointerException npe) { return null; }
	        catch(Exception ex) { return null; }
	    }
	    
	    /**
	    * Returns the complete Name of the Item (example: 5:2 -> WOOD Birch, 2 -> GRASS)
	    * @param data - String | complete Item Id/SubId
	    * @return String #ItemName / null
	    */
	    public static String getMaterialName(String data) {
	        try
	        {
	            String[] parts = data.split(":");
	            if(parts.length > 2) return null;
	         
	            String s = "";
	            if(isNumeric(parts[0])) s = Material.getMaterial(Integer.parseInt(parts[0])).name();
	            else {
	                return s;
	            }
	         
	            if(parts.length == 2) {
	                s = s + " " + parseMetadata(parts);
	            }
	         
	            return s;
	         
	        }
	        catch(NumberFormatException nfe) { return null; }
	        catch(NullPointerException npe) { return null; }
	        catch(Exception ex) { return null; }
	    }
	    
	    /**
	    * Returns the Id from a ItemId String (example: 2 -> 2, 5:2 -> 5)
	    * @param data - String | complete Item Id/SubId
	    * @return #int ItemId / 0
	    */
	    public static int getId(String data){
	    	try
	        {
	            String[] parts = data.split(":");
	            if(parts.length > 2) return Integer.parseInt(parts[0]);
	            return Integer.parseInt(parts[0]);
	         
	        }
	        catch(NumberFormatException nfe) { return 0; }
	        catch(NullPointerException npe) { return 0; }
	        catch(Exception ex) { return 0; }
	    }
	    
	    /**
	    * Returns the SubId from a ItemId String (example: 2 -> 0, 5:2 -> 2)
	    * @param data - String | complete Item Id/SubId
	    * @return #int ItemSubId / 0
	    */
	    public static int getSubId(String data){
	    	try
	        {
	            String[] parts = data.split(":");
	            if (parts.length != 2) return 0;
	            return Integer.parseInt(parts[1]);
	         
	        }
	        catch(NumberFormatException nfe) { return 0; }
	        catch(NullPointerException npe) { return 0; }
	        catch(Exception ex) { return 0; }
	    }
	 
	    /**
	    * Returns the metadata of a block as a string (example: 5:2 -> "Wood Birch")
	    * @param parts - String[] | Two ItemId String parts (example: 5:3)
	    * @return #String ItemMetadata / ""
	    */
	    public static String parseMetadata(String[] parts) {
	    	int id = Integer.parseInt(parts[0]);
	    	int subid = Integer.parseInt(parts[1]);

	    	if (id == 5 || id == 6 || id == 17 || id == 18){
	    		if(subid == 0) return "Oak";
                if(subid == 1) return "Pine";
                if(subid == 2) return "Birch";
                if(subid == 3) return "Jungle";
	    	}
	    	
	    	if (id == 24){
	    		if(subid == 1) return "Chiseled";
                if(subid == 2) return "Smooth";
	    	}
	    	
            if (id == 33 || id == 34){
            	if(subid == 0) return "Stone";
            	if(subid == 1) return "Sandstone";
                if(subid == 2) return "Wooden";
                if(subid == 3) return "Cobblestone";
                if(subid == 4) return "Brick";
                if(subid == 5) return "Stone Brick";
                if(subid == 6) return "Smooth";
	    	}
            
            if (id == 35){
            	if(subid == 0) return "White";
            	if(subid == 1) return "Orange";
                if(subid == 2) return "Magenta";
                if(subid == 3) return "Lightblue";
                if(subid == 4) return "Yellow";
                if(subid == 5) return "Lime";
                if(subid == 6) return "Pink";
                if(subid == 7) return "Gray";
                if(subid == 8) return "Lightgray";
                if(subid == 9) return "Cyan";
                if(subid == 10) return "Purple";
                if(subid == 11) return "Blue";
                if(subid == 12) return "Brown";
                if(subid == 13) return "Green";
                if(subid == 14) return "Red";
                if(subid == 15) return "Black";
            }
            
            if (id == 84){
            	if(subid == 1) return "gold disk";
                if(subid == 2) return "green disk";
                if(subid == 3) return "orange disk";
                if(subid == 4) return "red disk";
                if(subid == 5) return "lime disk";
                if(subid == 6) return "purple disk";
                if(subid == 7) return "violet disk";
                if(subid == 8) return "black disk";
                if(subid == 9) return "white disk";
                if(subid == 10) return "sea green disk";
                if(subid == 11) return "broken disk";
            }
            
            if (id == 98){
                if(subid == 1) return "Mossy";
                if(subid == 2) return "Cracked";
                if(subid == 3) return "Chiseled";
            }
	    	
	    	if (id == 144){
            	if(subid == 1) return "Wither Block Head";
            	if(subid == 2) return "Zombie Block Head";
            	if(subid == 3) return "Steve Block Head";
            	if(subid == 4) return "Creeper Block Head";
	    	}
	    	
	    	if (id == 373){
	    		if(subid == 16) return "Awkward";
            	if(subid == 32) return "Think";
            	if(subid == 64) return "Mundane";
            	if(subid == 8193) return "Regeneration 1a";
            	if(subid == 8194) return "Swiftness 1a";
            	if(subid == 8195) return "Fire Resistance 1a";
            	if(subid == 8196) return "Poison 1a";
            	if(subid == 8197) return "Healing 1";
            	if(subid == 8198) return "Night Version 1a";
            	if(subid == 8200) return "Weakness 1a";
            	if(subid == 8201) return "Strength 1a";
            	if(subid == 8202) return "Slowness 1a";
            	if(subid == 8204) return "Harming 1";
            	if(subid == 8206) return "Invisibility 1a";
            	if(subid == 8225) return "Regeneration 2";
            	if(subid == 8226) return "Swiftness 2";
            	if(subid == 8228) return "Poison 2";
            	if(subid == 8229) return "Healing 2";
            	if(subid == 8233) return "Strength 2";
            	if(subid == 8236) return "Harming 2";
            	if(subid == 8257) return "Regeneration 1b";
            	if(subid == 8258) return "Swiftness 1b";
            	if(subid == 8259) return "Fire Resistance 1b";
            	if(subid == 8258) return "Poison 1b";
            	if(subid == 8262) return "Night Version 1b";
            	if(subid == 8264) return "Weakness 1b";
            	if(subid == 8265) return "Strength 1b";
            	if(subid == 8266) return "Slowness 1b";
            	if(subid == 8270) return "Invisibility 1b";
            	
            	if(subid == 16385) return "Regeneration Splash 1a";
            	if(subid == 16386) return "Swiftness Splash 1a";
            	if(subid == 16387) return "Fire Resistance Splash 1a";
            	if(subid == 16388) return "Poison Splash 1a";
            	if(subid == 16389) return "Healing Splash 1";
            	if(subid == 16392) return "Weakness Splash 1a";
            	if(subid == 16393) return "Strength Splash 1a";
            	if(subid == 16394) return "Slowness Splash 1a";
            	if(subid == 16396) return "Harming Splash 1a";
            	if(subid == 16418) return "Swiftness Splash 2";
            	if(subid == 16420) return "Poison Splash 2";
            	if(subid == 16421) return "Healing Splash 2";
            	if(subid == 16425) return "Strength Splash 2";
            	if(subid == 16428) return "Harming Splash 2";
            	if(subid == 16449) return "Regeneration Splash 1b";
            	if(subid == 16450) return "Swiftness Splash 1b";
            	if(subid == 16451) return "Fire Resistance Splash 1b";
            	if(subid == 16452) return "Poison Splash 1b";
            	if(subid == 16456) return "Weakness Splash 1b";
            	if(subid == 16457) return "Strength Splash 1b";
            	if(subid == 16458) return "Slowness Splash 1b";
            	if(subid == 16471) return "Regeneration Splash 2";
            	
            	if(subid == 16390) return "Protection 1";
                if(subid == 16398) return "Protection 2";
                if(subid == 16427) return "Protection 3";
	    	}
	    	
	    	if (id == 383){
	    		if(subid == 50) return "Creeper";
            	if(subid == 51) return "Skeleton";
            	if(subid == 52) return "Spider";
            	if(subid == 54) return "Zombie";
            	if(subid == 55) return "Slime";
            	if(subid == 56) return "Ghast";
            	if(subid == 57) return "Zombie Pigman";
            	if(subid == 58) return "Enderman";
            	if(subid == 59) return "Cave Spider";
            	if(subid == 60) return "Silverfish";
            	if(subid == 61) return "Blaze";
            	if(subid == 62) return "Magma Cube";
            	
            	if(subid == 90) return "Pig";
            	if(subid == 91) return "Sheep";
            	if(subid == 92) return "Cow";
            	if(subid == 93) return "Chicken";
            	if(subid == 94) return "Squid";
            	if(subid == 95) return "Wolf";
            	if(subid == 96) return "Mooshroom";
            	if(subid == 98) return "Ocelot";
            	
            	if(subid == 120) return "Villager";
	    	}
	    	
	    	if (id == 397){
            	if(subid == 1) return "Wither Head";
            	if(subid == 2) return "Zombie Head";
            	if(subid == 3) return "Steve Head";
            	if(subid == 4) return "Creeper Head";
	    	}
	        return "";
	    }
	 
	    /**
	    * Checks if a string is numeric
	    * @param str String String to be checked
	    * @return boolean True if a string is numeric
	    */
	    @SuppressWarnings("unused")
	    public static boolean isNumeric(String str) {
	      try
	      { double d = Double.parseDouble(str); }
	      catch(NumberFormatException nfe)
	      { return false; }
	      return true;
	    }
	    
	    /**
	     * Query if the material is stackable
	     * @param Material
	     * @return true, if the maxStackSize == 64; false if not
	     */
	     public static boolean isMaterialStackable(Material m){
	     	if (m.getMaxStackSize() == 64){
	     		return true;
	     	}
	     	return false;
	     }

}
