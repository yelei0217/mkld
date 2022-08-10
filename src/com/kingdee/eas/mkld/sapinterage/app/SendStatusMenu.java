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
public class SendStatusMenu extends StringEnum
{
    public static final String SENTS_VALUE = "1";//alias=已发送接收成功
    public static final String UNSENT_VALUE = "0";//alias=未发送
    public static final String SENTF_VALUE = "2";//alias=已发送接收失败

    public static final SendStatusMenu SentS = new SendStatusMenu("SentS", SENTS_VALUE);
    public static final SendStatusMenu UnSent = new SendStatusMenu("UnSent", UNSENT_VALUE);
    public static final SendStatusMenu SentF = new SendStatusMenu("SentF", SENTF_VALUE);

    /**
     * construct function
     * @param String sendStatusMenu
     */
    private SendStatusMenu(String name, String sendStatusMenu)
    {
        super(name, sendStatusMenu);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static SendStatusMenu getEnum(String sendStatusMenu)
    {
        return (SendStatusMenu)getEnum(SendStatusMenu.class, sendStatusMenu);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(SendStatusMenu.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(SendStatusMenu.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(SendStatusMenu.class);
    }
}