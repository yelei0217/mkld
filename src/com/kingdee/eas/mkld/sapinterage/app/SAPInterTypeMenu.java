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
public class SAPInterTypeMenu extends StringEnum
{
    public static final String FICO_I012_VALUE = "FI012";//alias=收款认领结果
    public static final String FICO_I013_VALUE = "FI013";//alias=月末未认领清单
    public static final String FICO_I049_VALUE = "FI049";//alias=银行回单号传SAP

    public static final SAPInterTypeMenu FICO_I012 = new SAPInterTypeMenu("FICO_I012", FICO_I012_VALUE);
    public static final SAPInterTypeMenu FICO_I013 = new SAPInterTypeMenu("FICO_I013", FICO_I013_VALUE);
    public static final SAPInterTypeMenu FICO_I049 = new SAPInterTypeMenu("FICO_I049", FICO_I049_VALUE);

    /**
     * construct function
     * @param String sAPInterTypeMenu
     */
    private SAPInterTypeMenu(String name, String sAPInterTypeMenu)
    {
        super(name, sAPInterTypeMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static SAPInterTypeMenu getEnum(String sAPInterTypeMenu)
    {
        return (SAPInterTypeMenu)getEnum(SAPInterTypeMenu.class, sAPInterTypeMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(SAPInterTypeMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(SAPInterTypeMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(SAPInterTypeMenu.class);
    }
}