/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstitch.ColorTable
 * 
 */
package org.thaidev.crossstitch;

/**
 * @author Weerasak W. (weerasak@hotmail.com)
 *
 * ColorTable
 */
public abstract class ColorTable {
	public static final int R_MASK = 0x00ff0000;
	public static final int G_MASK = 0x0000ff00;
	public static final int B_MASK = 0x000000ff;
	public static final int A_MASK = 0xff000000;
	public abstract int getColorTableSize();
	public abstract int getColorTableAt(int index);
	
	public static int getRedValue(int rgb) {
		return ((rgb & ColorTable.R_MASK) >> 16);
	}

	public static int getGreenValue(int rgb) {
		return ((rgb & ColorTable.G_MASK) >> 8);
	}

	public static int getBlueValue(int rgb) {
		return (rgb & ColorTable.B_MASK);
	}

	public static int getRGB(int r, int g, int b) {
		return (r << 16) + (g << 8) + b;
	}

}