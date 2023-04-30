package ru.fakeduck_king.utils;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.HashSet;
import java.util.Set;

public class SexyAllPlayers {
	public static Set<Player> get() {
		return new HashSet<>(Bukkit.getOnlinePlayers());
	}
}
