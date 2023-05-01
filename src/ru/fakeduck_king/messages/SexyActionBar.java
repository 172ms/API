package ru.fakeduck_king.messages;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import net.minecraft.server.v1_16_R3.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import java.util.UUID;

public class SexyActionBar {
	public static void send(Player player, String message) {
		UUID UUID = player.getUniqueId();
		IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(iChatBaseComponent, ChatMessageType.GAME_INFO, UUID);
		
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
	}
}