package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ReceiptSentRecordFactory
{
    private ReceiptSentRecordFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("314BE638") ,com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("314BE638") ,com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("314BE638"));
    }
    public static com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IReceiptSentRecord)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("314BE638"));
    }
}