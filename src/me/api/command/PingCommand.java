package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import ru.fakeduck_king.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class PingCommand extends SexyCommand {
	
	public PingCommand() {
		super("ping",
		"",
		"",
		Prefix.ERROR_PERMISSIONS);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You not player!");
			return true;
		}
		
		Player player = (Player)sender;
		
		if (SexyPing.get(player) > 99) {
			SexyMessage.send(player, "Ваш пинг: &c" + SexyPing.get(player) + "ms.");
		}
		else if (SexyPing.get(player) > 49) {
			SexyMessage.send(player, "Ваш пинг: &6" + SexyPing.get(player) + "ms.");
		}
		else {
			SexyMessage.send(player, "Ваш пинг: &a" + SexyPing.get(player) + "ms.");
		}
		return true;
	}
}