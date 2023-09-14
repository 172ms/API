package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class OnlineCommand extends SexyCommand {
	
	public OnlineCommand() {
		super("online",
		"",
		"",
		Prefix.ERROR_PERMISSIONS,
		Arrays.asList("list"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			SexyMessage.send(sender, "Онлайн сервера: &a" + Bukkit.getOnlinePlayers().size() + " &fиз &a" + Bukkit.getMaxPlayers());
			return true;
		}
		
		Player player = (Player)sender;
		
		SexyMessage.send(player, "Онлайн сервера: &a" + Bukkit.getOnlinePlayers().size() + " &fиз &a" + Bukkit.getMaxPlayers());
		SexyActionBar.send(player, "&a" + Bukkit.getOnlinePlayers().size() + " &fиз &a" + Bukkit.getMaxPlayers());
		return true;
	}
}