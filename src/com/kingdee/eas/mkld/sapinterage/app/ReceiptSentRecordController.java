package com.kingdee.eas.mkld.sapinterage.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.eas.mkld.sapinterage.ReceiptSentRecordCollection;
import com.kingdee.eas.mkld.sapinterage.ReceiptSentRecordInfo;
import com.kingdee.eas.framework.app.DataBaseController;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ReceiptSentRecordController extends DataBaseController
{
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(Context ctx) throws BOSException, RemoteException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(Context ctx, String oql) throws BOSException, RemoteException;
}