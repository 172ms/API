package ru.fakeduck_king.utils.permissions;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class SexyPlayer {
	public static String get(Player player) {
		return ChatColor.translateAlternateColorCodes('&', PermissionsEx.getPermissionManager().getUser(player).getPrefix());
	}
}