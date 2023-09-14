package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;
import java.util.*;

public class ClearCommand extends SexyCommand {
	
	public ClearCommand() {
		super("clear",
		"§cИспользуйте правильно команду: /clear §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("ci"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			switch (args.length) {
				case 1: {
					Player target = Bukkit.getPlayer(args[0]);
					
					if (target == null) {
						SexyMessage.send(sender, "&cИгрок " + args[0] + " не найден!");
						return true;
					}
					
					target.getInventory().clear();
					SexyMessage.send(target, "&cИгрок " + sender.getName() + " очистил Вам инвентарь!");
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
			case 0: {
				if (!player.hasPermission("fluxmber.clear")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				player.getInventory().clear();
				SexyMessage.send(player, "&cВы очистили свой инвентарь!");
				return true;
			}
			
			case 1: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				
				target.getInventory().clear();
				SexyMessage.send(target, "&cИгрок " + player.getName() + " очистил Вам инвентарь!");
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
				list.addAll(Bukkit.getOnlinePlayers().stream()
				.map(Player::getName)
				.filter(name -> name.startsWith(args[0]))
				.collect(Collectors.toList()));
			}
		}
		Collections.sort(list);
		return list;
	}
}