/*
 * Created on Oct 27, 2003
 *
 */
package org.thaidev.util.examples;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * <p>Display all constants used in UIManager, i.e., Label.font.
 * It is very useful for finding names of the keys or checking default 
 * values or replacing them with new values.</p>
 * 
 * @author Weerasak Witthawaskul (weerasak@hotmail.com)
 * @version 1.0
 * @version 1.1 Sort output by key name
 */
public class UIManagerConstantsDisplay {
	List result = new ArrayList();
	
	public UIManagerConstantsDisplay() {
		UIDefaults uid = UIManager.getDefaults();
		for (Enumeration e = uid.keys(); e.hasMoreElements(); ) {
			Object key = e.nextElement();
			Object value = uid.get(key);
			String item = key + "=" + value;
			result.add(item);
		}
		Collections.sort(result);
		for (Iterator iter = result.iterator(); iter.hasNext(); ) {
			System.out.println(iter.next());
		}
	}

	public static void main(String[] args) {
		new UIManagerConstantsDisplay();
	}
}
