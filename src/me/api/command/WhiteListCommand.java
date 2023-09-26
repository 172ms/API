package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;
import java.util.*;

@SuppressWarnings("deprecation")
public class WhiteListCommand extends SexyCommand {
	
	public WhiteListCommand() {
		super("whitelist",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length < 1) {
				this.sendHelper(sender);
				return true;
			}
			
			String string = args[0];
			
			switch (string) {
				case "on": {
					if (Bukkit.hasWhitelist()) {
						SexyMessage.send(sender, "&cБелый список уже включен!");
						return true;
					}
					
					Bukkit.setWhitelist(true);
					SexyMessage.send(sender, "&aБелый список включен.");
					return true;
				}
				
				case "off": {
					if (!Bukkit.hasWhitelist()) {
						SexyMessage.send(sender, "&aБелый список уже выключен!");
						return true;
					}
					
					Bukkit.setWhitelist(false);
					SexyMessage.send(sender, "&cБелый список выключен.");
					return true;
				}
				
				case "list": {
					Set<OfflinePlayer> whitelistedPlayers = Bukkit.getWhitelistedPlayers();
					
					if (whitelistedPlayers.isEmpty()) {
						SexyMessage.send(sender, "&cБелый список пуст!");
					}
					else {
						String playerList = whitelistedPlayers.stream()
						.map(OfflinePlayer::getName)
						.sorted()
						.collect(Collectors.joining(", "));
						
						SexyMessage.send(sender, "В белом списке &a" + whitelistedPlayers.size() + " &fигроков: &a" + playerList + ".");
					}
					return true;
				}
				
				case "reload": {
					Bukkit.reloadWhitelist();
					SexyMessage.send(sender, "&aБелый список перезагружен.");
					return true;
				}
			}
			switch (string) {
				case "add": {
					if (args.length < 2) {
						SexyMessage.send(sender, "§cИспользуйте правильно команду: /whitelist add §8[§cИгрок§8]");
						return true;
					}
					
					Bukkit.getOfflinePlayer(args[1]).setWhitelisted(true);
					SexyMessage.send(sender, "§a" + args[1] + " &fдобавлен в белый список.");
					return true;
				}
				case "remove": {
					if (args.length < 2) {
						SexyMessage.send(sender, "§cИспользуйте правильно команду: /whitelist remove §8[§cИгрок§8]");
						return true;
					}
					
					if (!Bukkit.getWhitelistedPlayers().contains(Bukkit.getOfflinePlayer(args[1]))) {
						SexyMessage.send(sender, "&cИгрок " + args[1] + " не найден!");
						return true;
					}
					
					Bukkit.getOfflinePlayer(args[1]).setWhitelisted(false);
					SexyMessage.send(sender, "§c" + args[1] + " удален из белого списка.");
					return true;
				}
				default: {
					this.sendHelper(sender);
					return true;
				}
			}
		}
		
		Player player = (Player)sender;
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (args.length < 1) {
			this.sendHelper(player);
			return true;
		}
		
		String string = args[0];
		
		switch (string) {
			case "on": {
				if (Bukkit.hasWhitelist()) {
					SexyMessage.send(player, "&cБелый список уже включен!");
					return true;
				}
				
				Bukkit.setWhitelist(true);
				SexyMessage.send(player, "&aБелый список включен.");
				return true;
			}
			
			case "off": {
				if (!Bukkit.hasWhitelist()) {
					SexyMessage.send(player, "&aБелый список уже выключен!");
					return true;
				}
				
				Bukkit.setWhitelist(false);
				SexyMessage.send(player, "&cБелый список выключен.");
				return true;
			}
			
			case "list": {
				Set<OfflinePlayer> whitelistedPlayers = Bukkit.getWhitelistedPlayers();
				
				if (whitelistedPlayers.isEmpty()) {
					SexyMessage.send(sender, "&cБелый список пуст!");
				}
				else {
					String playerList = whitelistedPlayers.stream()
					.map(OfflinePlayer::getName)
					.sorted()
					.collect(Collectors.joining(", "));
					
					SexyMessage.send(player, "В белом списке &a" + whitelistedPlayers.size() + " &fигроков: &a" + playerList + ".");
				}
				return true;
			}
			
			case "reload": {
				Bukkit.reloadWhitelist();
				SexyMessage.send(player, "&aБелый список перезагружен.");
				return true;
			}
		}
		switch (string) {
			case "add": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /whitelist add §8[§cИгрок§8]");
					return true;
				}
				
				Bukkit.getOfflinePlayer(args[1]).setWhitelisted(true);
				SexyMessage.send(player, "§a" + args[1] + " &fдобавлен в белый список.");
				return true;
			}
			case "remove": {
				if (args.length < 2) {
					SexyMessage.send(player, "§cИспользуйте правильно команду: /whitelist remove §8[§cИгрок§8]");
					return true;
				}
				
				if (!Bukkit.getWhitelistedPlayers().contains(Bukkit.getOfflinePlayer(args[1]))) {
					SexyMessage.send(player, "&cИгрок " + args[1] + " не найден!");
					return true;
				}
				
				Bukkit.getOfflinePlayer(args[1]).setWhitelisted(false);
				SexyMessage.send(player, "§c" + args[1] + " удален из белого списка.");
				return true;
			}
			default: {
				this.sendHelper(player);
				return true;
			}
		}
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> list = Lists.newArrayList();
		
		if (args.length == 1) {
			List<String> available = Arrays.asList("on", "off", "list", "reload", "add", "remove");
			for (String key : available) {
				if (key.startsWith(args[0])) {
					list.add(key);
				}
			}
		}
		else if (args[0].equalsIgnoreCase("add")) {
			list.addAll(Bukkit.getOnlinePlayers().stream()
			.map(Player::getName)
			.filter(name -> name.startsWith(args[1]))
			.collect(Collectors.toList()));
		}
		else if (args[0].equalsIgnoreCase("remove")) {
			list.addAll(Bukkit.getWhitelistedPlayers().stream()
			.map(OfflinePlayer::getName)
			.filter(name -> name.startsWith(args[1]))
			.collect(Collectors.toList()));
		}
		return list;
	}
	
	private void sendHelper(CommandSender sender) {
		SexyMessage.send(sender, "Основные команды:");
		SexyMessage.send(sender, "&a/whitelist on - &fвключить белый список.");
		SexyMessage.send(sender, "&a/whitelist off - &fвыключить белый список.");
		SexyMessage.send(sender, "&a/whitelist list - &fузнать, кто в белом списке.");
		SexyMessage.send(sender, "&a/whitelist reload &a- &fперезагрузить белый список.");
		SexyMessage.send(sender, "&a/whitelist add §8[§cИгрок§8] &a- &fдобавить в белый список.");
		SexyMessage.send(sender, "&a/whitelist remove §8[§cИгрок§8] &a- &fудалить из белого списка.");
	}
	
	private void sendHelper(Player player) {
		SexyMessage.send(player, "Основные команды:");
		SexyMessage.send(player, "&a/whitelist on - &fвключить белый список.");
		SexyMessage.send(player, "&a/whitelist off - &fвыключить белый список.");
		SexyMessage.send(player, "&a/whitelist list - &fузнать, кто в белом списке.");
		SexyMessage.send(player, "&a/whitelist reload &a- &fперезагрузить белый список.");
		SexyMessage.send(player, "&a/whitelist add §8[§cИгрок§8] &a- &fдобавить в белый список.");
		SexyMessage.send(player, "&a/whitelist remove §8[§cИгрок§8] &a- &fудалить из белого списка.");
	}
}