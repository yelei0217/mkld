/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.client;

import java.awt.event.*;
import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.mkld.sapinterage.app.ClaimStatusMenu;
import com.kingdee.eas.mkld.sapinterage.app.ClaimTypeMenu;
import com.kingdee.eas.mkld.sapinterage.app.SendStatusMenu;

/**
 * output class name
 */
public class ClaimAccountEditUI extends AbstractClaimAccountEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(ClaimAccountEditUI.class);
    
    /**
     * output class constructor
     */
    public ClaimAccountEditUI() throws Exception
    {
        super(); 
        initF7BankAccount();
     }
  
    
	private void initF7BankAccount() {
		prmtacccount.setData(null); 
		prmtacccount.setEntityViewInfo(null);
		EntityViewInfo evi = new EntityViewInfo();
		 FilterInfo filterInfo = new FilterInfo();
		if(prmtcompany.getValue() !=null ){ 
 			CompanyOrgUnitInfo company = (CompanyOrgUnitInfo) prmtcompany.getValue();
 			if(company !=null && company.getId() !=null && !"".equals(company.getId().toString()))
 				filterInfo.getFilterItems().add(new FilterItemInfo("company.id",company.getId().toString(),CompareType.EQUALS));//认领类型：本月认领
 		}
		filterInfo.getFilterItems().add(new FilterItemInfo("isClosed", new Integer(0)));
		filterInfo.getFilterItems().add(new FilterItemInfo("isSetBankInterface", new Integer(1)));
		evi.setFilter(filterInfo);
		prmtacccount.setDisplayFormat("$number$ $name$");
		prmtacccount.setCommitFormat("$number$");
		prmtacccount.setEditFormat("$number$");
		//this.prmtacccount.setEnabledMultiSelection(true);
		prmtacccount.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7AccountBankQuery");
		prmtacccount.setEntityViewInfo(evi); 
		prmtacccount.setRefresh(true);
	}		
			
    @Override
    public void prmtcompany_Changed() throws Exception {
     	super.prmtcompany_Changed();
     	initF7BankAccount();
     }
    
    
    /**
     * output loadFields method
     */
    public void loadFields()
    {
        super.loadFields();
    }
    

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.mkld.sapinterage.ClaimAccountFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.mkld.sapinterage.ClaimAccountInfo objectValue = new com.kingdee.eas.mkld.sapinterage.ClaimAccountInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}