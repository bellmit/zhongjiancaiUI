package com.kingdee.eas.custom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.base.ssc.UserInfo;
import com.kingdee.eas.basedata.person.PersonCollection;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.moya.common.PropUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class MessageListUtil {
	public static JSONObject getMessageList(Context ctx, String phone,
			int page, int size, String msgtype,
			String pix) throws BOSException, SQLException, ParseException {
		IEAIDataBase idbf = EAIDataBaseFactory.getLocalInstance(ctx);
		EntityViewInfo viewInfo = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("dataBaseType.name", "SQL",CompareType.EQUALS));
		viewInfo.setFilter(filter);
		EAIDataBaseCollection coll = idbf.getEAIDataBaseCollection(viewInfo);
		String dbCenterNumber = "";
		String sql = "";
		System.out.println("MessageListUtil====getMessageList====msgtype:"+msgtype);
		
		
		phone = phone.replace("\\r", "");
		
		String countSql = "SELECT count(1) todoCount FROM T_WFR_Assign ass left join T_PM_USER us on ass.fpersonuserid = us.fid "
			+"where  ass.FBIZOBJID is not null and us.fnumber ='"+ phone.trim()+"' and  ass.FSTATE in (1 ,2 ,32) and ass.fsubject_l2 like '%"+ pix+ "%'";
		
		if ("todo".equals(msgtype)) {
			sql = " /*dialect*/ SELECT * "
				+"FROM (SELECT tt.*, ROWNUM AS rowno "
				+"FROM ( SELECT ass.FASSIGNID assId,ass.FBIZOBJID assObjId,to_char(ass.fcreatedtime,'yyyy-mm-dd hh24:mi:ss') createdTime,us.fnumber faccount,ass.fsubject_l2 content, ass.fbody_l1 BODY "
				+"FROM  T_WFR_Assign ass "+"left join T_PM_USER us on ass.fpersonuserid = us.fid "
				+"where  ass.FBIZOBJID is not null and us.fnumber ='"+ phone.trim()  
				+"' and  ass.FSTATE in (1 ,2 ,32)  and ass.fsubject_l2 like '%"+ pix+ "%'"
				+"order by ass.FCREATEDTIME desc "+") tt "
				+"WHERE ROWNUM <= " + size*page + ") table_alias "+"WHERE table_alias.rowno > "+ size*(page-1);
			
//			sql = " SELECT top "
//					+ size
//					+ " ass.FASSIGNID assId,ass.FBIZOBJID assObjId,ass.fcreatedtime createdTime,us.fnumber faccount,ass.fsubject_l2 content FROM  T_WFR_Assign ass "
//					+ " left join T_PM_USER us on ass.fpersonuserid = us.fid "
//					+ " where us.fnumber = '"
//					+ phone.trim()
//					+ "' and ass.FBIZOBJID is not null and ass.fsubject_l2 like '%"
//					+ pix
//					+ "%'"
//					+ " and ass.FASSIGNID not in (SELECT top "
//					+ size * page 
//					+ " ass.FASSIGNID assId FROM  T_WFR_Assign ass "
//					+ " left join T_PM_USER us on ass.fpersonuserid = us.fid "
//					+ " where us.fnumber = '"
//					+ phone.trim()
//					+ "' and ass.FBIZOBJID is not null and ass.fsubject_l2 like '%"
//					+ pix + "%'" + " order by ass.fcreatedtime desc)"
//					+ " order by ass.fcreatedtime desc";
		} else if("done".equals(msgtype)) {
			
			sql = "SELECT * "+"FROM (SELECT tt.*, ROWNUM AS rowno "
				//+"FROM ( SELECT ass.FASSIGNID assId,ass.FBIZOBJID assObjId,to_char(ass.fcreatedtime,'yyyy-mm-dd hh24:mi:ss') createdTime,us.fnumber faccount,ass.fsubject_l2 content , ass.fbody_l2 BODY "
				+"FROM ( SELECT ass.FASSIGNID assId,ass.FBIZOBJID assObjId,to_char(ass.FENDTIME,'yyyy-mm-dd hh24:mi:ss') createdTime,us.fnumber faccount,ass.fsubject_l2 content , ass.fbody_l2 BODY "
			
				+"FROM  t_wfr_assigndetail ass "+"left join T_PM_USER us on ass.fpersonuserid = us.fid "
				+"where  ass.FBIZOBJID is not null and us.fnumber ='"+ phone.trim()+"' and  ass.FSTATE !=4 and ass.fsubject_l2 like '%"
				+ pix+ "%'"+"order by ass.FENDTIME desc "+") tt "
				+"WHERE ROWNUM <= " + size*page + ") table_alias "
				+"WHERE table_alias.rowno > "+ size*(page-1);
			
			/*sql = "SELECT * "+"FROM (SELECT tt.*, ROWNUM AS rowno "
			+" FROM   ( select * from  ( SELECT ass.FASSIGNID assId,ass.FBIZOBJID assObjId,to_char(ass.FENDTIME,'yyyy-mm-dd hh24:mi:ss') createdTime,us.fnumber faccount,ass.fsubject_l2 content "
			+" FROM  t_wfr_assigndetail ass "+"left join T_PM_USER us on ass.fpersonuserid = us.fid where  ass.FBIZOBJID is not null and us.fnumber ='"+ phone.trim()+"' and  ass.FSTATE !=4 and ass.fsubject_l2 like '%"+ pix+"%'"
			+" UNION SELECT ass.FASSIGNID assId,ass.FBIZOBJID assObjId,to_char(ass.fcreatedtime,'yyyy-mm-dd hh24:mi:ss') createdTime,us.fnumber faccount,ass.fsubject_l2 content "
			+" FROM  T_WFR_Assign ass left join T_PM_USER us on ass.fpersonuserid = us.fid where  ass.FBIZOBJID is not null and us.fnumber ='"+ phone.trim()+"' and  ass.FSTATE not in (1 ,2 ,32)  and ass.fsubject_l2 like '%"+ pix+"%' "
			+ " )  order by  createdTime desc ) tt "
			+"WHERE ROWNUM <= " + size*page + ") table_alias "
			+"WHERE table_alias.rowno > "+ size*(page-1);*/
		
			
//			sql = " SELECT top "
//					+ size
//					+ " ass.FASSIGNID assId,ass.FBIZOBJID assObjId,ass.fcreatedtime createdTime,us.fnumber faccount,ass.fsubject_l2 content FROM  t_wfr_assigndetail ass "
//					+ " left join T_PM_USER us on ass.fpersonuserid = us.fid "
//					+ " where us.fnumber = '"
//					+ phone.trim()
//					+ "'  and ass.FBIZOBJID is not null and ass.fsubject_l2 like '%"
//					+ pix
//					+ "%' and "
//					+ " ass.FASSIGNID not in (SELECT top "
//					+ size * page 
//					+ " ass.FASSIGNID assId FROM  t_wfr_assigndetail ass "
//					+ " left join T_PM_USER us on ass.fpersonuserid = us.fid "
//					+ " where us.fnumber = '"
//					+ phone.trim()
//					+ "'  and ass.FBIZOBJID is not null and ass.fsubject_l2 like '%"
//					+ pix + "%' order by ass.fcreatedtime desc)"
//					+ " order by ass.fcreatedtime desc";
		} 
		
		System.out.println("\u83b7\u53d6\u6d88\u606fSQL\uff1a" + page+ ":size:" + size + ";123" + sql);
		JSONObject json = new JSONObject();
		
		
		if ("todo".equals(msgtype) || "done".equals(msgtype)) {
			int todoCount = 0;
			if (coll != null && coll.size() > 0) {
				
//				IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
//				json.put("count", (Object) rowSet.size());
				JSONArray ja = new JSONArray();
				
				String rootUrl = PropUtil.getParameterByName("eas.rooturl");
				for (Iterator it = coll.iterator(); it.hasNext();) {
					EAIDataBaseInfo dbInfo = (EAIDataBaseInfo) it.next();
					dbCenterNumber = dbInfo.getNumber();
					
					Map<String,Object> countResult = EAISynTemplate.queryRowOne(ctx, dbCenterNumber, countSql);
					
					System.out.println("******************toduCount="+countResult.get("TODOCOUNT"));
					System.out.println("******************toduCount="+countResult.get("TODOCOUNT").toString());
					int todoCountTemp =Integer.parseInt(countResult.get("TODOCOUNT").toString());
					todoCount += todoCountTemp;
					/*List<Map<String, Object>> list = EAISynTemplate.query(ctx,dbCenterNumber, sql);
					for (Map<String, Object> map : list) {
						JSONObject jsonTemp = new JSONObject();
						jsonTemp.put("title", map.get("CONTENT"));
						String body = map.get("BODY")==null? "":map.get("BODY").toString();  
						Clob bb = (Clob) map.get("BODY");
						String dd = clobToString(bb);    
						String[] arr = body.split("类型");
						if(arr.length >  1 ){
							jsonTemp.put("content", "类型:"+arr[1]);
						}else{
							jsonTemp.put("content", "类型:"+arr[0]);
						}
						
						//jsonTemp.put("time", map.get("CREATEDTIME"));
						System.out.println("********************time = " +map.get("CREATEDTIME"));
						System.out.println("********************time = " +map.get("CREATEDTIME").toString());
						String faccount = map.get("FACCOUNT").toString(); 
						
						jsonTemp.put("time", getTimeSub(map.get("CREATEDTIME").toString()));
						
						
						String assId = map.get("ASSID").toString();
						String assObjId = map.get("ASSOBJID").toString();
						String link = String.valueOf(rootUrl)+ "/portal/UserAccountValidate.jsp?";
						link = String.valueOf(link) + "dataCenter=" + dbCenterNumber + "&userNumber=" + faccount+ "&billid=" + assObjId;
						link = String.valueOf(link) + "&assignID=" + assId; 
						System.out.println("*************url="+link);
						
						//link = String.valueOf(rootUrl)+"/my_app/page/fee.html?billid="+assObjId+"&assignId="+assId+"&nid="+userid;
						
						System.out.println("*************newurl="+link);
						//"/my_app/page/fee.html?billid="+billid+"&assignId="+assignID;
						jsonTemp.put("url", (Object) link);
						ja.add((Object) jsonTemp);
					}*/
					
					IRowSet row = DbUtil.executeQuery(ctx, sql );
			    	while (row.next()) { 
			    		JSONObject jsonTemp = new JSONObject();
						jsonTemp.put("title", row.getString("CONTENT"));
						String body = row.getString("BODY")==null? "":row.getString("BODY").toString();    
						
						System.out.println("********************body ="+body);
						
						String[] arr = body.split("类型"); 
						if(arr.length >  1 ){
							jsonTemp.put("content", "类型:"+arr[1].split("//n")[0].split("/\\*")[0]);  
							/*System.out.println("********************arr[1] ="+arr[1].split("//n")[0]);
							System.out.println("********************arr[1] ="+arr[1]);
							System.out.println("********************arr[1] ="+arr[1].split("/*")[0]);
							System.out.println("********************arr[1] ="+arr[1].split("value")[0]);*/
						}else{
							if(body.indexOf("】") >=0){
								jsonTemp.put("content", "类型:"+body.split("】")[0]+"】");
							}else{
								jsonTemp.put("content", "类型:"+arr[0].split("//n")[0].split("/\\*")[0]);
							}
							
						}
						
						//jsonTemp.put("time", map.get("CREATEDTIME"));
						System.out.println("********************time = " +row.getString("CREATEDTIME"));
						//System.out.println("********************time = " +row.getString("CREATEDTIME").toString());
						String faccount = row.getString("FACCOUNT"); 
						
						jsonTemp.put("time", getTimeSub(row.getString("CREATEDTIME").toString()));
						
						
						String assId = row.getString("ASSID");
						String assObjId = row.getString("ASSOBJID");
						String link = String.valueOf(rootUrl)+ "/portal/UserAccountValidate.jsp?";
						link = String.valueOf(link) + "dataCenter=" + dbCenterNumber + "&userNumber=" + faccount+ "&billid=" + assObjId;
						link = String.valueOf(link) + "&assignID=" + assId; 
						System.out.println("*************url="+link);
						
						//link = String.valueOf(rootUrl)+"/my_app/page/fee.html?billid="+assObjId+"&assignId="+assId+"&nid="+userid;
						
						System.out.println("*************newurl="+link);
						//"/my_app/page/fee.html?billid="+billid+"&assignId="+assignID;
						jsonTemp.put("url", (Object) link);
						ja.add((Object) jsonTemp); 
			    	}
				}
				json.put("count", todoCount);
				json.put("rows", (Object) ja);
			}
		}else {
			//本地配置的UI
			String uiInfoListSql = "select CFAuditTitle,FDescription from CT_CUS_UITool";
		    /*if (coll != null && coll.size() > 0) {
				
//				IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
//				json.put("count", (Object) rowSet.size());
				JSONArray ja = new JSONArray();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
				String rootUrl = PropUtil.getParameterByName("eas.rooturl");
				for (Iterator it = coll.iterator(); it.hasNext();) {
					EAIDataBaseInfo dbInfo = (EAIDataBaseInfo) it.next();
					dbCenterNumber = dbInfo.getNumber();
					
					List<Map<String, Object>> billTypeList = EAISynTemplate.query(ctx,dbCenterNumber, uiInfoListSql);
					
					for (Map<String, Object> billType : billTypeList) {
						String tableName = billType.get("FDESCRIPTION").toString();
						String billTypeName = billType.get("CFAUDITTITLE").toString();
						String listSql = "SELECT * FROM (SELECT tt.*, ROWNUM AS rowno FROM (select t1.FID, t1.FNumber,to_char(t1.FCreateTime,'yyyy-mm-dd hh24:mi:ss') createdTime,'"+billTypeName+"' as billType,t2.FNumber as FAccount from " + tableName + " t1 inner join t_pm_user t2 on t1.FCreatorID = t2.FID where t2.fcell='"+phone.trim()+"' and t1.FNumber like '%"+pix+"%'"+" order by t1.FCreateTime desc ) tt "
						+" WHERE ROWNUM <= " + size*page + ") table_alias "+" WHERE table_alias.rowno > "+ size*(page-1);

						List<Map<String, Object>> list = EAISynTemplate.query(ctx,dbCenterNumber, listSql);
						
						System.out.println("********************listSql = " +listSql);
						System.out.println("********************list.size = " +list.size());
						for (Map<String, Object> map : list) {
							JSONObject jsonTemp = new JSONObject();
							jsonTemp.put("title", map.get("BILLTYPE"));
							jsonTemp.put("content", map.get("FNUMBER"));
							jsonTemp.put("time", map.get("CREATEDTIME"));
							System.out.println("********************time = " +map.get("CREATEDTIME"));
							System.out.println("********************time = " +map.get("CREATEDTIME").toString());
							String faccount = map.get("FACCOUNT").toString();
							String assId = map.get("FID").toString();
							String assObjId = map.get("FID").toString();
							String link = String.valueOf(rootUrl)+ "/portal/UserAccountValidate.jsp?";
							link = String.valueOf(link) + "dataCenter=" + dbCenterNumber + "&userNumber=" + faccount+ "&billid=" + assObjId;
							link = String.valueOf(link) + "&assignID=" + assId;
							System.out.println("*************url="+link);
							jsonTemp.put("url", (Object) link);
							ja.add((Object) jsonTemp);
						}
					}
				}
				json.put("rows", (Object) ja);
			}*/
		}
		
		return json;
	}
	
	private static String  getTimeSub(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date getdate = sdf.parse(dateStr);
	    Date date = new Date();
	    long from2 = getdate.getTime();  
	    long now = date.getTime();  
	    int hours = (int) ((now - from2) / (1000 * 60 * 60));
	    System.out.println("两个时间之间的小时差为：" + hours); 
	    if(hours > 12){
	    	return dateStr;
	    }else if(hours == 0){
	    	hours = (int) ((now - from2) / (1000 * 60 ));
	    	return hours+"分钟前";
	    }
	    return hours+"小时前";
	}
	
    
	public static String clobToString(  Clob clob)  { 
		String reString = ""; 
		Reader is = null; 
		try { 
		is = clob.getCharacterStream(); 
		} catch (SQLException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
		// 得到流 
		BufferedReader br = new BufferedReader(is); 
		String s = null; 
		try { 
		s = br.readLine(); 
		} catch (IOException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
		StringBuffer sb = new StringBuffer(); 
		while (s != null) { 
		// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING 
		sb.append(s); 
		try { 
		s = br.readLine(); 
		} catch (IOException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
		} 
		reString = sb.toString(); 
		return reString; 
	} 
    

}
