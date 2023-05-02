package me.api.runnable;

import ru.fakeduck_king.utils.permissions.*;
import me.api.configuration.*;
import org.bukkit.scheduler.*;
import org.bukkit.*;

public class TablistTag extends BukkitRunnable {
	
	@Override
	public void run() {
		if (!Bukkit.getOnlinePlayers().isEmpty()) {
			/*Bukkit.getOnlinePlayers().forEach(player -> {
				/*if (player.getName().equals("OwO_172ms_OwO")) {
					player.setPlayerListName("§c天主§f");
					player.setDisplayName("§c天主§f");
				}
				else {
					String syntax = ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfigManager().getConfig().getString("tablistTag-syntax").replace("%prefix%", SexyPlayer.get(player)).replace("%player%", player.getName()));
					player.setPlayerListName(syntax);
				}
			});
			}*/
		}
	}
}