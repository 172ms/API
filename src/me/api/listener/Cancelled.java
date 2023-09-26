package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import me.api.configuration.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class Cancelled extends SexyEvent {
	
	public Cancelled(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!ConfigManager.getConfigManager().getConfig().getBoolean("fallDamage")) {
			if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (!ConfigManager.getConfigManager().getConfig().getBoolean("dropItem")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (!ConfigManager.getConfigManager().getConfig().getBoolean("changeInventory")) {
			if (event.getWhoClicked() != null) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (!ConfigManager.getConfigManager().getConfig().getBoolean("changeInventory")) {
			event.setCancelled(true);
		}
	}
	
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
    	if (ConfigManager.getConfigManager().getConfig().getBoolean("changeWeather")) {
	        event.setCancelled(true);
    	}
    }
}