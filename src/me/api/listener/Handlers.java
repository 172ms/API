package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import me.api.configuration.settings.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;
import api.*;

@SuppressWarnings("deprecation")
public class Handlers extends SexyEvent {
	private final Set<String> resourcePack = new HashSet<>();
	
	public Handlers(Plugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled()) {
			return;
		}
		
		event.setCancelled(true);
		Player player = event.getPlayer();
		String message;
		
		if (player.hasPermission("fluxmber.color")) {
			message = event.getMessage()
			.replaceAll("&([0-9a-fk-orA-FK-OR])\\s*", "§$1")
			.replaceAll("\\s+", " ").trim();
		}
		else {
			message = event.getMessage();
		}
		
		Set<Player> notify = SexyAllPlayers.get();
		
		String chatGlobalSyntax = ConfigSettings.getConfigSettings().getGlobalChatSyntax()
		.replace("%player%", player.getDisplayName())
		.replace("%message%", SexyColorize.colorize(player, message.substring(1).trim()));
		
		String chatLocalSyntax = ConfigSettings.getConfigSettings().getLocalChatSyntax()
		.replace("%player%", player.getDisplayName())
		.replace("%message%", SexyColorize.colorize(player, message));
		
		String globalMessage = ChatColor.translateAlternateColorCodes('&', ConfigSettings.getConfigSettings().getGlobalChatMessage());
		String localMessage = ChatColor.translateAlternateColorCodes('&', ConfigSettings.getConfigSettings().getLocalChatMessage());
		
		if (message.startsWith("!") && message.substring(1).trim().isEmpty()) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return;
		}
		
		if (player.hasPermission("fluxmber.color")) {
			if (message.replaceAll("[&§][0-9a-zA-Z.,/?!@#$%^&*()-=_+\\[\\]{}|;':\"<>\"]", "").trim().isEmpty() 
			|| message.replaceAll("[&§]", "").trim().isEmpty()
			|| message.replaceAll("![&§][0-9a-zA-Z.,/?!@#$%^&*()-=_+\\[\\]{}|;':\"<>\"]", "").trim().isEmpty()
			|| message.replaceAll("![&§]", "").trim().isEmpty()) {
				SexyMessage.send(player, "&cВведите сообщение!");
				return;
			}
		}
		
		if (!(player.hasPermission("fluxmber.chatbypass"))) {
			if (PlayerCooldown.getPlayerCooldown().has(player)) {
				PlayerCooldown.getPlayerCooldown().set(player, ConfigSettings.getConfigSettings().getChatCooldown());
			}
			else {
				SexyMessage.send(player, "Подождите &c" + PlayerCooldown.getPlayerCooldown().get(player) + "с &fперед отправкой сообщение.");
				return;
			}
		}
		
		notify.forEach(onlinePlayers -> {
			if (message.startsWith("!")) {
				onlinePlayers.sendMessage(globalMessage + chatGlobalSyntax);
			}
			else if (player.getLocation().distance(onlinePlayers.getLocation()) <= 100) {
				onlinePlayers.sendMessage(localMessage + chatLocalSyntax);
			}
		});
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
		
		if (ConfigSettings.getConfigSettings().isKeepInventory()) {
			event.setKeepInventory(true);
            event.getDrops().clear();
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		
		Player player = event.getPlayer();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (playerAPI.getLastJoin() == null) {
			playerAPI.setLastJoin(new Date());
		}
		
		if (playerAPI.getFirstJoin() == null) {
			playerAPI.setFirstJoin(new Date());
		}
		
		DatabaseManager.getDatabaseManager().save(playerAPI);
		
		if (ConfigSettings.getConfigSettings().isLobby()) {
			player.getInventory().clear();
			
			player.getInventory().setItem(0, CustomItem.create(Material.COMPASS, "&aВыбор сервера"));
			
			player.getInventory().setItem(1, CustomItem.create(Material.CLOCK, "&aСкрыть игроков"));
			
			player.getInventory().setItem(8, CustomItem.create(Material.NETHER_STAR, "&aНастройки"));
		}
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
	
//	@EventHandler
//	public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
//		Player player = event.getPlayer();
//		
//		if (event.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
//			this.resourcePack.remove(player.getName());
//		}
//		
//		if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED || event.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {
//			this.resourcePack.remove(player.getName());
//			
//			Bukkit.getScheduler().runTaskLater(API.getInstance(), () -> player.kickPlayer("§cТекстуры сервера обязательно"), 20L);
//		}
//	}
}