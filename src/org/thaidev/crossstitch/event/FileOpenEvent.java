/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.event.FileOpenEvent
 * 
 */
package org.thaidev.crossstitch.event;

import java.io.File;

/**
 * LGPL Open source copyright 
 * 
 * @author Mr Sweetcorn mcgyver@writeme.com
 * @version 2.0 20031214
 *
 * FileOpenEvent
 */
public class FileOpenEvent {
	private File file;
	/**
	 * 
	 */
	public FileOpenEvent(File file) {
		setFile(file);
	}
	/**
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
