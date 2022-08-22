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

public class ReceClaimSentFacade extends AbstractBizCtrl implements IReceClaimSentFacade
{
    public ReceClaimSentFacade()
    {
        super();
        registerInterface(IReceClaimSentFacade.class, this);
    }
    public ReceClaimSentFacade(Context ctx)
    {
        super(ctx);
        registerInterface(IReceClaimSentFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9CA6EB30");
    }
    private ReceClaimSentFacadeController getController() throws BOSException
    {
        return (ReceClaimSentFacadeController)getBizController();
    }
    /**
     *发送收款认领结果-User defined method
     *@return
     */
    public String sentReceClaim() throws BOSException
    {
        try {
            return getController().sentReceClaim(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *发送月末未认领-User defined method
     *@return
     */
    public String sentReceNoClaim() throws BOSException
    {
        try {
            return getController().sentReceNoClaim(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *二次发送认领结果-User defined method
     *@return
     */
    public String sentClaimAgain() throws BOSException
    {
        try {
            return getController().sentClaimAgain(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}