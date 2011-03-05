/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.CrossStitchInputModel
 * 
 */
package org.thaidev.crossstitch;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.thaidev.crossstitch.event.ErrorStatusMessageEvent;
import org.thaidev.crossstitch.event.ImageUpdateEvent;
import org.thaidev.crossstitch.event.StatusMessageEvent;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * CrossStitchInputModel
 */
public class CrossStitchInputModel extends CrossStitchModel {

	/**
	 * 
	 */
	public CrossStitchInputModel() {
	}
	
	public void readInput(File inputFile) {
		if (inputFile == null) {
			notifyObservers(new ErrorStatusMessageEvent("Invalid input file"));
			return;
		}
		try {
			setImage(ImageIO.read(inputFile));
		} catch (IOException e) {
			notifyObservers(new ErrorStatusMessageEvent(e.toString()));
			return;
		}
		notifyObservers(new ImageUpdateEvent(getImage()));
		notifyObservers(
			new StatusMessageEvent(
				"File "
					+ inputFile.getName()
					+ " read, image size = "
					+ getImage().getWidth()
					+ "x"
					+ getImage().getHeight()));
	}


}
