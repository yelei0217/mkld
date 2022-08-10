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

public class SAPInterfaceLog extends CoreBillBase implements ISAPInterfaceLog
{
    public SAPInterfaceLog()
    {
        super();
        registerInterface(ISAPInterfaceLog.class, this);
    }
    public SAPInterfaceLog(Context ctx)
    {
        super(ctx);
        registerInterface(ISAPInterfaceLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D380C1E4");
    }
    private SAPInterfaceLogController getController() throws BOSException
    {
        return (SAPInterfaceLogController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection() throws BOSException
    {
        try {
            return getController().getSAPInterfaceLogCollection(getContext());
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
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSAPInterfaceLogCollection(getContext(), view);
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
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getSAPInterfaceLogCollection(getContext(), oql);
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
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSAPInterfaceLogInfo(getContext(), pk);
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
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSAPInterfaceLogInfo(getContext(), pk, selector);
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
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSAPInterfaceLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}