package ru.fakeduck_king.messages;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class SexyStaff {
	public static void send(Player player, String message) {
		player.sendMessage(Prefix.STAFF + ChatColor.translateAlternateColorCodes('&', message));
	}
}