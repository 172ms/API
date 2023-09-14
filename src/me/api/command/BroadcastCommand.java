package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class BroadcastCommand extends SexyCommand {
	
	public BroadcastCommand() {
		super("broadcast",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("bc"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			String message = String.join(" ", args).trim()
			.replaceAll("&([0-9a-fk-orA-FK-OR])\\s*", "§$1")
			.replaceAll("\\s+", " ");
			
			if (message.replaceAll("[&§][0-9a-zA-Z.,/?!@#$%^&*()-=_+\\[\\]{}|;':\"<>\"]", "").trim().isEmpty()
			|| message.replaceAll("[&§]", "").trim().isEmpty()) {
				SexyMessage.send(sender, "&cВведите сообщение!");
				return true;
			}
			
			SexyBroadcast.send(message);
			return true;
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.broadcast")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		String message = String.join(" ", args).trim()
		.replaceAll("&([0-9a-fk-orA-FK-OR])\\s*", "§$1")
		.replaceAll("\\s+", " ");
		
		if (message.replaceAll("[&§][0-9a-zA-Z.,/?!@#$%^&*()-=_+\\[\\]{}|;':\"<>\"]", "").trim().isEmpty()
		|| message.replaceAll("[&§]", "").trim().isEmpty()) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return true;
		}
		
		SexyBroadcast.send(message);
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> list = Lists.newArrayList();
		
		if (args.length == 1) {
			if (sender.hasPermission("fluxmber.broadcast")) {
				list = Collections.singletonList("сообщение");
			}
		}
		return list;
	}
}