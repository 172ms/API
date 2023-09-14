package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class WeatherCommand extends SexyCommand {
	
	public WeatherCommand() {
		super("weather",
		"§cИспользуйте правильно команду: /weather §8[§cПогода§8]",
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
						case "clear":
						case "c": {
							Bukkit.getWorlds().forEach(world -> {
								world.setStorm(false);
								world.setThundering(false);
								world.setThunderDuration(0);
								world.setClearWeatherDuration(0);
							});
							
							SexyMessage.send(sender, "Вы установили режим &aCLEAR.");
							return true;
						}
						
						case "rain":
						case "r": {
							Bukkit.getWorlds().forEach(world -> {
								world.setStorm(true);
								world.setThundering(false);
							});
							
							SexyMessage.send(sender, "Вы установили режим &aRAIN.");
							return true;
						}
						
						case "thunder":
						case "t": {
							Bukkit.getWorlds().forEach(world -> {
								world.setStorm(true);
								world.setThundering(true);
							});
							
							SexyMessage.send(sender, "Вы установили режим &aTHUNDER.");
							return true;
						}
					}
					SexyMessage.send(sender, "&cАргумент был не найден! Используйте аргументы: &8[&cclear/rain/thunder&8]");
					return true;
				}
				
				default: {
					SexyMessage.send(sender, this.usage);
					return true;
				}
			}
			
		}
		
		Player player = (Player)sender;
		
		switch (args.length) {
			case 1: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				String string = args[0];
				
				switch (string) {
					case "clear":
					case "c": {
						Bukkit.getWorlds().forEach(world -> {
							world.setStorm(false);
							world.setThundering(false);
							world.setThunderDuration(0);
							world.setClearWeatherDuration(0);
						});
						
						SexyMessage.send(player, "Вы установили режим &aCLEAR.");
						return true;
					}
					
					case "rain":
					case "r": {
						Bukkit.getWorlds().forEach(world -> {
							world.setStorm(true);
							world.setThundering(false);
						});
						
						SexyMessage.send(player, "Вы установили режим &aRAIN.");
						return true;
					}
					
					case "thunder":
					case "t": {
						Bukkit.getWorlds().forEach(world -> {
							world.setStorm(true);
							world.setThundering(true);
						});
						
						SexyMessage.send(player, "Вы установили режим &aTHUNDER.");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&cclear/rain/thunder&8]");
				return true;
			}
			
			default: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
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
				List<String> available = Arrays.asList("clear", "rain", "thunder");
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