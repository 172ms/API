package me.api.runnable;

import me.api.configuration.settings.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

public class AlwaysNight extends BukkitRunnable {
	
	@Override
	public void run() {
		if (ConfigSettings.getConfigSettings().isAlwaysNight()) {
			if (!Bukkit.getOnlinePlayers().isEmpty()) {
				Bukkit.getOnlinePlayers().forEach(player -> {
					player.getWorld().setTime(18000L);
				});
			}
		}
	}
}