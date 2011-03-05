/*
 * Created on Dec 14, 2003
 *
 * org.thaidev.crossstich.event.ErrorStatusMessageEvent
 * 
 */
package org.thaidev.crossstitch.event;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * ErrorStatusMessageEvent
 */
public class ErrorStatusMessageEvent extends StatusMessageEvent {

	/**
	 * @param message
	 */
	public ErrorStatusMessageEvent(String message) {
		super(message);
	}

}
