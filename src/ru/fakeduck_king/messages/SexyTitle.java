package ru.fakeduck_king.messages;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

@SuppressWarnings("deprecation")
public class SexyTitle {
	public static void send(Player player, String upMessage) {
		player.sendTitle(ChatColor.translateAlternateColorCodes('&', upMessage), null);
	}
	
	public static void send(Player player, String upMessage, String downMessage) {
		player.sendTitle(ChatColor.translateAlternateColorCodes('&', upMessage), ChatColor.translateAlternateColorCodes('&', downMessage));
	}
}