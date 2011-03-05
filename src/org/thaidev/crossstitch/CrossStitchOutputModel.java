/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.CrossStitchOutputModel
 * 
 */
package org.thaidev.crossstitch;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.thaidev.crossstitch.event.ErrorStatusMessageEvent;
import org.thaidev.crossstitch.event.StatusMessageEvent;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * CrossStitchOutputModel
 */
public class CrossStitchOutputModel extends CrossStitchModel {

	/**
	 * 
	 */
	public CrossStitchOutputModel() {
		super();
	}

	public void writeOutput(File outFile) {
		if (getImage() != null) {

			String fileName = outFile.getName().toLowerCase();
			String fileExtension = "jpg";
			if (fileName.endsWith(".gif")) {
				fileExtension = "gif";
			}
			try {
				ImageIO.write(getImage(), fileExtension, outFile);
			} catch (IOException e) {
				notifyObservers(new ErrorStatusMessageEvent(e.toString()));
			}
			notifyObservers(new StatusMessageEvent("File:" + outFile.getName() + " is saved."));
		}
	}
}