package ru.fakeduck_king.messages;

import com.google.common.io.ByteArrayDataOutput;
import ru.fakeduck_king.utils.player.PlayerSet;
import org.bukkit.scheduler.BukkitRunnable;
import com.google.common.io.ByteStreams;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SexyMessage {
	public static void send(Plugin plugin, Player player, String[] messages) {
		List<String> list = Arrays.asList(messages);
		Iterator<String> iterator = list.iterator();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!iterator.hasNext()) {
					this.cancel();
					
					if (PlayerSet.getPlayerSet().get(player)) {
						PlayerSet.getPlayerSet().remove(player);
					}
					return;
				}
				String message = iterator.next();
				
				SexyMessage.send(player, message);
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
	public static void send(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void send(Player player, String message) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void send(Plugin plugin, Player player, Player target, String message) {
		ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
		
		byteArrayDataOutput.writeUTF("Message");
		byteArrayDataOutput.writeUTF(target.getName());
		byteArrayDataOutput.writeUTF(message);
		
		player.sendPluginMessage(plugin, "BungeeCord", byteArrayDataOutput.toByteArray());
	}
}