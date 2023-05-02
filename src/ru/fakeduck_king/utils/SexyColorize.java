package ru.fakeduck_king.utils;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class SexyColorize {
	public static String colorize(Player player, String message) {
		if (player.hasPermission("chat.color")) {
			return ChatColor.translateAlternateColorCodes('&', message);
		}
		return message;
	}
}