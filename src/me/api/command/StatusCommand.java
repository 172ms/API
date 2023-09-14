package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import api.*;

public class StatusCommand extends SexyCommand {
	
	public StatusCommand() {
		super("status",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			String[] messages = {
				"TPC: &a" + SexyTPS.get(),
				"Threads: &a" + SexyThreads.get(),
				"Total Memory: &a" + SexyRam.getTotal(),
				"Used Memory: &a" + SexyRam.getUsed(),
				"Free Memory: &a" + SexyRam.getFree(),
				"Max Memory: &a" + SexyRam.getMax()
			};
			
			for (String message : messages) {
				if (message != null) {
					SexyMessage.send(sender, message);
				}
			}
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (!PlayerSet.getPlayerSet().has(player)) {
			PlayerSet.getPlayerSet().add(player);
			
			String[] messages = {
				"TPC: &a" + SexyTPS.get(),
				"Threads: &a" + SexyThreads.get(),
				"Total Memory: &a" + SexyRam.getTotal(),
				"Used Memory: &a" + SexyRam.getUsed(),
				"Free Memory: &a" + SexyRam.getFree(),
				"Max Memory: &a" + SexyRam.getMax()
			};
			
			SexyMessage.send(API.getInstance(), player, messages);
		}
		return true;
	}
}