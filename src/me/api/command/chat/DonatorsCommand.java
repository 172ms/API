package me.api.command.chat;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.utils.player.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import me.api.configuration.ConfigManager;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class DonatorsCommand extends SexyCommand {
	
	public DonatorsCommand() {
		super("donators",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("dc"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!player.hasPermission("fluxmber.donators")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (playerAPI.getMessageDonators()) {
			SexyMessage.send(player, "&cУ вас выключен DONATORS, чтобы включить: /donatorstoggle");
			return true;
		}
		
		if (args.length == 0) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return true;
		}
		
		String message = String.join(" ", args).trim()
		.replaceAll("&([0-9a-fk-orA-FK-OR])\\s*", "§$1")
		.replaceAll("\\s+", " ");
		
		if (message.replaceAll("[&§][0-9a-zA-Z.,/?!@#$%^&*()-=_+\\[\\]{}|;':\"<>\"]", "").trim().isEmpty()
		|| message.replaceAll("[&§]", "").trim().isEmpty()) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return true;
		}
		
		if (!(player.hasPermission("fluxmber.chatbypass"))) {
			if (PlayerCooldown.getPlayerCooldown().has(player)) {
				PlayerCooldown.getPlayerCooldown().set(player, ConfigManager.getConfigManager().getConfig().getInt("chat-cooldown"));
			}
			else {
				SexyMessage.send(player, "Подождите &c" + PlayerCooldown.getPlayerCooldown().get(player) + "с &fперед отправкой сообщение.");
				return true;
			}
		}
		
		Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
			PlayerAPI playerAPIEach = PlayerAPI.getPlayerAPI(onlinePlayers);
			
			if (playerAPIEach.getMessageDonators()) {
				return;
			}
			if (onlinePlayers.hasPermission("fluxmber.donators")) {
				SexyDonators.send(onlinePlayers, player.getDisplayName() + ": " + message);
			}
		});
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = Lists.newArrayList();
		
		if (args.length == 1) {
			if (sender.hasPermission("fluxmtber.donators")) {
				list = Collections.singletonList("сообщение");
			}
		}
		return list;
	}
}