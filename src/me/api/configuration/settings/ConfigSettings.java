package me.api.configuration.settings;

import ru.fakeduck_king.messages.*;
import me.api.configuration.*;
import org.bukkit.*;

public class ConfigSettings {
	private static final ConfigSettings configSettings = new ConfigSettings();
	private boolean alwaysDay, changeInventory, donatorsEnabled, dropItem, fallDamage, oldPvP, changeWeather;
	private String globalSyntax, localSyntax, globalMessage, localMessage, tablistTagHeader, tablistTagFooter;
	private int chatCooldown;
	
	public static ConfigSettings getConfigSettings() {
		return ConfigSettings.configSettings;
	}
	
	public void setup() {
		try {
			this.alwaysDay = ConfigManager.getConfigManager().getConfig().getBoolean("alwaysDay");
			this.changeInventory = ConfigManager.getConfigManager().getConfig().getBoolean("changeInventory");
			
			this.globalSyntax = ConfigManager.getConfigManager().getConfig().getString("chat-globalSyntax");
			this.localSyntax = ConfigManager.getConfigManager().getConfig().getString("chat-localSyntax");
			this.globalMessage = ConfigManager.getConfigManager().getConfig().getString("chat-globalMessage");
			this.localMessage = ConfigManager.getConfigManager().getConfig().getString("chat-localMessage");
			this.chatCooldown = ConfigManager.getConfigManager().getConfig().getInt("chat-cooldown");
			
			this.donatorsEnabled = ConfigManager.getConfigManager().getConfig().getBoolean("donatorsEnabled");
			this.dropItem = ConfigManager.getConfigManager().getConfig().getBoolean("dropItem");
			this.fallDamage = ConfigManager.getConfigManager().getConfig().getBoolean("fallDamage");
			this.oldPvP = ConfigManager.getConfigManager().getConfig().getBoolean("oldPvP");
			this.changeWeather = ConfigManager.getConfigManager().getConfig().getBoolean("changeWeather");
			
			this.tablistTagHeader = ConfigManager.getConfigManager().getConfig().getString("tablistTag-header");
			this.tablistTagFooter = ConfigManager.getConfigManager().getConfig().getString("tablistTag-footer");
			
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "ALL CONFIG SETTINGS ARE LOADED");
		}
		catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "CONFIG SETTINGS COULD NOT BE LOAD");
		}
	}
	
	public boolean isAlwaysDay() {
		return this.alwaysDay;
	}
	
	public boolean isChangeInventory() {
		return this.changeInventory;
	}
	
	public String getGlobalChatSyntax() {
		return this.globalSyntax;
	}
	
	public String getLocalChatSyntax() {
		return this.localSyntax;
	}
	
	public String getGlobalChatMessage() {
		return this.globalMessage;
	}
	
	public String getLocalChatMessage() {
		return this.localMessage;
	}
	
	public int getChatCooldown() {
		return this.chatCooldown;
	}
	
	public boolean isDonators() {
		return this.donatorsEnabled;
	}
	
	public boolean isDropItem() {
		return this.dropItem;
	}
	
	public boolean isFallDamage() {
		return this.fallDamage;
	}
	
	public boolean isOldPvP() {
		return this.oldPvP;
	}
	
	public boolean isChangeWeather() {
		return this.changeWeather;
	}
	
	public String getTablistTagHeader() {
		return this.tablistTagHeader;
	}

	public String getTablistTagFooter() {
		return this.tablistTagFooter;
	}
}