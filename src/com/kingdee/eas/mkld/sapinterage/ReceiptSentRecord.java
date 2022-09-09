package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.mkld.sapinterage.app.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.DataBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class ReceiptSentRecord extends DataBase implements IReceiptSentRecord
{
    public ReceiptSentRecord()
    {
        super();
        registerInterface(IReceiptSentRecord.class, this);
    }
    public ReceiptSentRecord(Context ctx)
    {
        super(ctx);
        registerInterface(IReceiptSentRecord.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("314BE638");
    }
    private ReceiptSentRecordController getController() throws BOSException
    {
        return (ReceiptSentRecordController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getReceiptSentRecordInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getReceiptSentRecordInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getReceiptSentRecordInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public ReceiptSentRecordCollection getReceiptSentRecordCollection() throws BOSException
    {
        try {
            return getController().getReceiptSentRecordCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getReceiptSentRecordCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(String oql) throws BOSException
    {
        try {
            return getController().getReceiptSentRecordCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}