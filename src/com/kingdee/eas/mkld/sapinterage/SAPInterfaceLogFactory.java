package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SAPInterfaceLogFactory
{
    private SAPInterfaceLogFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("D380C1E4") ,com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("D380C1E4") ,com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("D380C1E4"));
    }
    public static com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.ISAPInterfaceLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("D380C1E4"));
    }
}