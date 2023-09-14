package ru.fakeduck_king.utils;

import ru.fakeduck_king.messages.Prefix;
import java.text.DecimalFormat;
import org.bukkit.Bukkit;

public class Format {
	public static String formatDouble(float value) {
		try {
			String[] array = { "", "K", "M" };
			int index = 0;
			
			while (value >= 1000 && index < array.length - 1) {
				value = value / 1000.F;
				index++;
			}
			
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			return String.format("%s%s", decimalFormat.format(value).replace(',', '.'), array[index]);
		}
		catch (Exception e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "FAILED TO GET A NUMBER");
			return null;
		}
	}
	
	public static float fixDouble(float amount, int digits) {
		if (digits == 0) {
			return amount;
		}
		
		StringBuilder stringBuilder = new StringBuilder("###.");
		
		for (int i = 0; i < digits; i++) {
			stringBuilder.append("#");
		}
		
		DecimalFormat decimalFormat = new DecimalFormat(stringBuilder.toString());
		return Float.valueOf(decimalFormat.format(amount).replace(",", "."));
	}
	
	public static float fixDouble(float amount) {
		return Format.fixDouble(amount, 3);
	}
}