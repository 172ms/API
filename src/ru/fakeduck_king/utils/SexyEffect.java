package ru.fakeduck_king.utils;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Effect;

@SuppressWarnings("deprecation")
public class SexyEffect {
	public static void add(Player player, Location location, Effect effect, int data) {
		player.playEffect(location, effect, data);
	}
}