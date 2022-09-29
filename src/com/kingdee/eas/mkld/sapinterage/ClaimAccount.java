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

public class ClaimAccount extends DataBase implements IClaimAccount
{
    public ClaimAccount()
    {
        super();
        registerInterface(IClaimAccount.class, this);
    }
    public ClaimAccount(Context ctx)
    {
        super(ctx);
        registerInterface(IClaimAccount.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("738C4C1A");
    }
    private ClaimAccountController getController() throws BOSException
    {
        return (ClaimAccountController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public ClaimAccountInfo getClaimAccountInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getClaimAccountInfo(getContext(), pk);
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
    public ClaimAccountInfo getClaimAccountInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getClaimAccountInfo(getContext(), pk, selector);
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
    public ClaimAccountInfo getClaimAccountInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getClaimAccountInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public ClaimAccountCollection getClaimAccountCollection() throws BOSException
    {
        try {
            return getController().getClaimAccountCollection(getContext());
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
    public ClaimAccountCollection getClaimAccountCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getClaimAccountCollection(getContext(), view);
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
    public ClaimAccountCollection getClaimAccountCollection(String oql) throws BOSException
    {
        try {
            return getController().getClaimAccountCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}