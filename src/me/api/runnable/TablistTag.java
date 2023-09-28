package me.api.runnable;

import me.api.configuration.settings.*;
import org.bukkit.scheduler.*;
import me.api.utils.*;
import org.bukkit.*;

public class TablistTag extends BukkitRunnable {
	
	@Override
	public void run() {
		if (!Bukkit.getOnlinePlayers().isEmpty()) {
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.setPlayerListHeaderFooter(ChatColor.translateAlternateColorCodes('&', ConfigSettings.getConfigSettings().getTablistTagHeader())
				.replace("%online%", Integer.toString(Bukkit.getOnlinePlayers().size()))
				.replace("%onlineMAX%", Integer.toString(Bukkit.getMaxPlayers())),
				
				ChatColor.translateAlternateColorCodes('&', ConfigSettings.getConfigSettings().getTablistTagFooter()));
				
				SortDonation.sort(player);
			});
		}
	}
}