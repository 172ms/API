package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;

public class ClearChatCommand extends SexyCommand {
	
	public ClearChatCommand() {
		super("clearchat",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			IntStream.range(0, 100).forEach(i -> {
				Bukkit.broadcastMessage("");
			});
			
			SexyBroadcast.send("Чат был очищен &a" + sender.getName() + ".");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		IntStream.range(0, 100).forEach(i -> {
			Bukkit.broadcastMessage("");
		});
		
		SexyBroadcast.send("Чат был очищен &a" + player.getName() + ".");
		return true;
	}
}