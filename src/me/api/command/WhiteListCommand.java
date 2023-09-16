package me.api.command;

import ru.fakeduck_king.register.commands.*;
import net.minecraft.server.v1_16_R3.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

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
			return true;
		}
		
		Player player = (Player)sender;
		MinecraftServer minecraftServer = MinecraftServer.getServer();
		
		if (!player.hasPermission("fluxmber.admin")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (args.length < 1) {
			return true;
		}
		
		String string = args[0];
		
		switch (string) {
			case "on": {
				minecraftServer.getPlayerList().setHasWhitelist(true);
				return true;
			}
			
			case "off": {
				minecraftServer.getPlayerList().setHasWhitelist(false);
				return true;
			}
			
			case "list": {;
                String[] getWhitelisted = minecraftServer.getPlayerList().getWhitelisted();
                return true;
			}
		}
		return true;
	}
}