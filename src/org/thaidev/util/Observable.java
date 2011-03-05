/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.util.Observable
 * 
 */
package org.thaidev.util;

/**
 * @author Weerasak W. (weerasak@hotmail.com)
 *
 * Observable
 */
public interface Observable {
	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(Object arg);
}
