package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class TimeCommand extends SexyCommand {
	
	public TimeCommand() {
		super("time",
		"Используйте правильно команду: §c/time §8[§cРежим§8]",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			switch (args.length) {
				case 1: {
					String string = args[0];
					
					switch (string) {
						case "day":
						case "d": {
							Bukkit.getOnlinePlayers().forEach(player -> {
								player.getWorld().setTime(0L);
							});
							
							SexyMessage.send(sender, "Вы установили режим §aдень.");
							return true;
						}
						
						case "night":
						case "n": {
							Bukkit.getOnlinePlayers().forEach(player -> {
								player.getWorld().setTime(14000L);
							});
							
							SexyMessage.send(sender, "Вы установили режим §aночьц.");
							return true;
						}
					}
					SexyMessage.send(sender, "&cАргумент был не найден! Используйте аргументы: &8[&cday/night&8]");
					return true;
				}
				
				default: {
					SexyMessage.send(sender, this.usage);
					return true;
				}
			}
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		switch (args.length) {
			case 1: {
				String string = args[0];
				
				switch (string) {
					case "day":
					case "d": {
						player.getWorld().setTime(0L);
						SexyMessage.send(player, "Вы установили режим §aдень.");
						return true;
					}
					
					case "night":
					case "n": {
						player.getWorld().setTime(14000L);
						SexyMessage.send(player, "Вы установили режим §aночь.");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&cday/night&8]");
				return true;
			}
			
			default: {
				SexyMessage.send(player, this.usage);
				return true;
			}
		}
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> list = Lists.newArrayList();
		
		if (args.length == 1) {
			if (sender.hasPermission("fluxmber.admin")) {
				List<String> available = Arrays.asList("day", "night");
				for (String key : available) {
					if (key.startsWith(args[0])) {
						list.add(key);
					}
				}
			}
		}
		Collections.sort(list);
		return list;
	}
}