package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PaymentSentRecordFactory
{
    private PaymentSentRecordFactory()
    {
    }
    public static com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("080ABC86") ,com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord.class);
    }
    
    public static com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("080ABC86") ,com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord.class, objectCtx);
    }
    public static com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("080ABC86"));
    }
    public static com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.mkld.sapinterage.IPaymentSentRecord)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("080ABC86"));
    }
}