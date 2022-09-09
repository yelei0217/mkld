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
public class ReceiptSourceTypeMenu extends StringEnum
{
    public static final String RECE_VALUE = "1";//alias=收款单
    public static final String PAYMENT_VALUE = "2";//alias=付款单

    public static final ReceiptSourceTypeMenu Rece = new ReceiptSourceTypeMenu("Rece", RECE_VALUE);
    public static final ReceiptSourceTypeMenu Payment = new ReceiptSourceTypeMenu("Payment", PAYMENT_VALUE);

    /**
     * construct function
     * @param String receiptSourceTypeMenu
     */
    private ReceiptSourceTypeMenu(String name, String receiptSourceTypeMenu)
    {
        super(name, receiptSourceTypeMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static ReceiptSourceTypeMenu getEnum(String receiptSourceTypeMenu)
    {
        return (ReceiptSourceTypeMenu)getEnum(ReceiptSourceTypeMenu.class, receiptSourceTypeMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(ReceiptSourceTypeMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(ReceiptSourceTypeMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(ReceiptSourceTypeMenu.class);
    }
}