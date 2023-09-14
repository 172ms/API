package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class SaveallCommand extends SexyCommand {
	
	public SaveallCommand() {
		super("save-all",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("save"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SexyMessage.send(sender, "§aИгра сохранера.");
			Bukkit.getServer().savePlayers();
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		SexyMessage.send(player, "§aИгра сохранера.");
		Bukkit.getServer().savePlayers();
		return true;
	}
}