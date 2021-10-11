package com.kingdee.eas.moya.message.app.dingding;

import com.alibaba.dingtalk.openapi.demo.Env;
import com.alibaba.dingtalk.openapi.demo.auth.*;
import com.alibaba.dingtalk.openapi.demo.department.*;
import com.dingtalk.open.client.api.model.corp.*;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Head;
import com.alibaba.dingtalk.openapi.demo.message.LightAppMessageDelivery;
import com.alibaba.dingtalk.openapi.demo.message.MessageHelper;
import com.alibaba.dingtalk.openapi.demo.message.MessageHelper.Receipt;
import com.alibaba.dingtalk.openapi.demo.user.*;
import com.alibaba.fastjson.*;
import java.util.*;
import com.taobao.api.*;
import com.dingtalk.api.*;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import org.apache.http.util.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.*;
import java.net.*;
import org.apache.http.client.*;
import java.io.*;
import com.dingtalk.open.client.api.model.corp.MessageBody.OABody.Body;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.kdf.data.logging.Logger;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;

public final class Notifies
{
    public static String getToken() throws Exception {
    	System.out.println("*******************************getToken()=");
    	String token = AuthHelper.getAccessToken();
    	System.out.println("*******************************getToken() token="+token);
        return AuthHelper.getAccessToken();
    }
    
    public static String getUserId(final String mobile) throws Exception {
        String accessToken = AuthHelper.getAccessToken();
        System.out.println("*******************************dingding    accessToken="+accessToken);
    	
        String userId = UserHelper.getUserIdByMobile(accessToken, mobile);
        System.out.println("*******************************dingding    userId="+userId);
        return userId;
    }
    
    public static JSONArray getAllUserInfo(final Set<String> existIds) throws Exception {
        final String token = getToken();
        List<Department> departments = new ArrayList<Department>();
        departments = DepartmentHelper.listDepartments(token, "1");
        final JSONArray retArr = new JSONArray();
        for (int i = 0; i < departments.size(); ++i) {
            long offset = 0L;
            int size = 5;
            CorpUserList corpUserList = new CorpUserList();
            while (true) {
                corpUserList = UserHelper.getDepartmentUser(AuthHelper.getAccessToken(), Long.valueOf(departments.get(i).getId()), offset, size, null);
                if (!corpUserList.isHasMore()) {
                    break;
                }
                offset += size;
            }
            if (corpUserList.getUserlist().size() != 0) {
                for (int j = 0; j < corpUserList.getUserlist().size(); ++j) {
                    String user = JSON.toJSONString(corpUserList.getUserlist().get(j));
                    JSONObject userObj = JSONObject.parseObject(user);
                    String userid = userObj.getString("userid");
                    if (existIds == null || !existIds.contains(userid)) {
                        String userInfo = getUser(userid, token);
                        retArr.add((Object)JSONObject.parseObject(userInfo));
                    }
                }
            }
        }
        return retArr;
    }
    
    public static JSONObject sendWorkRecord(String mobile, String title, String url, String content) {
    	
    	System.out.println("*******************************dingding    mobile="+mobile);
    	
        String userid=null;
        String token=null;
		try {
			userid = getUserId(mobile);
			token = getToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*******************************dingding    exce="+e.getMessage());
		}        
        
        System.out.println("*******************************dingding    userid="+userid);
        System.out.println("*******************************dingding    token="+token);
     // 创建oa消息
        MessageBody.OABody oaBody = new MessageBody.OABody();
        oaBody.setMessage_url(url);
        Head head = new Head();
        head.setText(title);
        head.setBgcolor("FFBBBBBB");
        oaBody.setHead(head);
        Body body = new Body();
        body.setAuthor("eas");
        body.setTitle(title);
        body.setContent(content);
        oaBody.setBody(body);
        
        LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(userid, "", Env.AGENT_ID);

        lightAppMessageDelivery.withMessage(MessageType.OA, oaBody);
        Receipt rsp = null;
		try {
			if(null != userid){
				rsp = MessageHelper.send(token, lightAppMessageDelivery);
			}		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*******************************dingding    e1="+e.getMessage());
		}
        
        return null;
    }
    
    public static JSONObject sendWorkRecord(String mobile, String title, String content) {
    	
    	System.out.println("*******************************dingding    mobile="+mobile);
    	
        String userid=null;
        String token=null;
		try {
			userid = getUserId(mobile);
			token = getToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*******************************dingding    exce="+e.getMessage());
		}        
        
        System.out.println("*******************************dingding    userid="+userid);
        System.out.println("*******************************dingding    token="+token);
     // 创建oa消息
        MessageBody.OABody oaBody = new MessageBody.OABody();
        Head head = new Head();
        head.setText(title);
        head.setBgcolor("FFBBBBBB");
        oaBody.setHead(head);
        Body body = new Body();
        body.setAuthor("eas");
        body.setTitle(title);
        body.setContent(content);
        oaBody.setBody(body);
       
        LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(userid, "", Env.AGENT_ID);

        lightAppMessageDelivery.withMessage(MessageType.OA, oaBody);
        Receipt rsp = null;
		try {
			if(null != userid){
				rsp = MessageHelper.send(token, lightAppMessageDelivery);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*******************************dingding    e1="+e.getMessage());
		}
        return null;
    }
    
    
    public static JSONObject updateWordRecord(String userName, String recordId) throws Exception {
        String token = getToken();
        String userId = getUserId(userName);
        
//        // 创建oa消息
//        MessageBody.OABody oaBody = new MessageBody.OABody();
////        oaBody.setMessage_url(url);
//        Head head = new Head();
//        head.setText("审批完成");
//        head.setBgcolor("FFBBBBBB");
//        oaBody.setHead(head);
//        Body body = new Body();
//        body.setAuthor("eas");
//        body.setTitle(title);
//        body.setContent(content);
//        oaBody.setBody(body);
//        
//        LightAppMessageDelivery lightAppMessageDelivery = new LightAppMessageDelivery(userid, "", Env.AGENT_ID);
//
//        lightAppMessageDelivery.withMessage(MessageType.OA, oaBody);
//        Receipt rsp = null;
//		try {
//			rsp = MessageHelper.send(token, lightAppMessageDelivery);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("*******************************dingding    e1="+e.getMessage());
//		}
        
        
        DingTalkClient client = (DingTalkClient)new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
        OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
        req.setUserid(userId);
        req.setRecordId(recordId);
        OapiWorkrecordUpdateResponse rsp = (OapiWorkrecordUpdateResponse)client.execute((TaobaoRequest)req, token);
        return JSONObject.parseObject(rsp.getBody());
    }
    
    public static String getUserId(String code, String accessToken) throws URISyntaxException, ClientProtocolException, IOException, ApiException {
        StringBuffer url = new StringBuffer("https://oapi.dingtalk.com/user/getuserinfo");
        url.append("?access_token=").append(accessToken);
        url.append("&code=").append(code);
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(url.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse res = httpClient.execute((HttpUriRequest)httpGet);
        HttpEntity resEntity = res.getEntity();
        String content = EntityUtils.toString(resEntity, "utf-8");
    	
    	/*DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
		OapiUserGetuserinfoRequest req = new OapiUserGetuserinfoRequest();
		req.setCode(code);
		req.setHttpMethod("GET");
		OapiUserGetuserinfoResponse rsp = client.execute(req, accessToken);
		String content = rsp.getBody();*/
        return content;
    }
    
    public static String getUser(String userid, String accessToken) throws URISyntaxException, ClientProtocolException, IOException {
        StringBuffer url = new StringBuffer("https://oapi.dingtalk.com/user/get");
        url.append("?access_token=").append(accessToken);
        url.append("&userid=").append(userid);
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI(url.toString()));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse res = httpClient.execute((HttpUriRequest)httpGet);
        HttpEntity resEntity = res.getEntity();
        String content = EntityUtils.toString(resEntity, "utf-8");
        return content;
    }
    
    public static String sendTask(Context ctx, String userNumber ,String yidongUrl ,String pcUrl ,
    		String title,String body,String sourceID) { 
        
		try {
			String accessToken = getToken();
			//String accessToken = "9f246788f92035f2a9f58e6b49ddc931";
	        if(StringUtils.isEmpty(sourceID)){
	            return "";
	        }
	        String ddid = "";
	        String cell = "";
	        String perNum = "";
	        String sql = " select person.fnumber as PERNUM , person.cfdduserid as DDID, person.FCELL as CELLNUM from t_bd_person  person  INNER JOIN  T_WFR_Assign  assi   on  assi.FPERSONEMPID = person.fid where assi.fAssignid ='"+sourceID+"' ";
	        IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
			System.out.println("---------======sql:"+sql);
		    System.out.println("---------======rowSet:"+rowSet.size());
		    while (rowSet.next()) {
	        	ddid = rowSet.getString("DDID");
	        	cell = rowSet.getString("CELLNUM");
	        	perNum =  rowSet.getString("PERNUM");
	        }	
	 
	        System.out.println("---------======ddidddid:"+ddid);
	        System.out.println("---------======cell:"+cell);
	        if(  ddid == null || ddid.equals("") || !IsExistUserByNumber(ddid,accessToken)   ){
	        	 //根据电话号码获取发送人id
	        	 System.out.println("---------======cellcellcell:"+cell);
	            DingTalkClient mobilClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_by_mobile");
	            OapiUserGetByMobileRequest useReq = new OapiUserGetByMobileRequest();
	            useReq.setMobile(cell);
	            useReq.setHttpMethod("GET");
	            OapiUserGetByMobileResponse useRsp = mobilClient.execute(useReq, accessToken);
	            //获取到Urid就是在公司里要发送到那个人的id
	            ddid = useRsp.getUserid();
	        }
	        System.out.println("---------======dingding:"+ddid);
	       
	        OapiWorkrecordAddRequest req = new OapiWorkrecordAddRequest();
	        req.setUserid(ddid);
	        
	        req.setCreateTime(new Date().getTime());
	        req.setTitle(title);
	        req.setUrl(yidongUrl);
	        //req.setPcUrl(pcUrl);
	        List<OapiWorkrecordAddRequest.FormItemVo> list2 = new ArrayList<OapiWorkrecordAddRequest.FormItemVo>();
	        OapiWorkrecordAddRequest.FormItemVo obj3 = new OapiWorkrecordAddRequest.FormItemVo();
	        list2.add(obj3);
	        //obj3.setTitle(title);
	        obj3.setTitle("\b\b类型");
	        System.out.println("---------======\b\b类型:"+sourceID);
	        System.out.println("---------======BODY:"+body.split("//n")[0].split("/\\*")[0]);  
	        obj3.setContent(body.split("//n")[0].split("/\\*")[0]);  
	        req.setFormItemList(list2);
	        //发起人id
	        req.setOriginatorUserId(ddid);
	        req.setSourceName("审批~");
	        req.setPcOpenType(2L);
	        //流程业务id&#xff0c;避免多个业务冲突
	        req.setBizId(sourceID);
	        System.out.println("---------======setBizId:"+sourceID);
	        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/add");
	        System.out.println("---------======accessToken:"+accessToken);
	        OapiWorkrecordAddResponse rsp = client.execute(req, accessToken);
	        System.out.println("---------======rsp.getBody():"+rsp.getBody()); 
	        
	        JSONObject bodyJson = JSONObject.parseObject(rsp.getBody());
	          
	        System.out.println("任务ID：" + sourceID);
	        StringBuffer sqlIn = new StringBuffer();
	        sqlIn.append("insert into CT_CUS_Task(fid,cfassignid,cfrecordid,cfddid,fname_l2)");
	        sqlIn.append(" values(");
	        sqlIn.append("  newbosid('4499254A') ,'" + sourceID+ "','"+bodyJson.get("record_id")+"' , '"+ddid+"'  , '"+perNum+"'");
	        sqlIn.append(")");
	        System.out.println("---------======sqlIn:"+sqlIn); 
	        DbUtil.execute(ctx, sqlIn.toString());
	        return rsp.getBody();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sourceID;
       
        
    }
    
  //判断员工是否存在
    public static boolean IsExistUserByNumber(String number, String accessToken) throws  Exception { //true  存在
    	 
    	DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
		OapiV2UserGetRequest req = new OapiV2UserGetRequest();
 		req.setUserid(number);
 		OapiV2UserGetResponse rsp = client.execute(req, accessToken);
 		
 		JSONObject json = JSONObject.parseObject(rsp.getBody());  
 		
 		if(  null !=json &&  null != json.get("errcode")  ){
 			if( !"".equals(json.get("errcode")) && !"0".equals(json.get("errcode").toString())    ){ // && "60121".equals(json.get("errcode").toString())
 				System.out.println(number+"----------------IsExistUserByNumber:"+false);
 				return  false;//说明不存在
 	 		}
 		}else{
 			return  false;//说明不存在
 		} 
 		System.out.println(number+"----------------IsExistUserByNumber:"+true);
    	return  true; 
    }
    
    
}
