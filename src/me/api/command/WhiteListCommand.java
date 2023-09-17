package me.api.command;

import ru.fakeduck_king.register.commands.*;
import net.minecraft.server.v1_16_R3.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.*;

@SuppressWarnings("deprecation")
public class WhiteListCommand extends SexyCommand {
	
	public WhiteListCommand() {
		super("wt",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player)sender;
		MinecraftServer minecraftServer = MinecraftServer.getServer();
		
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
				if (minecraftServer.getPlayerList().getHasWhitelist()) {
					minecraftServer.getPlayerList().reloadWhitelist();
				}
				
				minecraftServer.getPlayerList().setHasWhitelist(true);
				SexyMessage.send(player, "&aБелый список включен.");
				return true;
			}
			
			case "off": {
				if (!minecraftServer.getPlayerList().getHasWhitelist()) {
					minecraftServer.getPlayerList().reloadWhitelist();
				}
				
				minecraftServer.getPlayerList().setHasWhitelist(false);
				SexyMessage.send(player, "&сБелый список выключен.");
				return true;
			}
			
			case "list": {
				String[] getWhitelisted = minecraftServer.getPlayerList().getWhitelisted();
				
				if (getWhitelisted.length == 0) {
					SexyMessage.send(player, "&cБелый список пуст!");
				}
				else {
					SexyMessage.send(player, "В белом списке &a" + getWhitelisted.length + " &fигроков: &a" + String.join(", ", getWhitelisted) + ".");
				}
				return true;
			}
			
			case "reload": {
				minecraftServer.getPlayerList().reloadWhitelist();
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
					SexyMessage.send(sender, "&cИгрок " + args[1] + " не найден!");
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