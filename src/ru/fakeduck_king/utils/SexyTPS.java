package ru.fakeduck_king.utils;

import java.lang.reflect.Field;
import org.bukkit.Bukkit;

public class SexyTPS {
	public static String get() {
		try {
			String version = Bukkit.getServer().getClass().getPackage().getName().substring(23);
			Class<?> nmsClass = Class.forName("net.minecraft.server." + version + ".MinecraftServer");
			Object minecraftServer = nmsClass.getMethod("getServer").invoke(null);
			Field recentTPSField = minecraftServer.getClass().getField("recentTps");
			
			double[] recentTPS = (double[])recentTPSField.get(minecraftServer);
			
			return SexyTPS.format(recentTPS[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String format(double tps) {
		String color;
		
		if (tps > 18.D) {
			color = "§a";
		}
		else if (tps > 16.D) {
			color = "§e";
		}
		else {
			color = "§c";
		}
		
		double formattedTPS = Math.min(Math.round(tps * 100.D) / 100.D, 20.D);
		
		return color + formattedTPS;
	}
}