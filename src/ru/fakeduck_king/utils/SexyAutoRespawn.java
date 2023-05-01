package ru.fakeduck_king.utils;

import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SexyAutoRespawn {
	public static void respawn(Player player) {
		PacketPlayInClientCommand packetPlayInClientCommand = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
		
		((CraftPlayer)player).getHandle().playerConnection.a(packetPlayInClientCommand);
	}
}