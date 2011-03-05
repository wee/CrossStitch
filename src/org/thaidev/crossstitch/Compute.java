/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.Compute
 * 
 */
package org.thaidev.crossstitch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.thaidev.crossstitch.event.ErrorStatusMessageEvent;
import org.thaidev.crossstitch.event.ProgressEvent;
import org.thaidev.crossstitch.event.StatusMessageEvent;
import org.thaidev.crossstitch.event.StitchCompletedEvent;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * LGPL Open source copyright
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 * 
 * Compute
 */
public class Compute implements Observable {
	private boolean stop = false;
	private ColorTable colorTable = new DMC();
	private List observers = new ArrayList();

	private Set usedColors;
	
	/**
	 *  
	 */
	public Compute() {
		super();
	}

	public BufferedImage compute(BufferedImage originalImage, int step) {
		setStop(false);
		setProgress(0);
		if (originalImage == null) {
			notifyObservers(new StatusMessageEvent(""));
			notifyObservers(new ErrorStatusMessageEvent(
					"Please open an input image first"));
			return null;
		}
		usedColors = new HashSet();
		BufferedImage resultImage = new BufferedImage(originalImage.getWidth()
				* 5 / step, originalImage.getHeight() * 5 / step,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = resultImage.createGraphics();

		int height = originalImage.getHeight();
		int width = originalImage.getWidth();
		double red1, green1, blue1;
		for (int row = 0; row < height; row += step) {
			if (stop) {
				setProgress(0);
				notifyObservers(new StatusMessageEvent("Stiching stopped."));
				return null;
			}
			for (int column = 0; column < width; column += step) {
				setProgress((100 * row) / height);
				Color color = computePixel(originalImage, step, graphics, row, column);
				if (color != null) {
					usedColors.add(color);
				}
			}
			yield();
		}
		setProgress(100);
		notifyObservers(new StitchCompletedEvent(resultImage));
		notifyObservers(new StatusMessageEvent("Succeeded, number of colors used = " + usedColors.size()));
		return resultImage;
	}

	/**
	 * @param percent
	 *            TODO
	 *  
	 */
	private void setProgress(int percent) {
		notifyObservers(new ProgressEvent(percent));
	}

	/**
	 * @param originalImage
	 * @param step
	 * @param graphics
	 * @param row
	 * @param column
	 */
	private Color computePixel(BufferedImage originalImage, int step,
			Graphics2D graphics, int row, int column) {
		double redSquare;
		double greenSquare;
		double blueSquare;
		double lowestValue = Double.MAX_VALUE;
		int lowestPos = -1;
		for (int i = 0; i < DMC.redDMC.length; i++) {
			int rgb = originalImage.getRGB(column, row);
			redSquare = Math.pow(ColorTable.getRedValue(rgb) - DMC.redDMC[i], 2);
			greenSquare = Math.pow(ColorTable.getGreenValue(rgb) - DMC.greenDMC[i], 2);
			blueSquare = Math.pow(ColorTable.getBlueValue(rgb) - DMC.blueDMC[i], 2);
			double result = Math.sqrt(redSquare + greenSquare + blueSquare);
			if (result <= lowestValue) {
				lowestValue = result;
				lowestPos = i;
			}
		}
		if (lowestPos == -1) {
			notifyObservers(new ErrorStatusMessageEvent("Should not happen"));
		} else {
			Color aa = new Color(DMC.redDMC[lowestPos], DMC.greenDMC[lowestPos],
					DMC.blueDMC[lowestPos]);
			drawPixel(step, graphics, row, column, aa);
			return aa;
		}
		return null;
	}

	/**
	 * @param step
	 * @param graphics
	 * @param row
	 * @param column
	 * @param aa
	 */
	private void drawPixel(int step, Graphics2D graphics, int row, int column, Color aa) {
		graphics.setColor(aa);
		int x1 = 5 * column / step;
		int y1 = 5 * row / step;
		int x2 = 5 + (5 * column / step);
		int y2 = 5 + (5 * row / step);
		graphics.drawLine(x1, y1, x2, y2);
		graphics.drawLine(x1, y2, x2, y1);
	}

	private void yield() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.thaidev.util.Observable#addObserver(org.thaidev.util.Observer)
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.thaidev.util.Observable#removeObserver(org.thaidev.util.Observer)
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.thaidev.util.Observable#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) {
		for (Iterator iter = observers.iterator(); iter.hasNext();) {
			Observer o = (Observer) iter.next();
			o.update(this, arg);
		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
}