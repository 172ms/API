package api;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import me.api.configuration.*;
import me.api.configuration.settings.ConfigSettings;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class APIReloadConfigCommand extends SexyCommand {
	
	public APIReloadConfigCommand() {
		super("apireloadconfig",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("arc"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			ConfigManager.getConfigManager().reload();
			
			if (ConfigSettings.getConfigSettings().isOldPvP()) {
				Bukkit.getOnlinePlayers().forEach(player -> player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D));
			}
			else {
				Bukkit.getOnlinePlayers().forEach(player -> player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D));
			}
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("*")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		ConfigManager.getConfigManager().reload();
		
		if (ConfigSettings.getConfigSettings().isOldPvP()) {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100.D);
		}
		else {
			player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4.D);
		}
		return true;
	}
}