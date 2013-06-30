package com.ape.fgengine;

public class Logger {

	public static final int INFO = 0, DEBUG = 1;

	private static int level = 0;

	public static void log(String msg, int level) {
		if (level <= Logger.level) {
			System.out.println(msg);
		}
	}

}
