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
public class ClaimTypeMenu extends StringEnum
{
    public static final String CURRMONTH_VALUE = "A";//alias=当月认领
    public static final String NEXTMONTH_VALUE = "B";//alias=次月认领

    public static final ClaimTypeMenu CurrMonth = new ClaimTypeMenu("CurrMonth", CURRMONTH_VALUE);
    public static final ClaimTypeMenu NextMonth = new ClaimTypeMenu("NextMonth", NEXTMONTH_VALUE);

    /**
     * construct function
     * @param String claimTypeMenu
     */
    private ClaimTypeMenu(String name, String claimTypeMenu)
    {
        super(name, claimTypeMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static ClaimTypeMenu getEnum(String claimTypeMenu)
    {
        return (ClaimTypeMenu)getEnum(ClaimTypeMenu.class, claimTypeMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(ClaimTypeMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(ClaimTypeMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(ClaimTypeMenu.class);
    }
}