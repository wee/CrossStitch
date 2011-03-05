/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstich.event.StitchCompletedEvent
 * 
 */
package org.thaidev.crossstitch.event;

import java.awt.image.BufferedImage;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * StitchCompletedEvent
 */
public class StitchCompletedEvent {
	private BufferedImage resultImage;
	/**
	 * 
	 */
	public StitchCompletedEvent(BufferedImage resultImage) {
		setResultImage(resultImage);
	}

	/**
	 * @return
	 */
	public BufferedImage getResultImage() {
		return resultImage;
	}

	/**
	 * @param image
	 */
	public void setResultImage(BufferedImage image) {
		resultImage = image;
	}

}
