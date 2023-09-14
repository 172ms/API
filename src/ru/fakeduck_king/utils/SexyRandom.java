package ru.fakeduck_king.utils;

import java.util.Random;

public class SexyRandom {
	public static int random(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
}