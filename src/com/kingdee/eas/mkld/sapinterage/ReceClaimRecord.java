package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.mkld.sapinterage.app.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBillBase;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class ReceClaimRecord extends CoreBillBase implements IReceClaimRecord
{
    public ReceClaimRecord()
    {
        super();
        registerInterface(IReceClaimRecord.class, this);
    }
    public ReceClaimRecord(Context ctx)
    {
        super(ctx);
        registerInterface(IReceClaimRecord.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("841A1C2F");
    }
    private ReceClaimRecordController getController() throws BOSException
    {
        return (ReceClaimRecordController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public ReceClaimRecordCollection getReceClaimRecordCollection() throws BOSException
    {
        try {
            return getController().getReceClaimRecordCollection(getContext());
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
    public ReceClaimRecordCollection getReceClaimRecordCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getReceClaimRecordCollection(getContext(), view);
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
    public ReceClaimRecordCollection getReceClaimRecordCollection(String oql) throws BOSException
    {
        try {
            return getController().getReceClaimRecordCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public ReceClaimRecordInfo getReceClaimRecordInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getReceClaimRecordInfo(getContext(), pk);
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
    public ReceClaimRecordInfo getReceClaimRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getReceClaimRecordInfo(getContext(), pk, selector);
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
    public ReceClaimRecordInfo getReceClaimRecordInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getReceClaimRecordInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *发送收款认领结果-User defined method
     *@param model model
     */
    public void sentReceClaim(ReceClaimRecordInfo model) throws BOSException
    {
        try {
            getController().sentReceClaim(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *发送月末未认领-User defined method
     *@param model model
     */
    public void sentNoClaimMonthEnd(ReceClaimRecordInfo model) throws BOSException
    {
        try {
            getController().sentNoClaimMonthEnd(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *认领二次发送-User defined method
     *@param model model
     */
    public void sentClaimAgain(ReceClaimRecordInfo model) throws BOSException
    {
        try {
            getController().sentClaimAgain(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}