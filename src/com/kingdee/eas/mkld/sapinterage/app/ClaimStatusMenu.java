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
public class ClaimStatusMenu extends StringEnum
{
    public static final String YES_VALUE = "1";//alias=已认领
    public static final String NO_VALUE = "0";//alias=未认领

    public static final ClaimStatusMenu Yes = new ClaimStatusMenu("Yes", YES_VALUE);
    public static final ClaimStatusMenu No = new ClaimStatusMenu("No", NO_VALUE);

    /**
     * construct function
     * @param String claimStatusMenu
     */
    private ClaimStatusMenu(String name, String claimStatusMenu)
    {
        super(name, claimStatusMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static ClaimStatusMenu getEnum(String claimStatusMenu)
    {
        return (ClaimStatusMenu)getEnum(ClaimStatusMenu.class, claimStatusMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(ClaimStatusMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(ClaimStatusMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(ClaimStatusMenu.class);
    }
}