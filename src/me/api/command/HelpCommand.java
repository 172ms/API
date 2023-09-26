package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.utils.player.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import api.*;

public class HelpCommand extends SexyCommand {
	
	public HelpCommand() {
		super("help",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			String[] messages = {
				"Основные команды:",
				"&a/donate - &fоткрыть меню привилегий.",
				"&a/online - &fузнать текущий онлайн на сервере.",
				"&a/ping - &fпроверить пинг.",
				"&a/friends - &fпомощник друзей."
			};
			
			for (String message : messages) {
				if (message != null) {
					SexyMessage.send(sender, message);
				}
			}
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!PlayerSet.getPlayerSet().get(player)) {
			PlayerSet.getPlayerSet().add(player);
			
			String[] messages = {
				"Основные команды:",
				"&a/donate - &fоткрыть меню привилегий.",
				"&a/online - &fузнать текущий онлайн на сервере.",
				"&a/ping - &fпроверить пинг.",
				"&a/friends - &fпомощник друзей."
			};
			
			SexyMessage.send(API.getInstance(), player, messages);
		}
		return true;
	}
}