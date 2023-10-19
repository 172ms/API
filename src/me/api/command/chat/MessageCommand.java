package me.api.command.chat;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import api.*;

public class MessageCommand extends SexyCommand {
	
	public MessageCommand() {
		super("message",
		"§cИспользуйте правильно команду: /message §8[§cИгрок§8] §8[§cСообщение§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("m"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (args.length == 0) {
			SexyMessage.send(player, this.usage);
			return true;
		}

		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 1; i < args.length; ++i) {
			stringBuilder.append(args[i]).append(" ");
		}
		
		String message = stringBuilder.toString().trim()
		.replaceAll("\\s+", " ").trim();
		
		if (message.isEmpty()) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return true;
		}
		
		SexyMessage.send(player, "§8[§fЯ §a-> §f" + args[0] + "§8]§f: " + message);
		SexyMessage.send(API.getInstance(), player, args[0], "§8[§f" + player.getName() + " §a-> §fЯ§8]§f: " + message);
		return true;
	}
}