package me.api.configuration;

import org.bukkit.configuration.file.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.*;
import java.io.*;
import api.*;

public class ConfigManager {
	private static final ConfigManager configManager = new ConfigManager();
	private final API plugin = API.getInstance();
	private FileConfiguration config;
	private File configFile;
	
	public static ConfigManager getConfigManager() {
		return ConfigManager.configManager;
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void setup() {
		this.configFile = new File(plugin.getDataFolder(), "config.yml");
		
		if (!this.configFile.exists()) {
			this.configFile.getParentFile().mkdirs();
			plugin.saveResource("config.yml", false);
		}
		
		this.config = new YamlConfiguration();
		
		try {
			this.config.load(this.configFile);
			
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "ALL CONFIGS ARE LOADED");
		}
		catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "ALL CONFIGS COULD NOT BE LOAD");
		}
	}
	
	public void save() {
		try {
			this.config.save(this.configFile);
		}
		catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "ALL CONFIGS COULD NOT BE SAVED");
		}
	}
}