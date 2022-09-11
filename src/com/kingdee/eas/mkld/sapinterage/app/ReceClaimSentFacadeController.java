package com.kingdee.eas.mkld.sapinterage.app;

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

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ReceClaimSentFacadeController extends BizController
{
    public String sentReceClaim(Context ctx) throws BOSException, RemoteException;
    public String sentReceNoClaim(Context ctx) throws BOSException, RemoteException;
    public String sentClaimAgain(Context ctx) throws BOSException, RemoteException;
    public String genReceiptRecord(Context ctx) throws BOSException, RemoteException;
    public String SentReceiptRecord(Context ctx) throws BOSException, RemoteException;
    public String sentCustomer2DMS(Context ctx) throws BOSException, RemoteException;
    public String updateClaimStaByCusName(Context ctx) throws BOSException, RemoteException;
}