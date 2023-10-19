package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import com.google.common.io.*;

import api.API;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class HubCommand extends SexyCommand {
	
	public HubCommand() {
		super("hub",
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
		
		Player player = (Player) sender;
		
		ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
		byteArrayDataOutput.writeUTF("Connect");
		byteArrayDataOutput.writeUTF("hub");
		
		player.sendPluginMessage(API.getInstance(), "BungeeCord", byteArrayDataOutput.toByteArray());
		return true;
	}
}