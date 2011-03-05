/*
 * Created on 28 µ.¤. 2546
 * XMLUtilTest
 * 
 * By ThaiDev.org
 * Please see LICENSE.TXT for license agreement information.

 */
package org.thaidev.util;

import junit.framework.TestCase;

/**
 * @author Weerasak W (weerasak@hotmail.com)
 * @version 1.0
 *
 */
public class XMLUtilTest extends TestCase {
    public void testStrToXML() {
		String test = "0 < 1 && 5 > 3";
		String expected = "0 &lt; 1 &amp;&amp; 5 &gt; 3"; 
        String result = XMLUtil.strToXML(test);
        assertEquals(expected, result);
    }

}
