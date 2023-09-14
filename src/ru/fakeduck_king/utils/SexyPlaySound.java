package ru.fakeduck_king.utils;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Sound;

public class SexyPlaySound {
	public static void play(Player player, Location location, Sound sound, long volume, long pitch) {
		player.playSound(location, sound, volume, pitch);
	}
}