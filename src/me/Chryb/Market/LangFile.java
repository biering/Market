package me.Chryb.Market;

public class LangFile {
	
	public static void load(){
		addLangDefault("Language", "EN");
		addLangDefault("CREATIVE_MODE_PROT", "You cannot break the ItemFrames in the Creative Mode.");
		addLangDefault("NO_SHOP_PERM", "You don't have any permission about that shop.");
		addLangDefault("SHOP_HELP_CREATE", "Create a selected shop");
		addLangDefault("SHOP_HELP_DELETE", "Delete a selected shop");
		addLangDefault("SHOP_HELP_PURCHASE", "Purchase price");
		addLangDefault("SHOP_HELP_RETAIL", "Retail price");
		addLangDefault("SHOP_HELP_TYPE", "Shop Type");
		addLangDefault("NO_PERMISSION", "You don't have the permission to use this command.");
		addLangDefault("NO_PURCHASES_RETAILS", "You don't have any purchases or retails.");
		addLangDefault("TYPE_AN_AMOUNT", "You have to type an amount.");
		addLangDefault("TYPE_A_PRICE", "You have to type a price.");
		addLangDefault("YOU_CANNOT_TYPE_AMOUNT_ALL", "You cannot type '/amount all' in a admin shop.");
		addLangDefault("CMD_USAGE", "Usage: ");
		addLangDefault("SHOP_SET_ADMIN", "Shop Type set to Admin.");
		addLangDefault("SHOP_SET_NORMAL", "Shop Type set to Normal.");
		addLangDefault("SHOP_SET_PURCHASE", "Purchase Price set to: %amount");
		addLangDefault("SHOP_SET_RETAIL", "Retail Price set to: %amount");
		addLangDefault("SHOP_SET_OWNER", "Owner set to: %player");
		addLangDefault("SHOP_DELETE", "Shop successfully deleted.");
		addLangDefault("SHOP_CREATE", "Shop successfully created.");
		addLangDefault("SHOP_ALREADY_CREATED", "¤7Shop is already created. Delete him with ¤6/shop delete¤7.");
		addLangDefault("NO_ITEMFRAME_SELECTED", "No ItemFrame selected.");
		addLangDefault("ITEMFRAME_ALREADY_SELECTED", "You already selected these ItemFrame!");
		addLangDefault("ITEMFRAME_SELECTED", "You select that ItemFrame.");
		addLangDefault("TYPE_ADMIN_NORMAL", "You have to type [Admin:Normal].");
		addLangDefault("PURCHASE_LOCKED", "In these shop you can't purchase something.");
		addLangDefault("RETAIL_LOCKED", "In these shop you can't retail something.");
		addLangDefault("NO_SHOP_ACCESS", "You don't have access to that Shop.");
		addLangDefault("NO_VALID_SHOP", "No valid normal Shop.");
		addLangDefault("NO_VALID_ITEM", "The item is not Valid.");
		addLangDefault("SHOP_HAS_NO_CHEST", "These normal Shop hasn't any chest.");
		addLangDefault("SHOP_HAS_NOT_ENOUGH_STOCK", "Chest is out of Stock.");
		addLangDefault("SHOP_HAS_NOT_ENOUGH_SPACE", "The Shop hasn't enough space for your items.");
		addLangDefault("CLIENT_HAS_NOT_ENOUGH_MONEY", "You don't have enough money.");
		addLangDefault("CLIENT_HAS_NOT_ENOUGH_SPACE", "You don't has enough space for the items.");
		addLangDefault("CLIENT_HAS_NOT_ENOUGH_ITEMS", "You don't have enough items in your inventory.");
		addLangDefault("OWNER_HAS_NOT_ENOUGH_MONEY", "The Shop Owner hasn't enough money.");
		addLangDefault("CMD_NOT_RECOGNIZED", "¤7The command was not recognized.");
		addLangDefault("SEE_SHOP_HELP", "¤7See ¤6/shop help¤7 for a list of commands.");
		addLangDefault("CLIENT_PURCHASE", "%player bought %amount %item for %cost from you.");
		addLangDefault("CLIENT_RETAIL", "%player sold you for %cost, %amount %item.");
		addLangDefault("ON_PURCHASE", "¤7You buy ¤b%amount ¤6%item ¤7for ¤6%cost¤7.");
		addLangDefault("ON_RETAIL", "¤7You sell ¤b%amount ¤6%item ¤7for ¤6%cost¤7.");
		addLangDefault("PURCHASE_SELECTED", "¤7Purchase the item/s with ¤b/amount [amount] ¤7or ¤b/a [amount]");
		addLangDefault("RETAIL_SELECTED", "¤7Retail the item/s with ¤b/amount [amount] ¤7or ¤b/a [amount]");
		addLangDefault("SHOP_INFO_ADMIN", "¤7In these Admin Shop you can buy/sell ¤6%item¤7 for:");
		addLangDefault("SHOP_INFO_NORMAL", "¤7In these Shop you can buy/sell ¤6%item¤7 for:");
		addLangDefault("SHOP_INFO_NORMAL_AMOUNT", "¤7In these Shop you can buy/sell ¤b%amount ¤6%item¤7 for:");
		addLangDefault("SHOP_INFO_PURCHASE", " - Purchase Price: ");
		addLangDefault("SHOP_INFO_PURCHASE_LOCKED", "No Purchase");
		addLangDefault("SHOP_INFO_RETAIL", " - Retail Price: ");
		addLangDefault("SHOP_INFO_RETAIL_LOCKED", "No Retail");
		addLangDefault("SHOP_INFO_OWNER", " - Owner: ");
		addLangDefault("CHEST_FULL", "Chest is full.");
		Config.getLangFile().options().copyDefaults(true);
		Config.saveLangFile();
	}
	
	public static void check(){
		Config.getLangFile().set("CMD_NOT_RECOGNIZED", Config.getLangFile().getString("CMD_NOT_RECOGNIZED").replace("?", "¤"));
		Config.getLangFile().set("SEE_SHOP_HELP", Config.getLangFile().getString("SEE_SHOP_HELP").replace("?", "¤"));
		Config.getLangFile().set("ON_PURCHASE", Config.getLangFile().getString("ON_PURCHASE").replace("?", "¤"));
		Config.getLangFile().set("ON_RETAIL", Config.getLangFile().getString("ON_RETAIL").replace("?", "¤"));
		Config.getLangFile().set("PURCHASE_SELECTED", Config.getLangFile().getString("PURCHASE_SELECTED").replace("?", "¤"));
		Config.getLangFile().set("RETAIL_SELECTED", Config.getLangFile().getString("RETAIL_SELECTED").replace("?", "¤"));
		Config.getLangFile().set("SHOP_INFO_ADMIN", Config.getLangFile().getString("SHOP_INFO_ADMIN").replace("?", "¤"));
		Config.getLangFile().set("SHOP_INFO_NORMAL", Config.getLangFile().getString("SHOP_INFO_NORMAL").replace("?", "¤"));
		Config.getLangFile().set("SHOP_INFO_NORMAL_AMOUNT", Config.getLangFile().getString("SHOP_INFO_NORMAL_AMOUNT").replace("?", "¤"));
		Config.getLangFile().set("SHOP_ALREADY_CREATED", Config.getLangFile().getString("SHOP_ALREADY_CREATED").replace("?", "¤"));
	}
	
	public static void addLangDefault(String path, String value){
		Config.getLangFile().addDefault(path, value);
	}

}
