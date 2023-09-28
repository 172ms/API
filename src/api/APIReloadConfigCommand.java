package api;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import me.api.configuration.*;
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
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("*")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		ConfigManager.getConfigManager().reload();
		return true;
	}
}