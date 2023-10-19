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
public class DeopCommand extends SexyCommand {
	
	public DeopCommand() {
		super("deop",
		"§cИспользуйте правильно команду: /deop §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			switch (args.length) {
				case 1: {
					String name = args[0];
					
					if (!Bukkit.getServer().getOfflinePlayer(name).isOp()) {
						SexyMessage.send(sender, "&cИгрок " + args[0] + " не найден!");
						return true;
					}
					
					Bukkit.getServer().getOfflinePlayer(name).setOp(false);
					SexyMessage.send(sender, "&cИгрок " + name + " больше не оператор!");
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
				if (!player.hasPermission("*")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				String name = args[0];
				
				if (!Bukkit.getServer().getOfflinePlayer(name).isOp()) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				
				Bukkit.getServer().getOfflinePlayer(name).setOp(false);
				SexyMessage.send(player, "&cИгрок " + name + " больше не оператор!");
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
		
		if (args.length == 1) {
			if (sender.hasPermission("*")) {
				list.addAll(Bukkit.getOperators().stream()
				.map(OfflinePlayer::getName)
				.filter(name -> name.startsWith(args[0]))
				.collect(Collectors.toList()));
			}
		}
		Collections.sort(list);
		return list;
	}
}