package ru.fakeduck_king.messages;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class SexyBroadcast {
	public static void send(String message) {
		Bukkit.broadcastMessage(Prefix.BROADCAST + ChatColor.translateAlternateColorCodes('&', message));
	}
}