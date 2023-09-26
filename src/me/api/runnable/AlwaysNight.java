package me.api.runnable;

import me.api.configuration.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

public class AlwaysNight extends BukkitRunnable {
	
	@Override
	public void run() {
		if (!ConfigManager.getConfigManager().getConfig().getBoolean("alwaysNight")) {
			if (!Bukkit.getOnlinePlayers().isEmpty()) {
				Bukkit.getWorlds().forEach(world -> {
					world.setTime(3000L);
				});
			}
		}
	}
}