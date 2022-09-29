package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ClaimAccountFactory
{
    private ClaimAccountFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.IClaimAccount getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IClaimAccount)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("738C4C1A") ,com.kingdee.eas.mkld.sapinterage.IClaimAccount.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.IClaimAccount getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IClaimAccount)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("738C4C1A") ,com.kingdee.eas.mkld.sapinterage.IClaimAccount.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.IClaimAccount getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IClaimAccount)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("738C4C1A"));
    }
    public static com.kingdee.eas.mkld.sapinterage.IClaimAccount getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IClaimAccount)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("738C4C1A"));
    }
}