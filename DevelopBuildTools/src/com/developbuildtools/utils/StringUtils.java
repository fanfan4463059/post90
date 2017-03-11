package com.developbuildtools.utils;

public class StringUtils {
	/**
	 * Ê××ÖÄ¸´óÐ´
	 * 
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}

	public static boolean isNull(String str) {
		return (str == null || str.equals(""));
	}
}
