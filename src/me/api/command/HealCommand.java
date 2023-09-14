package me.api.command;

import ru.fakeduck_king.register.commands.*;
import com.google.common.collect.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.stream.*;
import org.bukkit.*;
import java.util.*;

public class HealCommand extends SexyCommand {
	
	public HealCommand() {
		super("heal",
		"ІcИспользуйте правильно команду: /heal §8[§cИгрок§8]",
		"",
		Prefix.ERROR_PERMISSIONS);
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
					
					target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));
					target.setHealth(20.F);
					target.setFireTicks(0);
					target.setFoodLevel(20);
					target.setSaturation(20.F);
					SexyMessage.send(target, "&aИгрок " + sender.getName() + " Вас исцелил.");
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
				if (!player.hasPermission("fluxmber.heal")) {
					SexyMessage.send(player, this.permissionMessage);
					return true;
				}
				
				player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
				player.setHealth(20.F);
				player.setFireTicks(0);
				player.setFoodLevel(20);
				player.setSaturation(20.F);
				SexyMessage.send(player, "&aВы исцелились.");
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
				
				target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));
				target.setHealth(20.F);
				target.setFireTicks(0);
				target.setFoodLevel(20);
				target.setSaturation(20.F);
				SexyMessage.send(target, "&aИгрок " + player.getName() + " Вас исцелил.");
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