package com.kingdee.eas.mkld.sapinterage;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IPaymentSentRecord extends IDataBase
{
    public PaymentSentRecordInfo getPaymentSentRecordInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PaymentSentRecordInfo getPaymentSentRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PaymentSentRecordInfo getPaymentSentRecordInfo(String oql) throws BOSException, EASBizException;
    public PaymentSentRecordCollection getPaymentSentRecordCollection() throws BOSException;
    public PaymentSentRecordCollection getPaymentSentRecordCollection(EntityViewInfo view) throws BOSException;
    public PaymentSentRecordCollection getPaymentSentRecordCollection(String oql) throws BOSException;
}