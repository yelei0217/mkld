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
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IReceClaimRecord extends ICoreBillBase
{
    public ReceClaimRecordCollection getReceClaimRecordCollection() throws BOSException;
    public ReceClaimRecordCollection getReceClaimRecordCollection(EntityViewInfo view) throws BOSException;
    public ReceClaimRecordCollection getReceClaimRecordCollection(String oql) throws BOSException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(IObjectPK pk) throws BOSException, EASBizException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public ReceClaimRecordInfo getReceClaimRecordInfo(String oql) throws BOSException, EASBizException;
    public void sentReceClaim(ReceClaimRecordInfo model) throws BOSException;
    public void sentNoClaimMonthEnd(ReceClaimRecordInfo model) throws BOSException;
    public void sentClaimAgain(ReceClaimRecordInfo model) throws BOSException;
}