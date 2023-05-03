package me.api.runnable;

import me.api.configuration.*;
import org.bukkit.scheduler.*;
import me.api.utils.*;
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
				else {
					player.setPlayerListHeaderFooter(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("tablistTag-header"))
					.replace("%online%", Integer.toString(Bukkit.getOnlinePlayers().size()))
					.replace("%onlineMAX%", Integer.toString(Bukkit.getMaxPlayers())),
					ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("tablistTag-footer")));
					SortDonation.sort(player);
				}
			});
		}
	}
}