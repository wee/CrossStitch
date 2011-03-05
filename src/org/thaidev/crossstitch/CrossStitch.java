/*
 * Created on Dec 13, 2003
 *
 */
package org.thaidev.crossstitch;

import java.awt.image.BufferedImage;
import java.io.File;

import org.thaidev.crossstitch.event.CancelEvent;
import org.thaidev.crossstitch.event.DoStitchEvent;
import org.thaidev.crossstitch.event.FileOpenEvent;
import org.thaidev.crossstitch.event.FileSaveEvent;
import org.thaidev.crossstitch.event.StitchCompletedEvent;
import org.thaidev.crossstitch.ui.CrossStitchUI;
import org.thaidev.util.Observable;
import org.thaidev.util.Observer;

/**
 * Main class of the Cross Stitch matching program.
 * LGPL Open source copyright 
 * 
 * @author Zombibi
 * @version 0.9 20031012
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 1.0 20031013
 * @version 2.0 20031214
 */
public class CrossStitch implements Observer {
	private CrossStitchInputModel inputModel;
	private CrossStitchOutputModel outputModel;
	private CrossStitchUI ui;
	private Compute compute = null;
	 
	/**
	 * 
	 */
	public CrossStitch() {
		inputModel = new CrossStitchInputModel();
		outputModel = new CrossStitchOutputModel();
		ui = new CrossStitchUI();
		ui.addObserver(this);
		ui.getInputImagePanel().getZoomControlPanel().addObserver(ui.getInputImagePanel().getImagePanel());
		ui.getOutputImagePanel().getZoomControlPanel().addObserver(ui.getOutputImagePanel().getImagePanel());
		inputModel.addObserver(ui);
		inputModel.addObserver(ui.getInputImagePanel().getImagePanel());
		outputModel.addObserver(ui);
		outputModel.addObserver(ui.getOutputImagePanel().getImagePanel());
		ui.setVisible(true);
		
		inputModel.readInput(new File("C:\\Documents and Settings\\Wee\\My Documents\\jaime2.jpg"));

	}

	public static void main(String[] args) {
		new CrossStitch();
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observer#update(org.thaidev.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (arg instanceof FileOpenEvent) {
			inputModel.readInput(((FileOpenEvent)arg).getFile());
		} else if (arg instanceof FileSaveEvent) {
				outputModel.writeOutput(((FileSaveEvent)arg).getFile());
		} else if (arg instanceof DoStitchEvent) {
		
			final int step = ((DoStitchEvent)arg).getStep();
			Thread thread = new Thread(){
				public void run() {
					compute = new Compute();
					compute.addObserver(CrossStitch.this);
					compute.addObserver(ui);
					compute.compute(inputModel.getImage(), step);
				}
			};
			thread.start();
		} else if (arg instanceof StitchCompletedEvent) {
			BufferedImage result = ((StitchCompletedEvent)arg).getResultImage();
			outputModel.setImage(result);
		} else if (arg instanceof CancelEvent) {
			if (compute != null)
				compute.setStop(true);
		}
		
	}
}
