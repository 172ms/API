package me.api.runnable;

import org.bukkit.scheduler.*;
import org.bukkit.*;

public class TablistTag extends BukkitRunnable {

	@Override
	public void run() {
		if (!Bukkit.getOnlinePlayers().isEmpty()) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				if (player.getName().equals("OwO_172ms_OwO")) {
					player.setPlayerListName("§c天主§f");
					player.setDisplayName("§c天主§f");
				}
			});
		}
	}
}