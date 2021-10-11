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
public abstract class AbstractUIToolEditUIHandler extends com.kingdee.eas.framework.app.CoreBillEditUIHandler

{
	public void handleActionEditBill(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionEditBill(request,response,context);
	}
	protected void _handleActionEditBill(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionAddTab(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionAddTab(request,response,context);
	}
	protected void _handleActionAddTab(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionRemoveTab(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionRemoveTab(request,response,context);
	}
	protected void _handleActionRemoveTab(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionAddLineRow(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionAddLineRow(request,response,context);
	}
	protected void _handleActionAddLineRow(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionRemoveLineRow(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionRemoveLineRow(request,response,context);
	}
	protected void _handleActionRemoveLineRow(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionrRowUp(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionrRowUp(request,response,context);
	}
	protected void _handleActionrRowUp(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionRowDown(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionRowDown(request,response,context);
	}
	protected void _handleActionRowDown(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}