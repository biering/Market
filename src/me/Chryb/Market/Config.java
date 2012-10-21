package me.Chryb.Market;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
	
	public static Market plugin;
	
	private static FileConfiguration lang = null;
	private static File langFile = null;
	
	public Config(Market instance){
		 plugin = instance;
	}
	
	public static void load(){
		plugin.getConfig().addDefault("Update.OutputType.Console", true);
		plugin.getConfig().addDefault("Update.OutputType.Admin", false);
		plugin.getConfig().addDefault("Update.AutoInstall", false);
		plugin.getConfig().addDefault("Update.Information", true);
		plugin.getConfig().addDefault("Update.ArtifactInformation", true);
		
		plugin.getConfig().addDefault("Shop.Rent.Enabled", false);
		plugin.getConfig().addDefault("Shop.Rent.Value", 0);
		plugin.getConfig().addDefault("Shop.Rent.Interval", 3); // in days //
		
		plugin.getConfig().addDefault("EMERALD_CONVERTER.Enabled", false);
		plugin.getConfig().addDefault("Shop.ChestProtection.Enabled", true);
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	 // lang.yml //
	public static void reloadLangFile() {
	    if (langFile == null) {
	    langFile = new File(plugin.getDataFolder(), "lang.yml");
	    }
	    lang = YamlConfiguration.loadConfiguration(langFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("lang.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        lang.setDefaults(defConfig);
	    }
	}
	
	public static FileConfiguration getLangFile() {
	    if (lang == null) {
	        reloadLangFile();
	    }
	    return lang;
	}
	
	public static void saveLangFile() {
	    if (lang == null || langFile == null) {
	    return;
	    }
	    try {
	    	lang.save(langFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + langFile, ex);
	    }
	}
	

}
