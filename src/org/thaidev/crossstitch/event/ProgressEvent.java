/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstich.event.ProgressEvent
 * 
 */
package org.thaidev.crossstitch.event;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * ProgressEvent
 */
public class ProgressEvent {
	private int percentValue;
	/**
	 * 
	 */
	public ProgressEvent(int percent) {
		setPercentValue(percent);
	}

	/**
	 * @return
	 */
	public int getPercentValue() {
		return percentValue;
	}

	/**
	 * @param i
	 */
	public void setPercentValue(int i) {
		percentValue = i;
	}

}
