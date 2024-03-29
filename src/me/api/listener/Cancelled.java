package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import me.api.configuration.settings.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;

public class Cancelled extends SexyEvent {
	
	public Cancelled(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (ConfigSettings.getConfigSettings().isLobby()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (ConfigSettings.getConfigSettings().isLobby()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (ConfigSettings.getConfigSettings().isLobby()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!ConfigSettings.getConfigSettings().isFallDamage()) {
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
		
		if (!ConfigSettings.getConfigSettings().isDropItem()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		
		if (player.hasPermission("fluxmber.admin")) {
			return;
		}
		
		if (!ConfigSettings.getConfigSettings().isChangeInventory()) {
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
		
		if (!ConfigSettings.getConfigSettings().isChangeInventory()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (!ConfigSettings.getConfigSettings().isChangeWeather()) {
			event.setCancelled(true);
		}
	}
}