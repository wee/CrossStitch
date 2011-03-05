/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.event.ZoomEvent
 * 
 */
package org.thaidev.crossstitch.event;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * ZoomEvent
 */
public class ImageZoomEvent {
	private double zoomLevel;
	/**
	 * 
	 */
	public ImageZoomEvent(double zoomLevel) {
		setZoomLevel(zoomLevel);
	}

	/**
	 * @return
	 */
	public double getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * @param d
	 */
	public void setZoomLevel(double d) {
		zoomLevel = d;
	}

}
