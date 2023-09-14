package ru.fakeduck_king.messages;

import ru.fakeduck_king.utils.player.PlayerSet;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;
import java.util.Arrays;
import java.util.List;

public class SexyMessage {
	public static void send(Plugin plugin, Player player, String[] messages) {
		List<String> list = Arrays.asList(messages);
		
		new BukkitRunnable() {
			int i = 0;
			
			@Override
			public void run() {
				if (i >= list.size()) {
					this.cancel();
					if (PlayerSet.getPlayerSet().has(player)) {
						PlayerSet.getPlayerSet().remove(player);
					}
					return;
				}
				SexyMessage.send(player, list.get(i));
				i++;
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	public static void send(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void send(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}