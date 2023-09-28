package me.api.runnable;

import me.api.configuration.settings.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

public class AlwaysDay extends BukkitRunnable {
	
	@Override
	public void run() {
		if (ConfigSettings.getConfigSettings().isAlwaysDay()) {
			if (!Bukkit.getOnlinePlayers().isEmpty()) {
				Bukkit.getOnlinePlayers().forEach(player -> {
					player.getWorld().setTime(6000L);
				});
			}
		}
	}
}