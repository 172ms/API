package me.api.command.chat;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import me.api.data.*;
import org.bukkit.*;

@SuppressWarnings("deprecation")
public class DonatorsToggleCommand extends SexyCommand {
	
	public DonatorsToggleCommand() {
		super("donatorstoggle",
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
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		PlayerAPI playerAPI = PlayerAPI.getPlayerAPI(offlinePlayer);
		
		if (!player.hasPermission("fluxmber.donators")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (!playerAPI.getMessageDonators()) {
			playerAPI.setMessageDonators(true);
			DatabaseManager.getDatabaseManager().save(playerAPI);
			
			SexyMessage.send(player, "Вы выключили сообщение от &cDONATORS.");
			return true;
		}
		else {
			playerAPI.setMessageDonators(false);
			DatabaseManager.getDatabaseManager().save(playerAPI);
			
			SexyMessage.send(player, "Вы включили сообщение от &aDONATORS");
			return true;
		}
	}
}