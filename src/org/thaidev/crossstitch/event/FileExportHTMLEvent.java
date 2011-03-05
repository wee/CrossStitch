/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.crossstich.event.FileExportHTMLEvent
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
 * FileExportHTMLEvent
 */
public class FileExportHTMLEvent {
	private File file;
	/**
	 * 
	 */
	public FileExportHTMLEvent(File file) {
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
