package com.kingdee.eas.mkld.sapinterage.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordCollection;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.mkld.sapinterage.ReceClaimRecordInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ReceClaimRecordController extends CoreBillBaseController
{
    public ReceClaimRecordCollection getReceClaimRecordCollection(Context ctx) throws BOSException, RemoteException;
    public ReceClaimRecordCollection getReceClaimRecordCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ReceClaimRecordCollection getReceClaimRecordCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void sentReceClaim(Context ctx, ReceClaimRecordInfo model) throws BOSException, RemoteException;
    public void sentNoClaimMonthEnd(Context ctx, ReceClaimRecordInfo model) throws BOSException, RemoteException;
}