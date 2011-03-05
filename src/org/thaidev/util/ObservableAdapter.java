/*
 * Created on Dec 13, 2003
 *
 * org.thaidev.util.ObservableAdapter
 * 
 */
package org.thaidev.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Weerasak W. (weerasak@hotmail.com)
 *
 * ObservableAdapter
 */
public class ObservableAdapter implements Observable {
	private List observers = new ArrayList();
	
	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#addObserver(org.thaidev.util.Observer)
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#removeObserver(org.thaidev.util.Observer)
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/* (non-Javadoc)
	 * @see org.thaidev.util.Observable#notifyObservers(java.lang.Object)
	 */
	public void notifyObservers(Object arg) {
		for (Iterator iter = observers.iterator(); iter.hasNext(); ) {
			Observer o = (Observer)iter.next();
			o.update(this, arg);
		}
	}
}
