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
    public static final String BANKPAY_VALUE = "BANKPAY";//alias=银行付款同步OA
    public static final String CUSTOMER_VALUE = "CUSTOMER";//alias=客户同步
    public static final String CUSTOMER_DMS_VALUE = "6";//alias=客户名称同步至DMS
    public static final String RECEIPT_GEN_VALUE = "7";//alias=回单记录-生成
    public static final String RECEIPT_SENT_VALUE = "8";//alias=回单记录-同步至SAP
    public static final String RECE_STA_U_VALUE = "9";//alias=认领记录单状态修改

    public static final SAPInterTypeMenu FICO_I012 = new SAPInterTypeMenu("FICO_I012", FICO_I012_VALUE);
    public static final SAPInterTypeMenu FICO_I013 = new SAPInterTypeMenu("FICO_I013", FICO_I013_VALUE);
    public static final SAPInterTypeMenu FICO_I049 = new SAPInterTypeMenu("FICO_I049", FICO_I049_VALUE);
    public static final SAPInterTypeMenu BANKPAY = new SAPInterTypeMenu("BANKPAY", BANKPAY_VALUE);
    public static final SAPInterTypeMenu CUSTOMER = new SAPInterTypeMenu("CUSTOMER", CUSTOMER_VALUE);
    public static final SAPInterTypeMenu CUSTOMER_DMS = new SAPInterTypeMenu("CUSTOMER_DMS", CUSTOMER_DMS_VALUE);
    public static final SAPInterTypeMenu Receipt_Gen = new SAPInterTypeMenu("Receipt_Gen", RECEIPT_GEN_VALUE);
    public static final SAPInterTypeMenu Receipt_Sent = new SAPInterTypeMenu("Receipt_Sent", RECEIPT_SENT_VALUE);
    public static final SAPInterTypeMenu Rece_Sta_U = new SAPInterTypeMenu("Rece_Sta_U", RECE_STA_U_VALUE);

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