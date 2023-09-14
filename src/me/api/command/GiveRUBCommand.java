package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class GiveRUBCommand extends SexyCommand {
	
	public GiveRUBCommand() {
		super("giverub",
		"§cИспользуйте правильно команду: /giverub §8[§cИгрок§8] §8[§cadd/remove/set§8] §8[§cСумма§8]",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				SexyMessage.send(sender, this.usage);
				return true;
			}
			
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
			PlayerAPI targetAPI = PlayerAPI.getPlayerAPI(target);
			
			switch (args.length) {
				case 3: {
					int amount;
					
					try {
						amount = Integer.valueOf(args[2]);
					}
					catch (NumberFormatException e) {
						SexyMessage.send(sender, "&cНе удалось распознать число!");
						return true;
					}
					
					String string = args[1];
					
					switch (string) {
						case "add":
						case "a": {
							targetAPI.addRUB(amount);
							DatabaseManager.getDatabaseManager().save(targetAPI);
							
							if (target.isOnline()) {
								SexyActionBar.send((Player)target, "&a+ " + Format.formatDouble(amount) + " RUB");
								SexyMessage.send((Player)target, "Вам выдали &a" + Format.formatDouble(amount) + " RUB &fигрок &a" + sender.getName() + ".");
								return true;
							}
							return true;
						}
						
						case "remove":
						case "r": {
							targetAPI.removeRUB(amount);
							DatabaseManager.getDatabaseManager().save(targetAPI);
							
							if (target.isOnline()) {
								SexyActionBar.send((Player)target, "&c- " + Format.formatDouble(amount) + " RUB");
								SexyMessage.send((Player)target, "У Вас забрали &c" + Format.formatDouble(amount) + " RUB &fигрок &a" + sender.getName() + ".");
								return true;
							}
							return true;
						}
						
						case "set":
						case "s": {
							targetAPI.setRUB(amount);
							DatabaseManager.getDatabaseManager().save(targetAPI);
							
							if (target.isOnline()) {
								SexyActionBar.send((Player)target, "&a" + Format.formatDouble(amount) + " RUB");
								SexyMessage.send((Player)target, "Вам установили &a" + Format.formatDouble(amount) + " RUB &fигрок &a" + sender.getName() + ".");
								return true;
							}
							return true;
						}
					}
					SexyMessage.send(sender, "&cАргумент был не найден! Используйте аргументы: [set/add/remove]");
					return true;
				}
				
				default: {
					SexyMessage.send(sender, this.usage);
					return true;
				}
			}
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("*")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (args.length == 0) {
			SexyMessage.send(player, this.usage);
			return true;
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		PlayerAPI targetAPI = PlayerAPI.getPlayerAPI(target);
		
		switch (args.length) {
			case 3: {
				int amount;
				
				try {
					amount = Integer.valueOf(args[2]);
				}
				catch (NumberFormatException e) {
					SexyMessage.send(player, "&cНе удалось распознать число!");
					return true;
				}
				
				String string = args[1];
				
				switch (string) {
					case "add":
					case "a": {
						targetAPI.addRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						
						if (target.isOnline()) {
							SexyActionBar.send((Player)target, "&a+ " + Format.formatDouble(amount) + " RUB");
							SexyMessage.send((Player)target, "Вам выдали &a" + Format.formatDouble(amount) + " RUB &fигрок &a" + player.getName() + ".");
							return true;
						}
						return true;
					}
					
					case "remove":
					case "r": {
						targetAPI.removeRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						
						if (target.isOnline()) {
							SexyActionBar.send((Player)target, "&c- " + Format.formatDouble(amount) + " RUB");
							SexyMessage.send((Player)target, "У Вас забрали &c" + Format.formatDouble(amount) + " RUB &fигрок &a" + player.getName() + ".");
							return true;
						}
						return true;
					}
					
					case "set":
					case "s": {
						targetAPI.setRUB(amount);
						DatabaseManager.getDatabaseManager().save(targetAPI);
						
						if (target.isOnline()) {
							SexyActionBar.send((Player)target, "&a" + Format.formatDouble(amount) + " RUB");
							SexyMessage.send((Player)target, "Вам установили &a" + Format.formatDouble(amount) + " RUB &fигрок &a" + player.getName() + ".");
							return true;
						}
						return true;
					}
				}
				SexyMessage.send(player, "&cАргумент был не найден! Используйте аргументы: [set/add/remove]");
				return true;
			}
			
			default: {
				if (!player.hasPermission("*")) {
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
		
		if (sender.hasPermission("*")) {
			if (args.length == 1) {
				list.addAll(Bukkit.getOnlinePlayers().stream()
				.map(Player::getName)
				.filter(name -> name.startsWith(args[0]))
				.collect(Collectors.toList()));
			}
			
			else if (args.length == 2) {
				List<String> available = Arrays.asList("add", "remove",  "set");
				for (String key : available) {
					if (key.startsWith(args[1])) {
						list.add(key);
					}
				}
			}
			
			else if (args.length == 3) {
				list = Collections.singletonList("сумма");
			}
		}
		Collections.sort(list);
		return list;
	}
}