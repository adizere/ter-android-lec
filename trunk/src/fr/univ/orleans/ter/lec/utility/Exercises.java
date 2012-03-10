package fr.univ.orleans.ter.lec.utility;

import java.util.Random;

public class Exercises {

	public static String getRandomString(int length) {
		return getRandomString("abcdefghijklmnopqrstuvwxyz", length);
	}

	private static String getRandomString(String h, int length) {

		Random rand = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(h.charAt(rand.nextInt(h.length())));
		}
		return sb.toString();
	}

}
