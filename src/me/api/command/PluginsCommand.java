package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import java.util.*;

public class PluginsCommand extends SexyCommand {
	
	public PluginsCommand() {
		super("plugins",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("pl"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SexyMessage.send(sender, "Плагины: " + this.getPluginList());
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("*")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		SexyMessage.send(player, "Плагины: " + this.getPluginList());
		return true;
	}
	
	private String getPluginList() {
		StringBuilder stringBuilder = new StringBuilder();
		Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
		
		for (Plugin plugin : plugins) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append("§f");
				stringBuilder.append(", ");
			}
			
			stringBuilder.append(plugin.isEnabled() ? "§a" : "§c");
			stringBuilder.append(plugin.getDescription().getName());
		}
		return "(" + plugins.length + "): " + stringBuilder.toString();
	}
}