package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncDBCusFacadeFactory
{
    private SyncDBCusFacadeFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("DB9B2779") ,com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("DB9B2779") ,com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("DB9B2779"));
    }
    public static com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISyncDBCusFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("DB9B2779"));
    }
}