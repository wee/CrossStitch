/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.util.Observer
 * 
 */
package org.thaidev.util;

/**
 * @author Weerasak W. (weerasak@hotmail.com)
 *
 * Observer
 */
public interface Observer {
	public void update(Observable o, Object arg);
}
