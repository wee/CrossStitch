/*
 * Created on 28 µ.¤. 2546 
 * XMLUtil
 * 
 * By ThaiDev.org
 * Please see LICENSE.TXT for license agreement information.
 */
package org.thaidev.util;

/**
 * @author Weerasak W (weerasak@hotmail.com)
 * @version 1.0
 *  
 */
public class XMLUtil {
    /**
     * <p>strToXML converts a string into an XML encoding style
     * string.  It expands <, >, & and " into &lt; &gt; &amp; 
     * and &quot; respectively.</p>
     * @param s input string
     * @return XML encoding style output string
     */
    public static String strToXML(String s) {
        StringBuffer str = new StringBuffer();
        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '<' :
                    str.append("&lt;");
                    break;
                case '>' :
                    str.append("&gt;");
                    break;
                case '&' :
                    str.append("&amp;");
                    break;
                case '"' :
                    str.append("&quot;");
                    break;
                default :
                    str.append(ch);
            }
        }
        return str.toString();
    }

}
