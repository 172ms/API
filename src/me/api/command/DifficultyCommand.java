package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class DifficultyCommand extends SexyCommand {
	
	public DifficultyCommand() {
		super("difficulty",
		"§cИспользуйте правильно команду: /difficulty §8[§cСложность§8]",
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
						case "peaceful":
						case "p": {
							Bukkit.getWorlds().forEach(world -> {
								world.setDifficulty(Difficulty.PEACEFUL);
							});
							
							SexyMessage.send(sender, "Режим сложность игры: &aPEACEFUL.");
							return true;
						}
						
						case "easy":
						case "e": {
							Bukkit.getWorlds().forEach(world -> {
								world.setDifficulty(Difficulty.EASY);
							});
							
							SexyMessage.send(sender, "Режим сложность игры: &aEASY.");
							return true;
						}
						
						case "normal":
						case "n": {
							Bukkit.getWorlds().forEach(world -> {
								world.setDifficulty(Difficulty.NORMAL);
							});
							
							SexyMessage.send(sender, "Режим сложность игры: &aNORMAL.");
							return true;
						}
						
						case "hard":
						case "h": {
							Bukkit.getWorlds().forEach(world -> {
								world.setDifficulty(Difficulty.HARD);
							});
							
							SexyMessage.send(sender, "Режим сложность игры: &aHARD.");
							return true;
						}
					}
					SexyMessage.send(sender, "&cАргумент был не найден! Используйте аргументы: &8[&cpeaceful/easy/normal/hard&8]");
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
					case "peaceful":
					case "p": {
						Bukkit.getWorlds().forEach(world -> {
							world.setDifficulty(Difficulty.PEACEFUL);
						});
						
						SexyMessage.send(player, "Режим сложность игры: &aPEACEFUL.");
						return true;
					}
					
					case "easy":
					case "e": {
						Bukkit.getWorlds().forEach(world -> {
							world.setDifficulty(Difficulty.EASY);
						});
						
						SexyMessage.send(player, "Режим сложность игры: &aEASY.");
						return true;
					}
					
					case "normal":
					case "n": {
						Bukkit.getWorlds().forEach(world -> {
							world.setDifficulty(Difficulty.NORMAL);
						});
						
						SexyMessage.send(player, "Режим сложность игры: &aNORMAL.");
						return true;
					}
					
					case "hard":
					case "h": {
						Bukkit.getWorlds().forEach(world -> {
							world.setDifficulty(Difficulty.HARD);
						});
						
						SexyMessage.send(player, "Режим сложность игры: &aHARD.");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&cpeaceful/easy/normal/hard&8]");
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
				List<String> available = Arrays.asList("peaceful", "easy", "normal", "hard");
				for (String key : available) {
					if (key.startsWith(args[0])) {
						list.add(key);
					}
				}
			}
		}
		return list;
	}
}