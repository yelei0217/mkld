package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ReceClaimSentFacadeFactory
{
    private ReceClaimSentFacadeFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9CA6EB30") ,com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9CA6EB30") ,com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9CA6EB30"));
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimSentFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9CA6EB30"));
    }
}