package com.kingdee.eas.custom.app;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.cp.eip.sso.ltpa.LtpaToken;
import com.kingdee.eas.cp.eip.sso.ltpa.LtpaTokenManager;
import com.kingdee.eas.custom.unit.CoreUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class DataFacadeControllerBean extends AbstractDataFacadeControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.custom.app.DataFacadeControllerBean");

	@Override
	protected void _getAndPutDate(Context ctx, String data) throws BOSException {
		// TODO Auto-generated method stub 
		super._getAndPutDate(ctx, data); 
	}

	@Override
	public void getAndPutDate(Context ctx, String data) throws BOSException {
		
		/*PersonInfo PersonInfo = PersonFactory.getLocalInstance(ctx).getPersonCollection(" where number='335214'").get(0);
		createUserByNumber(ctx, PersonInfo, "IMP");*/
		/*System.out.print("-----_getAndPutDate"); 
		
		PersonInfo person = new PersonInfo();
		person = PersonFactory.getLocalInstance(ctx).getPersonCollection(" where number = '1003770'").get(0);
		
		String accessToken = "3a78722cdc993d238b4d980db37ffd8e";
		CorpUserDetail userDetail = new CorpUserDetail();
	    userDetail.setUserid(person.getNumber());
	    userDetail.setName(person.getName());
	    userDetail.setOrderInDepts("0");
	    
	    //userDetail.setDepartment("测试");
	    userDetail.setPosition("职位"); 
	    userDetail.setMobile("18716222203");
	    userDetail.setTel("18716222203");
	    userDetail.setWorkPlace("北京");
        userDetail.setRemark("备注");
        userDetail.setEmail("727765281@qq.com");
        userDetail.setJobnumber();
        userDetail.setIsHide();
        userDetail.setSenior(); 
        userDetail.setExtattr(); 
	    try {
			String userId = UserHelper.createUser(accessToken, userDetail);
			System.out.println("*******************************dingding    userId="+userId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("*******************************dingding    accessToken="+accessToken);
		*/
		//billid=bNsAAAAVaKS2XM7x&assignId=99e3e7e8-5023-4571-89cd-50c97e6d2539WFWKITEM
		try { 
			//FileUtil.upload(ctx, "bNsAAAAUpWG2XM7x", "D:/EASBOS8.5", "1111.jpg");
			
			/*final IEnactmentService service = EnactmentServiceFactory.createEnactService(ctx);
            final AssignmentInfo assignmentInfo = service.getAssignmentById("f2ecb87b-fb1e-4af9-8a2b-be756c9abeadWFWKITEM");
            System.out.println("*******************************dingding    accessToken=");*/
			String link = "http://115.182.82.15:8086/portal/UserAccountValidate.jsp?dataCenter=lbchrtest&userNumber=1003770&billid=bNsAAAAbd/bA2tAN&bostype=C0DAD00D&assignID=de4a032c-f0a9-40e8-a53a-d84375e093e5WFWKITEM";
			//Notifies.sendTask(ctx,"1003770",  link,  link ,"【调动流程】-销售主管-刘丽丽","【调动流程】-销售主管-刘丽丽","de4a032c-f0a9-40e8-a53a-d84375e093e5WFWKITEM");
			//setDDData(ctx);
			//setDDData(  ctx );   
			/*
			try { 
				IAttachment iAttachment =  AttachmentFactory.getLocalInstance(ctx);
				EntityViewInfo view = new EntityViewInfo();
		        FilterInfo filter = new FilterInfo();
		        filter.getFilterItems().add(new FilterItemInfo("id",  "bNsAAAAceRr0r08D"));
		        view.setFilter(filter);
		        AttachmentCollection coll =  iAttachment.getAttachmentCollection(view); //查询所关联附件
		        if( null ==  (coll)){
		        	System.out.print("null ==  (coll) :");
		        }
		        
		        IObjectPK[] pks = new ObjectUuidPK[coll.size()];
		        for(int i = 0; i < coll.size(); i++){  
		              AttachmentInfo attachment =coll.get(i); //附件对象
		              pks[i] = new ObjectUuidPK(attachment.getId());
		              
		        }
		        iAttachment.delete(pks); //删除附件 
		        
		        EntityViewInfo view2 = new EntityViewInfo();
		        ISHRAttachmentExt iSHRAttachmentExt =  SHRAttachmentExtFactory.getLocalInstance(ctx);
		        FilterInfo filter2 = new FilterInfo();
		        filter2.getFilterItems().add(new FilterItemInfo("ATTACHMENT",  "bNsAAAAceRr0r08D"));
		        view2.setFilter(filter2);
		        SHRAttachmentExtCollection coll2 =  iSHRAttachmentExt.getSHRAttachmentExtCollection(view2); //查询所关联附件
		        if( null ==  (coll2)){
		        	System.out.print("null ==  (coll2) :");
		        }
		        
		        IObjectPK[] pks2 = new ObjectUuidPK[coll2.size()];
		        for(int i = 0; i < coll2.size(); i++){  
		        	SHRAttachmentExtInfo shrAttachmentExtInfo =coll2.get(i); //附件对象
		            pks2[i] = new ObjectUuidPK(shrAttachmentExtInfo.getId());
		              
		        }
		        iSHRAttachmentExt.delete(pks2); //删除附件 
			        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("error :"+ e.getMessage()); 
			}  */
			
			//FileItem fi = new FileItem
			File savedFile = new File("D:/", "基础数据.txt");
			//FileUtil.upload(ctx, "bNsAAAAcDDa2XM7x", savedFile );
			
			/*IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx);
			
			EntityViewInfo view = new EntityViewInfo();
			FilterInfo filter = new FilterInfo();
	        filter.getFilterItems().add(new FilterItemInfo("ATTACHMENT",  "bNsAAAAb3ND0r08D"));
	        view.setFilter(filter);
	        BoAttchAssoCollection coll =  iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
	        if( null ==  (coll)){
	        	System.out.println("*******************************dingding    accessToken=");
	        }
	        IAttachment iAttachment =  AttachmentFactory.getLocalInstance(ctx);
	        IObjectPK[] pks = new ObjectUuidPK[coll.size()];
	        for(int i = 0; i < coll.size(); i++){ 
	        	BoAttchAssoInfo bo = coll.get(i); //附件关联对象
	            AttachmentInfo attachment = bo.getAttachment(); //附件对象
	            pks[i] = new ObjectUuidPK(attachment.getId());
	              
	        }
	        iAttachment.delete(pks); //删除附件
	        iBoAttchAsso.delete(filter); //删除附件关联
*/	        
			//String userInfo = Notifies.getUserId("ed92a0c9199338a0b62240ef71997265", "fce07aaca16b34e2b42faff7bb84c367");
			//FileUtil.upload(ctx, "bNsAAAAUpWG2XM7x", "D:/EASBOS8.5", "11111.png");
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAZ+HAn5i+4", "e48630a4-1681-41ac-89b1-f00b0e6c480eWFWKITEM");
			//JSONObject json1 = MessageListUtil.getMessageList(ctx, "18716006950", 1, 10, "todo", "");
			//转正
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAbZ++2XM7x", "f089c1f9-677f-43e0-84d8-e0ebf2be98d1WFWKITEM");
			//离职
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAbd/bA2tAN", "a337b928-ac80-4fb5-812e-c4591518d556WFWKITEM");
			 
			//调薪审批
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAb/NQ8ULTm", "a5f4c092-993d-4ee1-8ec1-91f298340945WFWKITEM");
			//续签
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAALH+oqJjKp", "7c91f764-7ced-4502-8be8-094309f1a0c3WFWKITEM");
			
			//调动
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAav6/A2tAN", "645ddd70-d95c-4ec3-bd74-83959281c3edWFWKITEM");
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAYgKPXS5kx", "c3fc180d-4fae-4278-8131-fcfec3f02e70WFWKITEM");
			
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAcKxc8ULTm", "8dbaffeb-1506-4106-b9f9-6622bfb6cd45WFWKITEM");
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAeL108ULTm", "c7e30242-30cc-4c10-9979-6c1bfae242e3WFWKITEM");
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAeL2g8ULTm", "70a01d82-0f89-404f-919c-f12f8b4ae3a4WFWKITEM");
			
			LtpaToken token = LtpaTokenManager.generate("user",LtpaTokenManager.getDefaultLtpaConfig());
			String password = URLEncoder.encode(token.toString());
			System.out.println("password="+password);
			
			Context ctxa  = CoreUtil.contextNoCtx(  "user");
			String selectSql = " select cfrecordid AS RECORDID , cfddid as DDID from  CT_CUS_Task where  cfassignid = '"+22222+"'";
			//IRowSet rowSet = SQLExecutorFactory.getRemoteInstance(selectSql.toString()).executeSQL();
			IRowSet rowSet = DbUtil.executeQuery(ctxa, selectSql);
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAeL3c8ULTm", "7a985783-6414-4b36-84f9-efd2a5ce8e4aWFWKITEM");
			
			//System.out.println("json="+json);
			
			//录用
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAcDFbMWKYX", "df3299f1-cf1f-4607-8137-f9d0b4f0016fWFWKITEM");
			 
			//r人员需求
			
			//调动实习期流程
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAbjrLA2tAN", "ee16a32c-44cf-4ad7-a3b5-1ed4f5722f68WFWKITEM");
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAbjruIWZSL", "d5212893-fdaa-41fb-8b64-2fa4b5981e13WFWKITEM");
			//JSONObject json = MessageListUtil.getMessageList(ctx, "1003770", 1, 210, "todo", "");
			//JSONObject json = MessageListUtil.getMessageList(ctx, "18716006950", 1, 100, "done", "");
			//System.out.println("json="+json);
			 
			String editData = "{\"entryid\":\"bNsAAAAcDFeaXqib\",\"entrys_lzjjr_name0_15\":\"nZo4FjtYRE+SpIzBCs97sYDvfe0=\",\"entrys_mailenddate0_16\":\"2021-08-11\",\"entrys_ywenddate0_17\":\"2021-08-11\",\"entrys_sflxjyxy0_18\":\"0\",\"entrys_lxsj0_19\":\"3\",\"entrys_qzjjr_name0_20\":\"undefined\",\"thisText\":\"ssssssssssssssssssss\",\"billType\":\"CC58A617\"}".replace("_", ".");
			//editmap:{"entrys.lzjjr.name0.14":"nZo4FjtYRE+SpIzBCs97sYDvfe0=","thisText":"ssssssssssssssssssss","entrys.qzjjr.name0.19":"undefined","entrys.sflxjyxy0.17":"0","billType":"CC58A617","entrys.mailenddate0.15":"2021-08-10","entrys.lxsj0.18":"","entrys.ywenddate0.16":"2021-08-10","entryid":"bNsAAAAb/NuaXqib"}
			net.sf.json.JSONObject editJson = net.sf.json.JSONObject.fromObject(editData);
			//Map<String, String> editmap = (Map)editJson;
			  
			 
			//Boolean dd = BillEdit.setEditData(ctx, "bNsAAAAcDFbMWKYX", editmap); 
			
			Map<String, String> editmap = new HashMap<String, String>();
			editmap.put("entryid", "bNsAAAAcDFeaXqib");
			editmap.put("entrys_sfyjbmfzr0_8".replace("_", "."), "是");
			editmap.put("billType", "CC58A617"); 
			//Boolean dd = BillEdit.setEditData(ctx, "bNsAAAAcDFbMWKYX", editmap);
			
			/*Map<String, String> editmap = new HashMap<String, String>();
			editmap.put("billid", "bNsAAAAcDEU8ULTm");
			editmap.put("sfyjbmfzr2_5", "否");
			editmap.put("billType", "3C50B4E6"); 
			Boolean dd = BillEdit.setEditData(ctx, "bNsAAAAcDEU8ULTm", editmap);*/
			
			
			//JSONObject jsonMo = MessageListUtil.getMessageList(ctx, "1003770", 1, 20, "todo", "");
            //System.out.println(jsonMo);
			
			
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAUpWG2XM7x", "69fa4b65-f035-400d-9be5-b5c2f11e262fWFWKITEM");
		 
			Map<String, String> aumap = new HashMap<String, String>();
			aumap.put("isPass", "1");
			//bNsAAAAYgKPXS5kx
			aumap.put("assignId", "2fb3c056-bb6f-44cb-b2ac-32576a0fb97cWFWKITEM"); 
			aumap.put("opinion", "同意222");
			aumap.put("handlerOpinion", "0");
			aumap.put("handlerOpinionStr", "同意"); 
			//BillAudit.audit(ctx, aumap);
			
			 
			//JSONObject json = MYBIllInfoUtil.getBillMap(ctx, "bNsAAAAWEHPA2tAN", "b67aab4d-ff50-4f68-898d-d28b245340fcWFWKITEM");
			
			
			 /*StringBuffer sb = new StringBuffer();
		        sb.append("select userid,orgunitName,personname,personid,potiName from (\n");
		        sb.append("\t\tSELECT top 20 users.fid userid,TOA.Fdisplayname_l2 orgunitName,person.fid personid,TOPO.fname_l2 potiName,person.fname_l2 personname\n");
		        sb.append("\t\tFROM T_BD_PERSON PERSON --\u05b0\u804c\u5458\n");
		        sb.append("\t\tINNER JOIN T_ORG_POSITIONMEMBER TOPM ON TOPM.FPERSONID = PERSON.FID \n");
		        sb.append("\t\tINNER JOIN T_ORG_POSITION TOPO ON TOPM.FPOSITIONID = TOPO.FID --\u05b0\u804c\u4f4d\n");
		        sb.append("\t\tINNER JOIN T_ORG_ADMIN TOA ON TOA.FID = TOPO.FADMINORGUNITID -- \u7ec4\u7ec7\n");
		        sb.append("\t\tINNER join t_pm_user users on users.fpersonid = person.fid\n");
		        sb.append("\t\t WHERE  TOPM.FISPRIMARY = 1 and person.fname_l2 like '%张%' ) b \n");
		        System.out.println(sb.toString());
		        IRowSet rowSet = DbUtil.executeQuery(ctx, sb.toString()); 
		        JSONObject json = new JSONObject();
		        JSONArray ja = new JSONArray();
		        while (rowSet.next()) {
		            JSONObject entryInfo = new JSONObject();
		            String personid = rowSet.getString("personid");
		            String orgunitName = rowSet.getString("orgunitName");
		            String potiName = rowSet.getString("potiName");
		            String personname = rowSet.getString("personname");
		            String userid = rowSet.getString("userid");
		            entryInfo.put("userid", (Object)userid);
		            entryInfo.put("personid", (Object)personid);
		            entryInfo.put("orgunitName", (Object)orgunitName);
		            entryInfo.put("personname", (Object)personname);
		            entryInfo.put("potiName", (Object)potiName);
		            ja.add((Object)entryInfo);
		        }
		        json.put("persons", (Object)ja); */
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 
		Map map = new HashMap();
       /* map.put("assignId", "99e3e7e8-5023-4571-89cd-50c97e6d2539WFWKITEM");
        map.put("signopinion", "加签资");
        map.put("appendMode", "2");
        map.put("routeMode", "2");
        map.put("abortCurrentActivity", "false");
        map.put("activities", "[{\"fpersonid\":\"+im9TCV5QSSAk7UoIKWT6YDvfe0=\",\"fname_l2\":\"张静\",\"activityName\":\"合同管理员\"}]");
        BillPCInfo.appendActivity(ctx, map); */
        
		/*map.put("assignmentId", "6c3b448e-a494-4275-b512-2085b5b839e6WFWKITEM");
		map.put("opinion", "1003770审批");
		map.put("positions", "合同管理员");
        map.put("ids", "+im9TCV5QSSAk7UoIKWT6YDvfe0=");
        map.put("names", "张静");
        map.put("billIds", "bNsAAAAVfkK2XM7x");
        map.put("appendMode", "2");
        map.put("routeMode", "1");  
        map.put("abortCurrentActivity", "false");*/
        
        

		//{billIds=bNsAAAAbZ  2XM7x, abortCurrentActivity=false, names=黎志平;, appendMode=1, routeMode=1, ids=nZo4FjtYRE+SpIzBCs97sYDvfe0=;, positions=印章管理员;, assignmentId=, option=11}
        map.put("assignmentId", "a66140dd-177d-4a0c-ab9e-427e9402aec3WFWKITEM");
		map.put("opinion", "10审批");
		map.put("positions", "印章管理员");
        map.put("ids", "nZo4FjtYRE+SpIzBCs97sYDvfe0=;");
        map.put("names", "黎志平;");
        map.put("billIds", "bNsAAAAbZ  2XM7x");//bNsAAAAbZ++2XM7x
        map.put("appendMode", "1");
        map.put("routeMode", "1");  
        map.put("abortCurrentActivity", "false");
        
        //BillPCInfo.appendActivityNew(ctx, map);
        
        
		
         
		map.put("userID", "ud7/7LXrTtCsoTA2ul4rABO33n8=");
 		map.put("assignId", "79bf7f4d-126e-4d81-a4c6-8f219bd44f50WFWKITEM");
 		map.put("strData", "再次转交1001"); 
         
        
		//BillDeliverTask.deliverTask(ctx, map);
		 
		 
     /*   map.put("isPass", 1); 
        //map.put("assignId", "db2170c8-9dd6-42f8-81d2-0f673dbfbe8aWFWKITEM"); 
        map.put("assignId", "6c3b448e-a494-4275-b512-2085b5b839e6WFWKITEM"); 
        
        map.put("opinion", "测试同意11。");
         
        map.put("handlerOpinion", "0"); 
        map.put("handlerOpinionStr", "同意");
        BillAudit.audit(ctx, map);*/
        
		//BillDeliverTask.deliverTask(ctx, map);
        
        
		/*// TODO Auto-generated method stub
		System.out.print("-----_getAndPutDate");
		//super.getAndPutDate(ctx, data);
		
		String uiStatus ="See";
		
		String actDigest = "";
		int actInstIndex = -1;
		try {  
			String  sqlAss = " select distinct act.FACTDEFID , act.FPROCINSTID "+  
			" FROM t_wfr_actInst ACT "+ //活动实例	
			" INNER JOIN t_WFR_ProcinstRef pr ON act.FPROCINSTID = pr.FPROCINSTID "+  //流程实例引用关系
			" INNER JOIN T_WFR_ProcInst pro ON pro.FPROCINSTID = act.FPROCINSTID "+  //流程实例
			" INNER JOIN T_WFR_Assign detail ON detail.FACTDEFID = act.FACTDEFID AND detail.FPROCINSTID = act.FPROCINSTID	 "+
			"where  detail.FASSIGNID  ='99e3e7e8-5023-4571-89cd-50c97e6d2539WFWKITEM'";
		  
			
			 
			
			String procInstId = "6ec046a1-6127-4c55-87e1-58de38e76f6cWFPCINST"; 
			String actDefId = "73177a06-fbdb-4ef1-96ee-01f88947cdb9WFACTDEF";
			Locale locale = ctx.getLocale();
			
			String msgBuddle = ActivityPropertyController.class.getName();
			ResourceBundle res = ResourceBundle.getBundle(msgBuddle, locale);
			
			EnactmentService enactmentService = new EnactmentService();
			ActivityInstInfo[] actInfos = enactmentService.getActInstMetaByDefId(actDefId, procInstId, true); 
			
			String actInstId = "4bbb37b1-70c8-4490-9b20-d6bca700e687WFATINST"; 
			
			if ((actInfos == null) || (actInfos.length == 0))
				throw new Exception(res.getString("msgNoActInst"));
			if (actInstIndex == -1) {
				actInstIndex = actInfos.length - 1;
				for (int i = 0; i < actInfos.length; ++i) {
					if (actInfos[i].getState().startsWith("open")) {
						actInstIndex = i;
						break;
					}
				}
			}  
			
			ActivityInstInfo info = actInfos[actInstIndex];
			String activityName = info.getActDefName(locale);
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
						String[] webUrlStr  = s.split(".");
						if(webUrlStr.length > 2 ){
							webStr = webUrlStr[webUrlStr.length-2]+"."+webUrlStr[webUrlStr.length-1];
						} 
					}
				}
			} 
		} catch (Exception e) {
			this.logger.error("initialize acitivity property fail.", e);
		}*/
		
	}
 
}