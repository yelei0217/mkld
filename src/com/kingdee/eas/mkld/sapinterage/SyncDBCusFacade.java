package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.mkld.sapinterage.app.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public class SyncDBCusFacade extends AbstractBizCtrl implements ISyncDBCusFacade
{
    public SyncDBCusFacade()
    {
        super();
        registerInterface(ISyncDBCusFacade.class, this);
    }
    public SyncDBCusFacade(Context ctx)
    {
        super(ctx);
        registerInterface(ISyncDBCusFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("DB9B2779");
    }
    private SyncDBCusFacadeController getController() throws BOSException
    {
        return (SyncDBCusFacadeController)getBizController();
    }
    /**
     *SyncCustomer-User defined method
     *@param data data
     */
    public void SyncCustomer(String data) throws BOSException
    {
        try {
            getController().SyncCustomer(getContext(), data);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}