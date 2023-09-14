package me.api.command;

import ru.fakeduck_king.register.commands.*;
import ru.fakeduck_king.messages.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

@SuppressWarnings("deprecation")
public class HatCommand extends SexyCommand {
	
	public HatCommand() {
		super("hat",
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
		ItemStack hand = player.getInventory().getItemInHand();
		ItemStack itemStack = player.getInventory().getHelmet();
		
		if (!player.hasPermission("fluxmber.hat")) {
			SexyMessage.send(player, this.permissionMessage);
			return true;
		}
		
		if (hand == null || hand.getType() == null || hand.getType() == Material.AIR) {
			SexyMessage.send(player, "&c???");
			return true;
		}
		
		player.getInventory().setHelmet(hand);
		player.getInventory().setItemInHand(itemStack);
		SexyMessage.send(player, "Вы надели на голову &a" + hand.getType() + ".");
		return true;
	}
}