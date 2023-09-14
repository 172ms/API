package me.api.listener;

import ru.fakeduck_king.register.listeners.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import ru.fakeduck_king.utils.*;
import me.api.configuration.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class Handlers extends SexyEvent {
	
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
		
		String chatGlobalSyntax = ConfigManager.getConfigManager().getConfig().getString("chat-globalSyntax")
		.replace("%player%", player.getDisplayName())
		.replace("%message%", SexyColorize.colorize(player, message.substring(1).trim()));
		
		String chatLocalSyntax = ConfigManager.getConfigManager().getConfig().getString("chat-localSyntax")
		.replace("%player%", player.getDisplayName())
		.replace("%message%", SexyColorize.colorize(player, message));
		
		String globalMessage = ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("chat-globalMessage"));
		String localMessage = ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("chat-localMessage"));
		
//		if (notify.size() < 2) {
//			SexyMessage.send(player, "&cВас никто не слышит.");
//			return;
//		}
		
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
				PlayerCooldown.getPlayerCooldown().set(player, ConfigManager.getConfigManager().getConfig().getInt("chat-cooldown"));
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