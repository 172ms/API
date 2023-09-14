package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;
import java.util.*;

public class TeleportCommand extends SexyCommand {
	
	public TeleportCommand() {
		super("teleport",
		"§cИспользуйте правильно команду: /teleport §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("tp"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		
		switch (args.length) {
			case 1: {
				if (!player.hasPermission("fluxmber.teleport")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
					return true;
				}
				
				player.teleport(target.getLocation());
				SexyMessage.send(player, "Вы телепортировались &a" + target.getName() + ".");
				return true;
			}
			
			case 3: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				Location location = player.getLocation();
				
				try {
					int x = Integer.parseInt(args[0]);
					int y = Integer.parseInt(args[1]);
					int z = Integer.parseInt(args[2]);
					location = new Location(player.getWorld(), (double)x, (double)y, (double)z);
				}
				catch (NumberFormatException e) {
					SexyMessage.send(player, "&cНе удалось распознать число!");
					return true;
				}
				
				player.teleport(location);
				Chunk chunk = player.getWorld().getChunkAt(location);
				Bukkit.getWorld(player.getWorld().getName()).loadChunk(chunk);
				SexyMessage.send(player, "Вы телепортировались по координатам &aX: " + player.getLocation().getX() + ", Y: " + player.getLocation().getY() + ", Z: " + player.getLocation().getZ() + ".");
				return true;
			}
			
			default: {
				if (!player.hasPermission("fluxmber.admin")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				if (args.length <= 2) {
					SexyMessage.send(sender, "&cВвведите координаты: X, Y, Z!");
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
			if (sender.hasPermission("fluxmber.teleport")) {
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