/**
 * output package name
 */
package com.kingdee.eas.custom.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractUIToolListUIHandler extends com.kingdee.eas.framework.app.CoreBillListUIHandler

{
	public void handleActionTDPrint(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionTDPrint(request,response,context);
	}
	protected void _handleActionTDPrint(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionTDPrintPreview(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionTDPrintPreview(request,response,context);
	}
	protected void _handleActionTDPrintPreview(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionClearCatch(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionClearCatch(request,response,context);
	}
	protected void _handleActionClearCatch(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionResutPage(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionResutPage(request,response,context);
	}
	protected void _handleActionResutPage(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}