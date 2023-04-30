package ru.fakeduck_king.utils;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;

public class SexyPing {
	public static int get(Player player) {
		CraftPlayer craftPlayer = (CraftPlayer)player;
		EntityPlayer entityPlayer = craftPlayer.getHandle();
		return entityPlayer.ping;
	}
}