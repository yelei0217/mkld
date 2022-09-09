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

public interface IReceiptSentRecord extends IDataBase
{
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(IObjectPK pk) throws BOSException, EASBizException;
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public ReceiptSentRecordInfo getReceiptSentRecordInfo(String oql) throws BOSException, EASBizException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection() throws BOSException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(EntityViewInfo view) throws BOSException;
    public ReceiptSentRecordCollection getReceiptSentRecordCollection(String oql) throws BOSException;
}