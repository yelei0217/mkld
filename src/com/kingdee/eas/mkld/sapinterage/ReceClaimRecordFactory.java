package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ReceClaimRecordFactory
{
    private ReceClaimRecordFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimRecord getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimRecord)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("841A1C2F") ,com.kingdee.eas.mkld.sapinterage.IReceClaimRecord.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimRecord getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimRecord)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("841A1C2F") ,com.kingdee.eas.mkld.sapinterage.IReceClaimRecord.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimRecord getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimRecord)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("841A1C2F"));
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceClaimRecord getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceClaimRecord)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("841A1C2F"));
    }
}