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

public interface ISAPInterfaceLog extends ICoreBillBase
{
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection() throws BOSException;
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection(EntityViewInfo view) throws BOSException;
    public SAPInterfaceLogCollection getSAPInterfaceLogCollection(String oql) throws BOSException;
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SAPInterfaceLogInfo getSAPInterfaceLogInfo(String oql) throws BOSException, EASBizException;
}