/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.event.StatusMessageEvent
 * 
 */
package org.thaidev.crossstitch.event;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * StatusMessageEvent
 */
public class StatusMessageEvent {
	private String message;
	/**
	 * 
	 */
	public StatusMessageEvent(String message) {
		setMessage(message);
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
	}

}
