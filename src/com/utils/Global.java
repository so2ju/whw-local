package com.utils;

public class Global {
	public static final int MAX_INGREDIENTES=10;
	public static final int LARGO_TORTERA=3;
	public static final int ANCHO_TORTERA=3;
	private static boolean verbose=false;
	public static void setVerbose(boolean isVerbose) {
		verbose = isVerbose;
	}
	public static boolean isVerbose() {
		return verbose;
	}
	
}
