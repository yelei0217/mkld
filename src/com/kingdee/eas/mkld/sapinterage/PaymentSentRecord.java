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

public class PaymentSentRecord extends DataBase implements IPaymentSentRecord
{
    public PaymentSentRecord()
    {
        super();
        registerInterface(IPaymentSentRecord.class, this);
    }
    public PaymentSentRecord(Context ctx)
    {
        super(ctx);
        registerInterface(IPaymentSentRecord.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("080ABC86");
    }
    private PaymentSentRecordController getController() throws BOSException
    {
        return (PaymentSentRecordController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PaymentSentRecordInfo getPaymentSentRecordInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPaymentSentRecordInfo(getContext(), pk);
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
    public PaymentSentRecordInfo getPaymentSentRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPaymentSentRecordInfo(getContext(), pk, selector);
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
    public PaymentSentRecordInfo getPaymentSentRecordInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPaymentSentRecordInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PaymentSentRecordCollection getPaymentSentRecordCollection() throws BOSException
    {
        try {
            return getController().getPaymentSentRecordCollection(getContext());
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
    public PaymentSentRecordCollection getPaymentSentRecordCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPaymentSentRecordCollection(getContext(), view);
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
    public PaymentSentRecordCollection getPaymentSentRecordCollection(String oql) throws BOSException
    {
        try {
            return getController().getPaymentSentRecordCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}