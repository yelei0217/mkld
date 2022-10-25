package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public interface IReceClaimSentFacade extends IBizCtrl
{
    public String sentReceClaim() throws BOSException;
    public String sentReceNoClaim() throws BOSException;
    public String sentClaimAgain() throws BOSException;
    public String genReceiptRecord() throws BOSException;
    public String SentReceiptRecord() throws BOSException;
    public String sentCustomer2DMS() throws BOSException;
    public String updateClaimStaByCusName() throws BOSException;
    public void genPaymentRecord() throws BOSException;
    public void SentPaymentRecord() throws BOSException;
}