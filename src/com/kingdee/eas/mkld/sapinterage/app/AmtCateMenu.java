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
public class AmtCateMenu extends StringEnum
{
    public static final String LOANS_VALUE = "Loans";//alias=贷款
    public static final String MARGIN_VALUE = "Margin";//alias=保证金
    public static final String DEPOSIT_VALUE = "Deposit";//alias=押金

    public static final AmtCateMenu Loans = new AmtCateMenu("Loans", LOANS_VALUE);
    public static final AmtCateMenu Margin = new AmtCateMenu("Margin", MARGIN_VALUE);
    public static final AmtCateMenu Deposit = new AmtCateMenu("Deposit", DEPOSIT_VALUE);

    /**
     * construct function
     * @param String amtCateMenu
     */
    private AmtCateMenu(String name, String amtCateMenu)
    {
        super(name, amtCateMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static AmtCateMenu getEnum(String amtCateMenu)
    {
        return (AmtCateMenu)getEnum(AmtCateMenu.class, amtCateMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(AmtCateMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(AmtCateMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(AmtCateMenu.class);
    }
}