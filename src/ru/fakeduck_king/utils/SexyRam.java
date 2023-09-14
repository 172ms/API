package ru.fakeduck_king.utils;

public class SexyRam {
	private static final int value = 1024 * 1024;
	
	public static String getTotal() {
		return Runtime.getRuntime().totalMemory() / SexyRam.value + "MB";
	}
	
	public static String getUsed() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / SexyRam.value + "MB";
	}
	
	public static String getFree() {
		return Runtime.getRuntime().freeMemory() / SexyRam.value + "MB";
	}
	
	public static String getMax() {
		return Runtime.getRuntime().maxMemory() / SexyRam.value + "MB";
	}
}