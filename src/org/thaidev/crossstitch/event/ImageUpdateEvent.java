/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.event.InputImageUpdateEvent
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
 * InputImageUpdateEvent
 */
public class ImageUpdateEvent {
	private BufferedImage image;
	/**
	 * 
	 */
	public ImageUpdateEvent(BufferedImage image) {
		setImage(image);
	}

	/**
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
