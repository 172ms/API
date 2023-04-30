package ru.fakeduck_king.messages;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class SexyDonators {
	public static void send(Player player, String message) {
		player.sendMessage(Prefix.DONATORS + ChatColor.translateAlternateColorCodes('&', message));
	}
}