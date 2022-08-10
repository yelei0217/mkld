/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.app;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class InterResultMenu extends StringEnum
{
    public static final String SUCCESS_VALUE = "S";//alias=³É¹¦
    public static final String FAIL_VALUE = "F";//alias=Ê§°Ü

    public static final InterResultMenu SUCCESS = new InterResultMenu("SUCCESS", SUCCESS_VALUE);
    public static final InterResultMenu FAIL = new InterResultMenu("FAIL", FAIL_VALUE);

    /**
     * construct function
     * @param String interResultMenu
     */
    private InterResultMenu(String name, String interResultMenu)
    {
        super(name, interResultMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static InterResultMenu getEnum(String interResultMenu)
    {
        return (InterResultMenu)getEnum(InterResultMenu.class, interResultMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(InterResultMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(InterResultMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(InterResultMenu.class);
    }
}