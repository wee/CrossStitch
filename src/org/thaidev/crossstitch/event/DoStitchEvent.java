/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstich.event.DoStitchEvent
 * 
 */
package org.thaidev.crossstitch.event;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * DoStitchEvent
 */
public class DoStitchEvent {
	private int step;
	/**
	 * 
	 */
	public DoStitchEvent(int step) {
		setStep(step);
	}

	/**
	 * @return
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @param i
	 */
	public void setStep(int i) {
		step = i;
	}

}
