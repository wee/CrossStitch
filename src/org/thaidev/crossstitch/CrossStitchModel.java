/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.CrossStitchModel
 * 
 */
package org.thaidev.crossstitch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.thaidev.crossstitch.event.ImageUpdateEvent;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * CrossStitchModel
 */
public abstract class CrossStitchModel implements Observable {
	private List observers = new ArrayList();

	private String imageFile;
	private BufferedImage image;

	/**
	 * 
	 */
	public CrossStitchModel() {

	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#addObserver(org.thaidev.util.Observer)
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#removeObserver(org.thaidev.util.Observer)
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) {
		for (Iterator iter = observers.iterator(); iter.hasNext();) {
			Observer o = (Observer) iter.next();
			o.update(this, arg);
		}
	}

	/**
	 * @return
	 */
	public String getImageFile() {
		return imageFile;
	}

	/**
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param string
	 */
	public void setImageFile(String string) {
		imageFile = string;
	}

	/**
	 * @param image
	 */
	public void setImage(BufferedImage newImage) {
		BufferedImage old = this.image;
		if (old != newImage) {
			this.image = newImage;
			notifyObservers(new ImageUpdateEvent(image));
		}
	}

}
