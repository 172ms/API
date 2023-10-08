package me.api.command.chat;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.api.data.*;
import org.bukkit.*;
import java.util.*;
import api.*;

public class MessageCommand extends SexyCommand {
	
	public MessageCommand() {
		super("message",
		"§cИспользуйте правильно команду: /message §8[§cИгрок§8] §8[§cСообщение§8]",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("m"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (args.length == 0) {
			SexyMessage.send(player, this.usage);
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			SexyMessage.send(player, "&cИгрок " + args[0] + " не найден!");
			return true;
		}
		
		if (player == target) {
			SexyMessage.send(player, "&cВы одинокий? :(");
			return true;
		}
		
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(player);
		
		if (playerAPI.getIgnoreList().contains(target.getName())) {
			SexyMessage.send(player, "&cВы добавили игрока " + target.getName() + " в чёрный список.");
			return true;
		}
		
		PlayerAPI targetAPI = PlayerAPI.getPlayerAPI(target);
		
		if (targetAPI.getIgnoreList().contains(player.getName())) {
			SexyMessage.send(player, "&cИгрок " + target.getName() + " добавил Вас в чёрный список.");
			return true;
		}
		
		if (playerAPI.isIgnoreAll()) {
			SexyMessage.send(player, "&cВы игнорируете всех.");
			return true;
		}
		
		if (targetAPI.isIgnoreAll()) {
			SexyMessage.send(player, "&cИгрок " + target.getName() + " игнорирует всех.");
			return true;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 1; i < args.length; ++i) {
			stringBuilder.append(args[i]).append(" ");
		}
		
		String message = stringBuilder.toString().trim()
		.replaceAll("\\s+", " ").trim();
		
		if (message.isEmpty()) {
			SexyMessage.send(player, "&cВведите сообщение!");
			return true;
		}
		
		SexyMessage.send(player, "§8[§fЯ §a-> §f" + target.getName() + "§8]§f: " + message);
		SexyMessage.send(API.getInstance(), player, target, "§8[§f" + player.getName() + " §a-> §fЯ§8]§f: " + message);
		return true;
	}
}