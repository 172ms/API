package ru.fakeduck_king.register.listeners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;

public class SexyEvent implements Listener {
	public SexyEvent(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}