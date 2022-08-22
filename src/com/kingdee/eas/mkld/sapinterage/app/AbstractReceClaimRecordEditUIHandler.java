/**
 * output package name
 */
package com.kingdee.eas.mkld.sapinterage.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractReceClaimRecordEditUIHandler extends com.kingdee.eas.framework.app.CoreBillEditUIHandler

{
	public void handleActionSentReceClaim(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSentReceClaim(request,response,context);
	}
	protected void _handleActionSentReceClaim(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionSentNoClaimMonthEnd(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSentNoClaimMonthEnd(request,response,context);
	}
	protected void _handleActionSentNoClaimMonthEnd(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionSentClaimAgain(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSentClaimAgain(request,response,context);
	}
	protected void _handleActionSentClaimAgain(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}