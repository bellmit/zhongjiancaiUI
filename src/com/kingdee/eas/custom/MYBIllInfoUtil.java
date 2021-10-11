package com.kingdee.eas.custom;

import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.eas.util.app.*;
import com.kingdee.bos.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.webframework.workflow.WfWafUtil;
import com.kingdee.bos.workflow.metas.ActInstCollection;
import com.kingdee.bos.workflow.service.IWfDefineService;
import com.kingdee.bos.workflow.service.WfDefineService;
import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.store.ActivityStorer;
import com.kingdee.bos.workflow.store.util.StoreUtils;
import com.kingdee.bos.workflow.store.util.WfBOSObjectTypeUtil;
import com.kingdee.bos.workflow.transaction.WfTxHelper;
import com.kingdee.bos.workflow.transaction.WfTxInvocationDesc;
import com.kingdee.bos.workflow.util.MethodDesc;
import com.kingdee.bos.workflow.util.WfDbUtil;
import com.kingdee.bos.workflow.web.ActivityPropertyController;
import com.kingdee.bos.workflow.biz.trans.*;
import com.kingdee.bos.workflow.define.ActivityDef;
import com.kingdee.bos.workflow.enactment.WfEngine;
import com.kingdee.bos.workflow.*;
import com.kingdee.jdbc.rowset.*;
import com.kingdee.shr.base.syssetting.exception.ShrWebBizException;
import com.kingdee.shr.cmpdesign.BatchAdjustBillEntryCollection;
import com.kingdee.shr.cmpdesign.BatchAdjustBillEntryFactory;
import com.kingdee.shr.cmpdesign.BatchAdjustBillFactory;
import com.kingdee.shr.cmpdesign.BatchAdjustBillInfo;
import com.kingdee.util.enums.DynamicEnum;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.metadata.bo.*;
import com.kingdee.eas.common.*;
import com.alibaba.fastjson.*;
import com.kingdee.eas.fm.common.util.CoreBaseInfoArrayUtil;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.framework.util.EntityUtility;
import com.kingdee.eas.hr.affair.FlucInBizBillEntryCollection;
import com.kingdee.eas.hr.affair.FlucInBizBillEntryFactory;
import com.kingdee.eas.hr.affair.FluctuationBizBillEntryCollection;
import com.kingdee.eas.hr.affair.FluctuationBizBillEntryFactory;
import com.kingdee.eas.hr.base.app.util.CoreBaseObjectUtil;
import com.kingdee.bos.dao.*;
import com.kingdee.eas.moya.common.*;
import java.sql.*;

import com.kingdee.eas.base.attachment.ftp.*;
import java.io.*;
import com.kingdee.bos.metadata.query.util.*;
import com.kingdee.bos.metadata.data.DataTableInfo;
import com.kingdee.bos.metadata.entity.*;
import java.text.*;
import java.util.*;

import com.kingdee.bos.metadata.*;
import com.kingdee.bos.dao.query.*;

public class MYBIllInfoUtil
{
	
	protected final static IORMappingDAO getDAO(BOSObjectType bosObjectType,
			Connection cn,Context ctx) {
		if (bosObjectType == null) {
			throw new IllegalArgumentException("bosObjectType can not be null!");
		}

		return ORMappingDAO.getInstance(bosObjectType,  ctx, cn);
	}
	
	
    public static JSONObject getBillMap(final Context ctx, final String billid, final String sourceID) throws BOSException, EASBizException, SQLException {
       
    	 System.out.println("getBillMap-------sql");
    	long start = System.currentTimeMillis();
        final long startSqlTime = System.currentTimeMillis();
        String procInstId = "";
        String actDefId = "";
        
        /*//查看当前节点    查看当前节点状态     是审批状态的
        
        String  sqlAss = " select distinct act.FACTDEFID , act.FPROCINSTID "+  
		" FROM t_wfr_actInst ACT "+ //活动实例	
		" INNER JOIN t_WFR_ProcinstRef pr ON act.FPROCINSTID = pr.FPROCINSTID "+  //流程实例引用关系
		" INNER JOIN T_WFR_ProcInst pro ON pro.FPROCINSTID = act.FPROCINSTID "+  //流程实例
		" INNER JOIN T_WFR_Assign detail ON detail.FACTDEFID = act.FACTDEFID AND detail.FPROCINSTID = act.FPROCINSTID	 "+
		" where  detail.FASSIGNID  ='"+sourceID+"' and  detail.FSTATE != 0 ";
        IRowSet rowSet = DbUtil.executeQuery(ctx, sqlAss);
        if(rowSet.size() >0 ){
        	if (rowSet.next()) {
        		actDefId = rowSet.getString("FACTDEFID");
        		procInstId  = rowSet.getString("FPROCINSTID");
                
            }
            
        }*//*else{
        	sqlAss = " select distinct act.FACTDEFID , act.FPROCINSTID "+  
    		" FROM t_wfr_actInst ACT "+ //活动实例	
    		" INNER JOIN t_WFR_ProcinstRef pr ON act.FPROCINSTID = pr.FPROCINSTID "+  //流程实例引用关系
    		" INNER JOIN T_WFR_ProcInst pro ON pro.FPROCINSTID = act.FPROCINSTID "+  //流程实例
    		" INNER JOIN T_WFR_AssignDetail detail ON detail.FACTDEFID = act.FACTDEFID AND detail.FPROCINSTID = act.FPROCINSTID	 "+
    		" where  detail.FASSIGNID  ='"+sourceID+"'";
            rowSet = DbUtil.executeQuery(ctx, sqlAss);
            if (rowSet.next()) {
            	actDefId = rowSet.getString("FACTDEFID");
            	procInstId = rowSet.getString("FPROCINSTID");
                
            }
        }*/
        String uiStatus ="view"; 
		String actDigest = "";
		String webStr  = "";
        /*if( !procInstId.equals("") && !actDefId.equals("")){
        	Locale locale = ctx.getLocale(); 
        	EnactmentService enactmentService = new EnactmentService();  
			Connection cn = null;
			ActInstCollection localActInstCollection1 = null;
			String cndtn = ""; 
			if ((actDefId != null) && (actDefId.length() > 0)) {
				if (cndtn.length() != 0) { cndtn = cndtn + " and "; }
				cndtn = cndtn + "(actDefID = '" + actDefId + "')";
			} 
			if ((procInstId != null) && (procInstId.length() > 0)) {
				if (cndtn.length() != 0) { cndtn = cndtn + " and "; } 
				cndtn = cndtn + "(procInstID = '" + procInstId + "')";
			} 
			String oql = "";
			if (cndtn.length() > 0) {
				oql = "WHERE " + cndtn;
			} 
			cn = WfDbUtil.getConnection(ctx);
			IORMappingDAO ormdao = getDAO(WfBOSObjectTypeUtil.ACTIVITYINSTBOT,cn,ctx);

			IObjectCollection oc = ormdao.getCollection(oql);
			ActInstCollection aic = (ActInstCollection) oc;
			if ((aic != null) && (aic.size() > 0)) {
				localActInstCollection1 = aic; 
			}
			ActivityInstInfo[] actInfos = null;
			if(localActInstCollection1 != null){
				actInfos =  StoreUtils.toActivityInstInfoArray(localActInstCollection1.toArray());    
			} 
			if ((localActInstCollection1 != null) && (localActInstCollection1.size() > 0)) {
				ActivityInstInfo[] actInfos =  StoreUtils.toActivityInstInfoArray(localActInstCollection1.toArray());
			}  
			//String actInstId = "4bbb37b1-70c8-4490-9b20-d6bca700e687WFATINST"; 
			
			if (( null!=  actInfos) && (actInfos.length > 0)){ 
				Object obj = WfDefineService.getService(ctx);
				Class classCaller = IWfDefineService.class;
				String methodName = "getActDefByActInstInfo";
				Class[] parameterTypes = { ActivityInstInfo.class };
				Object[] args = { actInfos[0] };
				MethodDesc methodDesc = new MethodDesc(classCaller, methodName,parameterTypes);

				WfTxInvocationDesc invocationDesc = new WfTxInvocationDesc(methodDesc,obj, args);

				Object result = WfTxHelper.invokeSupports(ctx, invocationDesc);
				actDigest = ((ActivityDef)result).getActDigest(locale);
				ActivityDef actDef = enactmentService.getActDefByActInstInfo(actInfos[0]);   
				actDigest = actDef.getActDigest(locale); 
				System.out.println(actDigest);
				String[] strArr = actDigest.split("\r\n");
				String	lastStr = strArr[strArr.length-1];
				
				String[] exStr =  lastStr.split("：");
				if(exStr.length >1 && exStr[0].equals("扩展属性")){
					String exValue = exStr[exStr.length-1];
					String[] exValueArr = exValue.split(";"); 
					for(String s : exValueArr){
						if(s.indexOf("customUIStatus") >= 0){
							uiStatus = s.split("=")[1];
						}else if(s.indexOf("WebBillApproveUrl") >= 0){
							String[] webUrlStr  = s.split("\\.");
							if(webUrlStr.length > 2 ){
								webStr = webUrlStr[webUrlStr.length-2]+"."+webUrlStr[webUrlStr.length-1];
							} 
						} 
					}
				} 
			} 
        }*/
		
		
        System.out.println("--" +uiStatus+"-------:"+webStr);
        //uiStatus = "EDIT";
        //webStr = "EmpHireBizBil_zhishu.form";
        final long endSql = System.currentTimeMillis();
        System.out.println("\u83b7\u53d6\u6570\u636e\u7528\u65f6\uff1a" + (endSql - startSqlTime) / 1000L+"-------sql");
        
        boolean isAudit = false;
        final long startTime = System.currentTimeMillis();
        final JSONObject json = new JSONObject();
        final ObjectUuidPK pk = new ObjectUuidPK(billid);
        if (sourceID != null && !"".equals(sourceID)) {
            final Map mapWF = new HashMap();
            try {
                IEnactmentService server = null;
                if (ctx != null) {
                    server = EnactmentServiceFactory.createEnactService(ctx);
                }
                else {
                    server = EnactmentServiceFactory.createRemoteEnactService();
                }
                
                //final String sql = "select fassignid from t_wfr_assigndetail where FASSIGNID ='" + sourceID + "'";
                final String sql = "select fassignid from t_wfr_assign where FASSIGNID ='" + sourceID + "' and FSTATE in (1 ,2 ,32) ";
                final IRowSet rs = DbUtil.executeQuery(ctx, sql);
                isAudit =!rs.next();
                json.put("isAudit", isAudit);///存在说明没有审核 
                
                //setManualDecisionItems(json, "0;同意;同意;:1;不同意;不同意;");
                 
                final EASWfServiceData data = server.getEASWfServiceData(new String[] { sourceID }, false);
                final AssignmentInfo assInfo = data.getAssignmentInfo();
                final Map extendedAttrFromActDef = data.getExtendedAttribute();
                final Map map1 = server.getAssignmentArgument(sourceID, true);
                mapWF.put("MANUALDECISION_ITEMS", new StringBuilder().append(map1.get("MANUALDECISION_ITEMS")).toString());
                mapWF.put("MANUALDECISION_NAME", new StringBuilder().append(map1.get("MANUALDECISION_NAME")).toString());
                mapWF.put("ManualDecisionUIFunction", assInfo.getBizFunction());
                final Object manualDecisionItems_l2 = extendedAttrFromActDef.get("manualDecisionItems_l2");
                setManualDecisionItems(json, (manualDecisionItems_l2 == null) ? null : manualDecisionItems_l2.toString()); 
                mapWF.put("IsDynamicSign", extendedAttrFromActDef.get("IsDynamicSign"));
                json.put("IsDynamicSign", extendedAttrFromActDef.get("IsDynamicSign"));
                mapWF.put("alwaysSetNextPersons", extendedAttrFromActDef.get("alwaysSetNextPersons")); 
                
                String nodeName = assInfo.getActDefName(ctx.getLocale());
                if(extendedAttrFromActDef.get("customUIStatus") != null && !"".equals(extendedAttrFromActDef.get("customUIStatus") )){
                	uiStatus = extendedAttrFromActDef.get("customUIStatus").toString();
                }
                
                if( extendedAttrFromActDef.get("WebBillApproveUrl") != null && !"".equals(extendedAttrFromActDef.get("WebBillApproveUrl") )){
                	String[] webUrlStr  = extendedAttrFromActDef.get("WebBillApproveUrl").toString().split("\\.");
					if(webUrlStr.length > 2 ){
						webStr = webUrlStr[webUrlStr.length-2]+"."+webUrlStr[webUrlStr.length-1]+";";
					} 
                }
                if("".equals(webStr)){
                	webStr = nodeName+";";
                }
                 
            }
            catch (WfException e1) {
                e1.printStackTrace();
            }
            catch (BOSException e2) {
                e2.printStackTrace();
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
            mapWF.put("BOSTYPE", pk.getObjectType().toString());
            MYTimeLogUtil.printTime(start, "get tast time");
            start = System.currentTimeMillis();
        } 
        json.put("BOSTYPE", pk.getObjectType().toString()); 
        final SelectorItemCollection sic = new SelectorItemCollection();
        sic.add("entrys.*");
        sic.add("TabTableEntry.*");
        sic.add("TabEntry.*"); 
        IUITool factory = null;
        if (ctx != null) {
            factory = UIToolFactory.getLocalInstance(ctx);
        }
        else {
            factory = UIToolFactory.getRemoteInstance();
        }
        
        if("3C50B4E6".equals(pk.getObjectType().toString())){
        	String billId = "";
        	BatchAdjustBillInfo batchAdjustBillInfo = BatchAdjustBillFactory.getLocalInstance(ctx).getBatchAdjustBillInfo(new ObjectUuidPK(billid));
        	 
        	String diaodongleix = (batchAdjustBillInfo.get("tiaoxy") != null) ? batchAdjustBillInfo.get("tiaoxy").toString(): "0";
        	
        	String personId = "";
			BatchAdjustBillEntryCollection batchAdjustBillEntryCollection = BatchAdjustBillEntryFactory.getLocalInstance(ctx).getBatchAdjustBillEntryCollection("where bill.id ='" + billid + "'");
			if (batchAdjustBillEntryCollection.size() > 0) {
				for (int i = 0; i < batchAdjustBillEntryCollection.size(); ++i) {
					personId = batchAdjustBillEntryCollection.get(i).getPerson().getId().toString();
				}
			}
        	if ("1".equals(diaodongleix)) { 
				try {
					FluctuationBizBillEntryCollection flCollection = FluctuationBizBillEntryFactory.getLocalInstance(ctx)
						.getFluctuationBizBillEntryCollection("where person.id = '" + personId+ "' order by bizDate desc");
					System.out.print(flCollection);
					for (int i = 0; i < flCollection.size(); ++i) {
						billId = flCollection.get(0).getBill().getId().toString();
					}  
				} catch (BOSException e) {
					e.printStackTrace();
				}
			} else if ("2".equals(diaodongleix)) { 
				FlucInBizBillEntryCollection flucInCollection = FlucInBizBillEntryFactory.getLocalInstance(ctx)
						.getFlucInBizBillEntryCollection("where person.id = '" + personId+ "' order by bizDate desc");
				for (int i = 0; i < flucInCollection.size(); ++i) {
					billId = flucInCollection.get(0).getBill().getId().toString();
				} 
			} else if ("0".equals(diaodongleix)) {
				
			}
        	json.put("inOrOut", billId);
        }
        
        UIToolInfo uiInfo = factory.getUIToolInfo("select id,auditTitle,fileViewNode,fileUpNode  where bostype = '" + pk.getObjectType().toString() + "'");
        
        System.out.println("-------------------getBillMap:"+pk+"--------------"+pk.getObjectType().toString());
        json.put("title", (Object)uiInfo.getAuditTitle());
        
        if(!isAudit && uiInfo.getFileUpNode() != null  && !"".equals(uiInfo.getFileUpNode()) && uiInfo.getFileUpNode().indexOf(webStr) >= 0){
        	json.put("upFileNode", "1");
        }else{
        	json.put("upFileNode", "0");
        }
        uiInfo = factory.getUIToolInfo((IObjectPK)new ObjectUuidPK(uiInfo.getId().toString()), sic);
        final SelectorItemCollection billSic = new SelectorItemCollection();
        for (int i = 0; i < uiInfo.getEntrys().size(); i++) {
        	System.out.println("***************getEntityAttributes111="+uiInfo.getEntrys().get(i).getEntityAttributes());
            billSic.add(uiInfo.getEntrys().get(i).getEntityAttributes());
        }
        for (int i = 0; i < uiInfo.getTabTableEntry().size(); i++) {
        	System.out.println("***************getEntityAttributes222="+uiInfo.getTabTableEntry().get(i).getEntityAttributes());
            billSic.add(uiInfo.getTabTableEntry().get(i).getEntityAttributes());
        }
        final EntityViewInfo evi = new EntityViewInfo();
        evi.setSelector(billSic);
        IObjectValue info = null;
        BusinessObjectInfo bo = null;
        if (ctx != null) {
            info = DynamicObjectFactory.getLocalInstance(ctx).getValue(pk.getObjectType(), (IObjectPK)pk, billSic);
            bo = MetaDataLoaderFactory.getMetaDataLoader(ctx).getBusinessObject(pk.getObjectType());
        }else {
            info = DynamicObjectFactory.getRemoteInstance().getValue(pk.getObjectType(), (IObjectPK)pk, billSic);
            bo = MetaDataLoaderFactory.getRemoteMetaDataLoader().getBusinessObject(pk.getObjectType());
        }
        setHeadValue(ctx,json, uiInfo, info,webStr,uiStatus);
        MYTimeLogUtil.printTime(start, "build head data time");
        start = System.currentTimeMillis();
        setEntryValue(ctx,json, uiInfo, info,webStr,uiStatus);
        MYTimeLogUtil.printTime(start, "build entrys data time");
        start = System.currentTimeMillis();
        setBillAtt(ctx, json, info);
        MYTimeLogUtil.printTime(start, "build att data time");
        start = System.currentTimeMillis();
        setwfArr(ctx, json, info.get("id").toString(), sourceID);
        MYTimeLogUtil.printTime(start, "build auditInfo data time");
        final long end = System.currentTimeMillis();
        System.out.println("\u83b7\u53d6\u6570\u636e\u7528\u65f6\uff1a" + (end - startTime) / 1000L);
        System.out.println("\u83b7\u53d6\u6570\u636e\u7528\u65f6\uff1a" +json);
        return json;
    }
    
    private static void setHeadValue(final Context ctx,final JSONObject json, final UIToolInfo uiInfo, final IObjectValue info ,final String webStr,final String uiStatus) {
        final JSONArray headArr = new JSONArray();
        for (int i = 0; i < uiInfo.getEntrys().size(); ++i) {
            final UIToolEntryInfo entiryInfo = uiInfo.getEntrys().get(i);
            final String entityAttributes = entiryInfo.getEntityAttributes();
            final String fieldType = entiryInfo.getFieldType();
            final String classname = entiryInfo.getDbName();
             
            final String displayName = entiryInfo.getDisplayName(); 
            
            final String editNode = entiryInfo.getEditNode()==null?"":entiryInfo.getShowNode();;
            final String showNode = entiryInfo.getShowNode()==null?"":entiryInfo.getShowNode();; 
            final String lablePro = entiryInfo.getLablePro()== null?" ":entiryInfo.getLablePro();
            final String requiredNode = entiryInfo.getRequiredNode()== null?"&&&&":entiryInfo.getRequiredNode();
             
            final int index = entiryInfo.getSeq();
            
            Boolean requiredflag = false;
            if(null != requiredNode && !webStr.equals("") && !requiredNode.equals("")  && requiredNode.indexOf(webStr)>= 0 ){
            	requiredflag = true;
            }  
            Boolean editflag = false;
            if(null != editNode && !webStr.equals("") && !editNode.equals("")  && editNode.indexOf(webStr)>= 0 ){
            	editflag = true;
            	if("Enum".equals(fieldType)){ 
            		//json.put(entityAttributes.replace(".", "_")+"_Enum", (Object)EnumUtils.getEnumList(classname)); 
            		json.put(entityAttributes.replace(".", "_")+"_Enum", (Object)DynamicEnum.getEnumList(classname));
               	   
                } 
            }  
            final boolean isPhone = entiryInfo.isIsPhone(); 
            final String toHideNode = entiryInfo.getToHideNode()== null ?"":entiryInfo.getToHideNode();  
            
            boolean flagHide = false; 
            if(!toHideNode.equals("") && !webStr.equals("")  && toHideNode.indexOf(webStr)>= 0 ){
            	flagHide = true; 
            }
            
            if ( isPhone ){ 
            	//if( !flagHide && ( ( showNode.equals("") && webStr.equals("")) ||  (!webStr.equals("") && showNode.indexOf(webStr)>= 0) )  ){
            	if( !flagHide && (  showNode.equals("") ||  (!webStr.equals("") && showNode.indexOf(webStr)>= 0) )  ){
                	if (entityAttributes != null  ) { 
                		final JSONObject headJson = new JSONObject(); 
            			if( entityAttributes.indexOf(".") >= 0){ 
                            System.out.println(displayName); 
                            final String[] entrys = entityAttributes.split("\\.");
                            if (info.get(entrys[0]) != null) {
                            	headJson.put("name", (Object)displayName); 
                            	if( editflag &&  fieldType.equals("F7") && ((CoreBaseInfo)info.get(entrys[0])).getId() != null){
                            		headJson.put("coreid", ( (CoreBaseInfo)info.get(entrys[0])).getId().toString());    
                                }
                            	Object temp = "";
                                if(entrys.length < 3){
                                	temp =   (Object)BillInfoUtil.getValue(fieldType, classname, ((CoreBaseInfo)info.get(entrys[0])).get(entrys[1]) );
                                }else{
                                	CoreBaseInfo infoq =(CoreBaseInfo) info.get(entrys[0]);
                                	for(int w = 1 ; w < entrys.length ; w++ ){
                                		String tit = entrys[w];
                                		if(w == entrys.length-1 ){
                                			if(infoq.get(tit) == null &&  infoq.getId()!= null){
                                				String id = infoq.getId().toString();
                                				ObjectUuidPK pk = new ObjectUuidPK(id);
                                				
                                				IObjectValue objinfo = null;
												try {
													objinfo = DynamicObjectFactory.getLocalInstance(ctx).getValue(pk.getObjectType(), (IObjectPK)pk);
												} catch (BOSException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
                                				temp = objinfo.get("name");
                                			}else{
                                				temp = infoq.get(tit);
                                			}
                                		}else{
                                			infoq = (CoreBaseInfo)infoq.get(tit);
                                		}
                                		
                                	}
                                } 
                                //Object temp = (Object)BillInfoUtil.getValue(fieldType, classname, ((CoreBaseInfo)info.get(entrys[0])).get(entrys[1]) );
                                headJson.put("value", null==temp?"":temp.toString().trim()); 
                                headJson.put("isEdit", editflag);   
    							 
                                headJson.put("fieldType", fieldType);  
                                headJson.put("lablePro", lablePro);  
                                headJson.put("isRequired", requiredflag);  
                                headJson.put("entityAttributes", entityAttributes);   
                                headJson.put("index", index);  
                                
                            }else {
                            	headJson.put("name", (Object)displayName);
                                headJson.put("value", (Object)"");
                                headJson.put("isEdit", editflag);  
                                
                                headJson.put("fieldType", fieldType);  
                                headJson.put("lablePro", lablePro);  
                                headJson.put("isRequired", requiredflag);  
                                headJson.put("entityAttributes", entityAttributes);   
                                headJson.put("index", index); 
                            }
            			} else { 
                        	headJson.put("name", (Object)displayName);
                        	 Object temp = (Object)BillInfoUtil.getValue(fieldType, classname, info.get(entityAttributes) );
                             headJson.put("value", null==temp?"":temp.toString().trim());
                          
                            headJson.put("isEdit", editflag);  
                            
                            if(editflag && fieldType.equals("Enum") ){
                            	if(info.get(entityAttributes) != null &&  info.get(entityAttributes).equals(temp)){
                            		Map<String, DynamicEnum> map = DynamicEnum.getEnumMap(classname); 
                                	Iterator<Map.Entry<String, DynamicEnum>> entries = map.entrySet().iterator(); 
                                	while (entries.hasNext()) { 
                                	    Map.Entry<String, DynamicEnum> entry = entries.next();
                                	    if(entry.getValue().getAlias().equals(temp)){
                                	    	headJson.put("coreid", DynamicEnum.getEnumByName(classname, entry.getKey()).getValue());
                                	    }
                                	    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
                                	}
                            	}else{
                            		headJson.put("coreid", info.get(entityAttributes));  
                            	}
                            	
                            } 
                            
                            headJson.put("fieldType", fieldType);  
                            headJson.put("lablePro", lablePro);  
                            headJson.put("isRequired", requiredflag);  
                            headJson.put("entityAttributes", entityAttributes);   
                            headJson.put("index", index);  
                        } 
            			headArr.add((Object)headJson);
                    }else{
                    	final JSONObject headJson = new JSONObject();
                    	String initValue = entiryInfo.getInitValue();
                    	headJson.put("name", (Object)displayName); 
                    	headJson.put("value", null==initValue?"":initValue.toString().trim());
                    	headJson.put("isEdit", editflag);  
                        
                    	headJson.put("lablePro", lablePro);  
                    	headJson.put("isRequired", requiredflag);   
                    	headJson.put("index", index);  
                        headArr.add((Object)headJson);
                         
                    }
                }   
            }
        }
        json.put("headArr", (Object)headArr);
    }
    
    private static void setEntryValue(final Context ctx,final JSONObject json, final UIToolInfo uiInfo, final IObjectValue info ,final String webStr,final String uiStatus) {
        final JSONArray entryArray = new JSONArray();
        System.out.println("*************************tabEntrySize="+uiInfo.getTabEntry().size());
        for (int i = 0; i < uiInfo.getTabEntry().size(); i++) {
        	System.out.println("*************************tabEntrySize i="+i);
            final UIToolTabEntryInfo tabEntryInfo = uiInfo.getTabEntry().get(i);
            final String entryAtt = tabEntryInfo.getEntryAtt();
            System.out.println("*************************entryAtt="+entryAtt);
            final IObjectCollection coll = (IObjectCollection)info.get(entryAtt);  
            int size = coll==null ? 0:coll.size();
            
            
            
            for (int j = 0; j < size; ++j) {
                final JSONObject jsonEntry = new JSONObject();
                final IObjectValue baseInfo = coll.getObject(j);
                
                boolean inHaveData = false;
               
                final JSONArray ja = new JSONArray();
                for (int k = 0; k < uiInfo.getTabTableEntry().size(); ++k) {
                    final UIToolTabTableEntryInfo tabTableEntryInfo = uiInfo.getTabTableEntry().get(k); 
                    final String entityAttributes = tabTableEntryInfo.getEntityAttributes()==null?"nothis":tabTableEntryInfo.getEntityAttributes();
                    
                    if(entityAttributes.split("\\.")[0].equals(entryAtt)){
                    	
                    	 boolean editflag = "edit".equals(uiStatus.toLowerCase());
                    	 
                    	final String fieldType = tabTableEntryInfo.getFieldType();
                        final String classname = tabTableEntryInfo.getDbName();
                        final String displayneme = tabTableEntryInfo.getDisplayName();
                        final String tablename = tabTableEntryInfo.getTableName()==null?"0":tabTableEntryInfo.getTableName();
                        
                        final Boolean isPhone = (Boolean)tabTableEntryInfo.getBoolean("isPhone") ;    
                        
                        final String editNode = tabTableEntryInfo.getEditNode(); 
                        final String showNode = tabTableEntryInfo.getShowNode()==null?"":tabTableEntryInfo.getShowNode();;
                        
                        final String lablePro = tabTableEntryInfo.getLablePro()== null?" ":tabTableEntryInfo.getLablePro();
                        final String requiredNode = tabTableEntryInfo.getRequiredNode()== null?"&&&&":tabTableEntryInfo.getRequiredNode();
                        
                        final String entryid = baseInfo.get("id").toString();
                         
                        Boolean requiredflag = false;
                        if(null != requiredNode && !webStr.equals("") && !requiredNode.equals("")  && requiredNode.indexOf(webStr)>= 0 ){
                        	requiredflag = true;
                        } 
                        System.out.println(displayneme);
                        final int index = tabTableEntryInfo.getSeq();
                         
                        if(editflag && null != editNode && !webStr.equals("") && !editNode.equals("")  && editNode.indexOf(webStr)>= 0 ){
                        	 
                        	if("Enum".equals(fieldType)){  //EnumUtils.getEnum((Class)Class.forName("com.kingdee.eas.custom.sfyjbmfzr"), "1")
                            	//json.put(entityAttributes.replace(".", "_")+"_Enum", (Object)EnumUtils.getEnumList(classname));
                        		
                        		json.put(entityAttributes.replace(".", "_")+"_Enum", (Object)DynamicEnum.getEnumList(classname));
                            }
                            /*if( "F7".equals(fieldType) && !"com.kingdee.eas.basedata.person.app.Person".equals(classname)  ){
                            	final String entryField = tabTableEntryInfo.getEntityAttributes().replaceAll(String.valueOf(entryAtt) + ".", "");
                            	try {
                            		 Class<?> clazz = Class.forName(((CoreBaseInfo)baseInfo.get(entryField.split("\\.")[0])).getClass().getName());
    								DataTableInfo table = EntityUtility.getBOSEntity(ctx, (IObjectValue) clazz.newInstance()).getTable();
    								String name = table.getName();  
    								String sql = " select fname";
    							} catch (Exception e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}  
                            }*/
                        }else{
                        	editflag = false;//是编辑页面 但是不是每一个字段都要编辑
                        }  
     
                        final String toHideNode = tabTableEntryInfo.getToHideNode()== null ?"":tabTableEntryInfo.getToHideNode();  
                        
                        boolean flagHide = false; 
                        if(!toHideNode.equals("") && !webStr.equals("")  && toHideNode.indexOf(webStr)>= 0 ){
                        	flagHide = true; 
                        }
                        
                        if( !flagHide && (  showNode.equals("") ||  (!webStr.equals("") && showNode.indexOf(webStr)>= 0) )  ){
                        	inHaveData = true;
                        	if (tabTableEntryInfo.getEntityAttributes() != null && tabTableEntryInfo.getEntityAttributes().startsWith(String.valueOf(entryAtt) + ".")) {
                                final JSONObject jsonEntryInfo = new JSONObject();
                                jsonEntryInfo.put("entryid", entryid);  
                                
                                final String entryField = tabTableEntryInfo.getEntityAttributes().replaceAll(String.valueOf(entryAtt) + ".", "");
                               
                                if (entryField.indexOf(".") >= 0) {
                                    final String[] entrys = entryField.split("\\.");
                                    if (baseInfo.get(entrys[0]) != null) {
                                        jsonEntryInfo.put("name", (Object)displayneme); 
                                        if( editflag && fieldType.equals("F7") && ((CoreBaseInfo)baseInfo.get(entrys[0])).getId() != null){
                                        	jsonEntryInfo.put("coreid", ( (CoreBaseInfo)baseInfo.get(entrys[0])).getId().toString());    
                                        } 
                                        Object temp = "";
                                        if(entrys.length < 3){
                                        	temp =  (Object)BillInfoUtil.getValue(fieldType, classname, new StringBuilder().append(((CoreBaseInfo)baseInfo.get(entrys[0])).get(entrys[1])).toString() );
                                        }else{
                                        	CoreBaseInfo infoq =(CoreBaseInfo) baseInfo.get(entrys[0]);
                                        	for(int w = 1 ; w < entrys.length ; w++ ){
                                        		String tit = entrys[w];
                                        		if(w == entrys.length-1 ){
                                        			if(infoq.get(tit) == null &&  infoq.getId()!= null){
                                        				String id = infoq.getId().toString();
                                        				ObjectUuidPK pk = new ObjectUuidPK(id);
                                        				
                                        				IObjectValue objinfo = null;
														try {
															objinfo = DynamicObjectFactory.getLocalInstance(ctx).getValue(pk.getObjectType(), (IObjectPK)pk);
														} catch (BOSException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
                                        				temp = objinfo.get("name");
                                        			}else{
                                        				temp = infoq.get(tit);
                                        			}
                                        		}else{
                                        			infoq = (CoreBaseInfo)infoq.get(tit);
                                        		}
                                        		
                                        	}
                                        }
                                        //temp = (Object)BillInfoUtil.getValue(fieldType, classname, new StringBuilder().append(((CoreBaseInfo)baseInfo.get(entrys[0])).get(entrys[1])).toString() );
                                        System.out.print("---------------"+null==temp);
                                        System.out.print("---------------11:"+temp);
                                        jsonEntryInfo.put("value", null==temp?"":temp.toString()); 
                                        jsonEntryInfo.put("isEdit", editflag);   
    									
                                        jsonEntryInfo.put("fieldType", fieldType);  
                                        jsonEntryInfo.put("lablePro", lablePro);  
                                        jsonEntryInfo.put("isRequired", requiredflag);  
                                        jsonEntryInfo.put("entityAttributes", entityAttributes);  
                                        jsonEntryInfo.put("tablename", tablename);  
                                        jsonEntryInfo.put("index", index);  
                                        
                                        jsonEntryInfo.put("isHide", isPhone);  
                                        
                                        
                                    }else {
                                        jsonEntryInfo.put("name", (Object)displayneme);
                                        jsonEntryInfo.put("value", (Object)"");
                                        jsonEntryInfo.put("isEdit", editflag);  
                                        
                                        jsonEntryInfo.put("fieldType", fieldType);  
                                        jsonEntryInfo.put("lablePro", lablePro);  
                                        jsonEntryInfo.put("isRequired", requiredflag);  
                                        jsonEntryInfo.put("entityAttributes", entityAttributes);  
                                        jsonEntryInfo.put("tablename", tablename);  
                                        jsonEntryInfo.put("index", index);  
                                        
                                        jsonEntryInfo.put("isHide", isPhone);  
                                    }
                                }else {   
                                    jsonEntryInfo.put("name", (Object)displayneme);
                                    Object temp = (Object)BillInfoUtil.getValue(fieldType, classname, new StringBuilder().append(baseInfo.get(entryField)).toString() );
                                    jsonEntryInfo.put("value", null==temp?"":temp.toString().trim());
                                    jsonEntryInfo.put("isEdit", editflag);  
                                    
                                    if(editflag && fieldType.equals("Enum") ){
                                    	if(baseInfo.get(entryField) != null && baseInfo.get(entryField).equals(temp)){
                                    		Map<String, DynamicEnum> map = DynamicEnum.getEnumMap(classname); 
                                        	Iterator<Map.Entry<String, DynamicEnum>> entries = map.entrySet().iterator(); 
                                        	while (entries.hasNext()) { 
                                        	    Map.Entry<String, DynamicEnum> entry = entries.next();
                                        	    if(entry.getValue().getAlias().equals(temp)){
                                        	    	jsonEntryInfo.put("coreid", DynamicEnum.getEnumByName(classname, entry.getKey()).getValue());
                                        	    }
                                        	    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
                                        	}
                                    	}else{
                                    		jsonEntryInfo.put("coreid", baseInfo.get(entryField));  
                                    	}
                                    	
                                    } 
                                    
                                    jsonEntryInfo.put("fieldType", fieldType);  
                                    jsonEntryInfo.put("lablePro", lablePro);  
                                    jsonEntryInfo.put("isRequired", requiredflag);  
                                    jsonEntryInfo.put("entityAttributes", entityAttributes);  
                                    jsonEntryInfo.put("tablename", tablename);  
                                    jsonEntryInfo.put("index", index);  
                                    
                                    jsonEntryInfo.put("isHide", isPhone);  
                                }
                                ja.add((Object)jsonEntryInfo);
                            }else{
                            	final JSONObject jsonEntryInfo = new JSONObject();
                            	String initValue = tabTableEntryInfo.getInitValue();
                            	jsonEntryInfo.put("name", (Object)displayneme); 
                                jsonEntryInfo.put("value", null==initValue?"":initValue.toString().trim());
                                jsonEntryInfo.put("isEdit", editflag);  
                                
                                jsonEntryInfo.put("lablePro", lablePro);  
                                jsonEntryInfo.put("isRequired", requiredflag);  
                                jsonEntryInfo.put("tablename", tablename);  
                                jsonEntryInfo.put("index", index);  
                                
                                jsonEntryInfo.put("isHide", isPhone);  
                                ja.add((Object)jsonEntryInfo);
                                 
                            }
                        }
                    }else if(tabTableEntryInfo.getEntityAttributes()== null || tabTableEntryInfo.getEntityAttributes().equals("")){
                    	final String fieldType = tabTableEntryInfo.getFieldType();
                        final String classname = tabTableEntryInfo.getDbName();
                        final String displayneme = tabTableEntryInfo.getDisplayName();
                        final String tablename = tabTableEntryInfo.getTableName()==null?"0":tabTableEntryInfo.getTableName();
                        
                        final Boolean isPhone = (Boolean)tabTableEntryInfo.getBoolean("isPhone") ;    
                        
                        final String editNode = tabTableEntryInfo.getEditNode(); 
                        final String showNode = tabTableEntryInfo.getShowNode()==null?"":tabTableEntryInfo.getShowNode();;
                        
                        final String lablePro = tabTableEntryInfo.getLablePro()== null?" ":tabTableEntryInfo.getLablePro();
                        final String requiredNode = tabTableEntryInfo.getRequiredNode()== null?"&&&&":tabTableEntryInfo.getRequiredNode();
                        
                        final String entryid = baseInfo.get("id").toString(); 
                        Boolean requiredflag = false; 
                        final int index = tabTableEntryInfo.getSeq();
                        System.out.println(displayneme);
                    	final JSONObject jsonEntryInfo = new JSONObject();
                    	String initValue = tabTableEntryInfo.getInitValue();
                    	jsonEntryInfo.put("name", (Object)displayneme); 
                        jsonEntryInfo.put("value", null==initValue?"":initValue.toString().trim());
                        jsonEntryInfo.put("isEdit", false);  
                        
                        jsonEntryInfo.put("lablePro", lablePro);  
                        jsonEntryInfo.put("isRequired", requiredflag);  
                        jsonEntryInfo.put("tablename", tablename);  
                        jsonEntryInfo.put("index", index);  
                        
                        jsonEntryInfo.put("isHide", isPhone);  
                        ja.add((Object)jsonEntryInfo);
                    }  
                }
                
                if(inHaveData){

                    if(size==1){
                    	jsonEntry.put("name", (Object)(String.valueOf(tabEntryInfo.getTabAlies())));
                    }else{
                    	jsonEntry.put("name", (Object)(String.valueOf(tabEntryInfo.getTabAlies()) + "(" + (j + 1) + ")"));
                    }
                    
                    if (j == 0) {
                        jsonEntry.put("firsttag", (Object)"1");
                    }
                    jsonEntry.put("entryArr", (Object)ja);
                    entryArray.add((Object)jsonEntry);
                }
                
            }
        }
        json.put("tabArr", (Object)entryArray);
    }
    
    private static void setBillAtt(final Context ctx, final JSONObject json, final IObjectValue info) throws BOSException {
        final String sqlParam = "select fvalue_l2 from T_BAS_ParamItem where fkeyid = (select fid from T_BAS_Param where fnumber = 'ATTACHMENTSTORAGE')";
        final IRowSet rowSet = DbUtil.executeQuery(ctx, sqlParam);
        try {
            if (rowSet.next()) {
                final String attType = rowSet.getString("fvalue_l2");
                if (attType.equals("0")) {
                    setDBAttFiles(ctx, json, info);
                }
                else if (attType.equals("1")) {
                    setFTPAttFiles(ctx, json, info);
                }
                else if (attType.equals("2")) {
                    setEASSERVERAttFiles(ctx, json, info);
                }
            }
        }
        catch (EASBizException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
    
    private static void setDBAttFiles(final Context ctx, final JSONObject json, final IObjectValue info) {
        try {
            final String billid = info.get("id").toString();
            //final String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime,FFile files from T_BAS_Attachment where fid in (select fattachmentid from T_BAS_BoAttchAsso where fboid = '" + billid + "')";
            final String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime,FFile files from T_BAS_Attachment where fid in (select FATTACHMENT  from T_HR_SHRAttachmentExt where fboid = '" + billid + "')";
            
            final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
            final String easHome = System.getProperty("EAS_HOME");
            final String path = String.valueOf(easHome) + "/server/deploy/portal.ear/my_app.war/files/";
            //String path = String.valueOf(easHome) + "/server/deploy/portal.ear/my_app.war/files/";
            final JSONArray ja = new JSONArray();
            final String rootUrl = PropUtil.getParameterByName("eas.rooturl");
            //path = "D:/EASBOS8.5/workspace";
            System.out.println("--------------------rootUrl:"+rootUrl);
            while (rowSet.next()) {
                String fname = rowSet.getString("fname");
                final String simplename = rowSet.getString("fsimplename");
                final Blob blob = rowSet.getBlob("files");
                final byte[] fille = BlobToBytes(blob);
                final JSONObject json2 = new JSONObject();
                json2.put("name", (Object)(String.valueOf(fname) + "." + simplename));
                fname = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
                final File file = new File(String.valueOf(path) + fname + "." + simplename);
                final FileOutputStream fos = new FileOutputStream(file);
                fos.write(fille);
                fos.close();
                json2.put("url", (Object)(String.valueOf(rootUrl) + "/my_app/files/" + fname + "." + simplename));
                ja.add((Object)json2);
            }
            json.put("fileArr", (Object)ja);
        }
        catch (BOSException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (SQLException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }
    
    private static byte[] BlobToBytes(final Blob blob) {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(blob.getBinaryStream());
            final byte[] bytes = new byte[(int)blob.length()];
            for (int len = bytes.length, offset = 0, read = 0; offset < len && (read = bufferedInputStream.read(bytes, offset, len - offset)) >= 0; offset += read) {}
            return bytes;
        }
        catch (Exception e) {
            return null;
        }
        finally {
            try {
                bufferedInputStream.close();
            }
            catch (IOException e2) {
                return null;
            }
        }
    }
    
    public static void setFTPAttFiles(final Context ctx, final JSONObject json, final IObjectValue info) throws BOSException, EASBizException {
        final String billId = info.get("id").toString();
        //final String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime from T_BAS_Attachment where fid in (select fattachmentid from T_BAS_BoAttchAsso where fboid = '" + billId + "')";
        final String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime from T_BAS_Attachment where fid in (select  FATTACHMENT  from T_HR_SHRAttachmentExt where fboid = '" + billId + "')";
         
        final IRowSet rs = DbUtil.executeQuery(ctx, sql);
        final JSONArray ja = new JSONArray();
        try {
            final String rootUrl = PropUtil.getParameterByName("eas.rooturl");
            while (rs.next()) {
                String fname = rs.getString("fname");
                final String fsimplename = rs.getString("fsimplename");
                final JSONObject json2 = new JSONObject();
                json2.put("name", (Object)(String.valueOf(fname) + "." + fsimplename));
                fname = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
                final String easHome = System.getProperty("EAS_HOME");
                final String path = String.valueOf(easHome) + "/server/deploy/portal.ear/my_app.war/files/";
                final File file = new File(path + fname + "." + fsimplename);
                final FileOutputStream fos = new FileOutputStream(file);
                final AttachmentDownloadServer iAff = new AttachmentDownloadServer();
                final byte[] fille = iAff.getFileFromFtp(ctx, rs.getString("fid"));
                if (fille == null) {
                    continue;
                }
                fos.write(fille);
                fos.close();
                json2.put("url", (Object)(String.valueOf(rootUrl) + "/my_app/files/" + fname + "." + fsimplename));
                ja.add((Object)json2);
            }
            json.put("fileArr", (Object)ja);
        }
        catch (Exception e) {
            throw new BOSException((Throwable)e);
        }
    }
    
    private static void setEASSERVERAttFiles(final Context ctx, final JSONObject json, final IObjectValue info) {
        try {
            final String billid = info.get("id").toString();
            final String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime,FREMOTEPATH from T_BAS_Attachment where fid in (select fattachmentid from T_BAS_BoAttchAsso where fboid = '" + billid + "')";
            final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
            final String easHome = System.getProperty("EAS_HOME");
            final String path = String.valueOf(easHome) + "/server/deploy/portal.ear/my_app.war/files/";
            final JSONArray ja = new JSONArray();
            final String rootUrl = PropUtil.getParameterByName("eas.rooturl");
            while (rowSet.next()) {
                String fname = rowSet.getString("fname");
                final String simplename = rowSet.getString("fsimplename");
                final String remotePath = rowSet.getString("FREMOTEPATH");
                if (remotePath != null) {
                    if ("".equals(remotePath)) {
                        continue;
                    }
                    final byte[] fille = getBytes(remotePath);
                    final JSONObject json2 = new JSONObject();
                    json2.put("name", (Object)(String.valueOf(fname) + "." + simplename));
                    fname = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
                    final File file = new File(String.valueOf(path) + fname + "." + simplename);
                    final FileOutputStream fos = new FileOutputStream(file);
                    fos.write(fille);
                    fos.close();
                    json2.put("url", (Object)(String.valueOf(rootUrl) + "/my_app/files/" + fname + "." + simplename));
                    ja.add((Object)json2);
                }
            }
            json.put("fileArr", (Object)ja);
        }
        catch (BOSException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (SQLException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }
    
    private static byte[] getBytes(final String filePath) {
        byte[] buffer = null;
        try {
            final File file = new File(filePath);
            if (!file.exists()) {
                return new byte[0];
            }
            final FileInputStream fis = new FileInputStream(file);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            final byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return buffer;
    }
    
    private static void setwfArr(final Context ctx, final JSONObject json, final String billid, final String assignID) {
        try {
            final JSONArray ja = new JSONArray();
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            final String sql = "  select b.FACTDEFNAME_l2 FACTDEFNAME ,a.FTYPE FTYPE,c.FNAME_L2 language   ,a.FResult,a.FOPINION_L2 FOPINION,a.FCREATETIME FCREATETIME,d.FID,d.FNAME_L2,a.fid,  "+
            	 
            	 " approve.FHandlerContent  ,approve.Fispass  "+
				 "  from t_bas_option a left join t_wfr_actinst b on a.FCurrentAcinstID= b.FACTINSTID  "+
				 "   left join  T_BAS_MultiApprove  approve  on approve.FASSIGNMENTID=a.FASSIGNMENTID "+
				 "   left join t_pm_user c on a.FCREATORID  = c.FID    left outer join T_BAS_Attachment d on a.FASSIGNMENTID  = d.FBeizhu  "+
				 "   where a.FBillID = '"+billid+"'   order by a.FCREATETIME  DESC " ;
            final IRowSet rowSet = DbUtil.executeQuery(ctx, sql);  
            while (rowSet.next()) {
            	final JSONObject entryInfo = new JSONObject();
            	Object temp = (Object)rowSet.getString("FACTDEFNAME");
                entryInfo.put("actdefName", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("Fispass");
                entryInfo.put("isPass",  null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("FHandlerContent");
                entryInfo.put("handlerOpinion", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("FOPINION");
                entryInfo.put("opinion", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("language");
                entryInfo.put("name", null==temp?"":temp.toString().trim());
                entryInfo.put("createTime", (Object)sdf.format(rowSet.getDate("FCREATETIME")));
                entryInfo.put("type", (Object)rowSet.getString("FTYPE"));
                
                ja.add((Object)entryInfo);
            }
            /*final IMetaDataPK queryPK = (IMetaDataPK)new MetaDataPK("com.kingdee.eas.base.multiapprove.app", "MultiApproveQuery");
            final IQueryExecutor exec = QueryExecutorFactory.getLocalInstance(ctx, queryPK);
            final EntityViewInfo evi = new EntityViewInfo();
            final FilterInfo filterInfo = new FilterInfo();
            filterInfo.getFilterItems().add(new FilterItemInfo("MultiApprove.billId", (Object)billid, CompareType.EQUALS));
            evi.setFilter(filterInfo);
            exec.setObjectView(evi);
            exec.option().isAutoTranslateEnum = true;
            exec.option().isAutoTranslateBoolean = true;
            final IRowSet rowSet = exec.executeQuery();
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            while (rowSet.next()) {
                final JSONObject entryInfo = new JSONObject();
                Object temp = (Object)rowSet.getString("AssignDetail.actdefName");
                entryInfo.put("actdefName", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("MultiApprove.isPass");
                entryInfo.put("isPass",  null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("MultiApprove.handlerOpinion");
                entryInfo.put("handlerOpinion", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("MultiApprove.opinion");
                entryInfo.put("opinion", null==temp?"":temp.toString().trim());
                temp = (Object)rowSet.getString("personId.name");
                entryInfo.put("name", null==temp?"":temp.toString().trim());
                entryInfo.put("createTime", (Object)sdf.format(rowSet.getDate("MultiApprove.createTime")));
                ja.add((Object)entryInfo);
            }*/
            json.put("wfArr", (Object)ja);
        }
        catch (BOSException e) {
            e.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
    
    private static void setManualDecisionItems(final JSONObject json, final String manualDecisionItems) {
        final JSONArray agreeHandleArr = new JSONArray();
        final JSONArray disagreeHandleArr = new JSONArray();
        JSONObject jsonTemp = new JSONObject();
        if (manualDecisionItems == null) {
            jsonTemp.put("name", (Object)"\u65e0");
            jsonTemp.put("value", (Object)"0");
            agreeHandleArr.add((Object)jsonTemp);
            disagreeHandleArr.add((Object)jsonTemp);
        }
        else {
            final String[] items = manualDecisionItems.split(":");
            for (int i = 0; i < items.length; ++i) {
                final String[] item = items[i].split(";");
                jsonTemp = new JSONObject();
                jsonTemp.put("name", (Object)item[1]);
                jsonTemp.put("value", (Object)item[0]);
                if (item[2].equals("\u540c\u610f")) {
                    agreeHandleArr.add((Object)jsonTemp);
                }
                else if (item[2].equals("\u4e0d\u540c\u610f")) {
                    disagreeHandleArr.add((Object)jsonTemp);
                }
                else if (item[2].equals("\u5168\u90e8")) {
                    agreeHandleArr.add((Object)jsonTemp);
                    disagreeHandleArr.add((Object)jsonTemp);
                }
            }
        }
        json.put("agreeHandleArr", (Object)agreeHandleArr);
        json.put("disagreeHandleArr", (Object)disagreeHandleArr);
    }
}
