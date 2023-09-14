package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;
import java.util.*;

public class GamemodeCommand extends SexyCommand {
	
	public GamemodeCommand() {
		super("gamemode",
		"§cИспользуйте правильно команду: /gamemode §8[§cРежим§8] §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("gm"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			switch (args.length) {
				case 2: {
					Player target = Bukkit.getPlayer(args[1]);
					
					if (target == null) {
						SexyMessage.send(sender, "&cИгрок " + args[1] + " не найден!");
						return true;
					}
					
					String string = args[0];
					
					switch (string) {
						case "survival":
						case "0": {
							target.setGameMode(GameMode.SURVIVAL);
							SexyMessage.send(target, "Вам установили режим &aвыживание &fигрок &a" + sender.getName() + ".");
							return true;
						}
						
						case "creative":
						case "1": {
							target.setGameMode(GameMode.CREATIVE);
							SexyMessage.send(target, "Вам установили режим &aкреатива &fигрок &a" + sender.getName() + ".");
							return true;
						}
						
						case "adventure":
						case "2": {
							target.setGameMode(GameMode.ADVENTURE);
							SexyMessage.send(target, "Вам установили режим &aприключение &fигрок &a" + sender.getName() + ".");
							return true;
						}
						
						case "spectator":
						case "3": {
							target.setGameMode(GameMode.SPECTATOR);
							SexyMessage.send(target, "Вам установили режим &aнаблюдателя &fигрок &a" + sender.getName() + ".");
							return true;
						}
					}
					SexyMessage.send(sender, "&cАргумент был не найден! Используйте аргументы: &8[&csurvival/creative/adventure/spectator&8]");
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
				if (!player.hasPermission("fluxmber.gamemode")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				String string = args[0];
				
				switch (string) {
					case "survival":
					case "0": {
						player.setGameMode(GameMode.SURVIVAL);
						SexyMessage.send(player, "Вы установили режим &aвыживание.");
						return true;
					}
					
					case "creative":
					case "1": {
						player.setGameMode(GameMode.CREATIVE);
						SexyMessage.send(player, "Вы установили режим &aкреатива.");
						return true;
					}
					
					case "adventure":
					case "2": {
						player.setGameMode(GameMode.ADVENTURE);
						SexyMessage.send(player, "Вы установили режим &aприключение.");
						return true;
					}
					
					case "spectator":
					case "3": {
						player.setGameMode(GameMode.SPECTATOR);
						SexyMessage.send(player, "Вы установили режим &aнаблюдателя.");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&csurvival/creative/adventure/spectator&8]");
				return true;
			}
			
			case 2: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[1] + " не найден!");
					return true;
				}
				
				String string = args[0];
				
				switch (string) {
					case "survival":
					case "0": {
						target.setGameMode(GameMode.SURVIVAL);
						SexyMessage.send(target, "Вам установили режим &aвыживание &fигрок &a" + player.getName() + ".");
						return true;
					}
					
					case "creative":
					case "1": {
						target.setGameMode(GameMode.CREATIVE);
						SexyMessage.send(target, "Вам установили режим &aкреатива &fигрок &a" + player.getName() + ".");
						return true;
					}
					
					case "adventure":
					case "2": {
						target.setGameMode(GameMode.ADVENTURE);
						SexyMessage.send(target, "Вам установили режим &aприключение &fигрок &a" + player.getName() + ".");
						return true;
					}
					
					case "spectator":
					case "3": {
						target.setGameMode(GameMode.SPECTATOR);
						SexyMessage.send(target, "Вам установили режим &aнаблюдателя &fигрок &a" + player.getName() + ".");
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: &8[&csurvival/creative/adventure/spectator&8]");
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
			if (sender.hasPermission("fluxmber.gamemode")) {
				List<String> available = Arrays.asList("0", "1", "2", "3");
				for (String key : available) {
					if (key.startsWith(args[0])) {
						list.add(key);
					}
				}
			}
		}
		
		else if (args.length == 2) {
			if (sender.hasPermission("fluxmber.admin")) {
				list.addAll(Bukkit.getOnlinePlayers().stream()
				.map(Player::getName)
				.filter(name -> name.startsWith(args[1]))
				.collect(Collectors.toList()));
			}
		}
		Collections.sort(list);
		return list;
	}
}