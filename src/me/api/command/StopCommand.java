package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class StopCommand extends SexyCommand {
	
	public StopCommand() {
		super("stop",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SexyBroadcast.send("§cСервер завершает работу!");
			Bukkit.getServer().savePlayers();
			Bukkit.getServer().shutdown();
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		SexyBroadcast.send("§cСервер завершает работу!");
		Bukkit.getServer().savePlayers();
		Bukkit.getServer().shutdown();
		return true;
	}
}