package me.Chryb.Market;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import me.Chryb.Market.Database.Database;
import me.Chryb.Market.Database.ShopStore;
import me.Chryb.Market.Money.EmeraldListener;
import me.Chryb.Market.Shop.Commands.ShopCommands;
import me.Chryb.Market.Shop.Listener.LChestAccess;
import me.Chryb.Market.Shop.Listener.LShopInformation;
import me.Chryb.Market.Shop.Listener.LItemFrameBreak;
import me.Chryb.Market.Shop.Listener.LItemFrameSelecter;
import me.Chryb.Market.Shop.Listener.LSelectPurchase;
import me.Chryb.Market.Shop.Listener.LSelectRetail;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Market extends JavaPlugin{
	
	public static HashMap<Player,Sign> selected_itemframe = new HashMap<Player,Sign>();
	public static HashMap<Player,Sign> player_purchase = new HashMap<Player,Sign>();
	public static HashMap<Player,Sign> player_retail = new HashMap<Player,Sign>();
	
	public static Economy econ = null;
	public static Permission perm = null;
	
	public final Config config = new Config(this);
	public final Database database = new Database(this);
	public final Language language = new Language(this);
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("[Market] Plugin disabled.");
	}

	@Override
	public void onEnable() {
        
		PluginDescriptionFile descFile = getDescription();
		LangFile.load();
		Config.load();
		
		Bukkit.getConsoleSender().sendMessage("----- Enable [Market] -----");
		Bukkit.getConsoleSender().sendMessage("Plugin initialized" + " - " + ChatColor.AQUA + descFile.getMain());
		Bukkit.getConsoleSender().sendMessage("Plugin by " + descFile.getAuthors() + " | MotD Plugin Language: " + ChatColor.WHITE + Language.get());
		loadVault();
		Bukkit.getConsoleSender().sendMessage("Hooked into Vault and Economy.");
		Database.setup();
		Bukkit.getConsoleSender().sendMessage("Database enabled.");
		Bukkit.getConsoleSender().sendMessage("----- --------------- -----");

		registerCommands();
		initializeEvents();
		startMetrics();
		setupPermissions();
		
		LangFile.check();
	}
	
	private void startMetrics() {
        try {
            new Metrics(this).start();
        } catch (IOException ex) {
            this.getLogger().severe("There was an error while submitting statistics.");
        }
    }
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
		    return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		    if (rsp == null) {
		        return false;
		    }
		    econ = rsp.getProvider();
		    return econ != null;
		}
	
	 private boolean setupPermissions()
	    {
	        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	        if (permissionProvider != null) {
	            perm = permissionProvider.getProvider();
	        }
	        return (perm != null);
	    }
	
	private void loadVault() {
		if (!setupEconomy() ) {
		  Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "No economy plugin found");
		  getServer().getPluginManager().disablePlugin(this);
		  return;
		}
	}
    
    public void rc(String command, CommandExecutor ce) {
		Bukkit.getServer().getPluginCommand(command).setExecutor(ce);
	}
    
    public void registerCommands(){
		rc("market", new MarketCommands(this));
		rc("m", new MarketCommands(this));
		rc("shop", new ShopCommands(this));
		rc("amount", new ShopCommands(this));
		rc("a", new ShopCommands(this));
    }
    
    private void initializeEvents() {
		registerEvent(new EmeraldListener(this));
		
		registerEvent(new LShopInformation(this));
		registerEvent(new LItemFrameSelecter(this));
		registerEvent(new LSelectPurchase(this));
		registerEvent(new LSelectRetail(this));
		registerEvent(new LChestAccess(this));
		registerEvent(new LItemFrameBreak(this));
    }  
    
    public void registerEvent(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
    
    public void reloadPlugin() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.disablePlugin(Market.this);
        pluginManager.enablePlugin(Market.this);
    }
    
    public void installDDLFile(){
	installDDL();
    }
	
    @Override
    public List<Class<?>> getDatabaseClasses() {
	List<Class<?>> classes = new LinkedList<Class<?>>();
        classes.add(ShopStore.class);
        return classes;
    }
	

}
