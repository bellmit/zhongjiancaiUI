package com.kingdee.eas.custom;

import java.lang.reflect.*;
import javax.servlet.*;
import com.kingdee.eas.cp.common.web.util.*;
import com.kingdee.eas.custom.unit.FileUtil;
import com.kingdee.eas.common.*;
import com.kingdee.bos.*;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.ui.face.*;
import com.alibaba.fastjson.*;
import com.kingdee.jdbc.rowset.*;
import com.kingdee.shr.attachment.ISHRAttachmentExt;
import com.kingdee.shr.attachment.SHRAttachmentExtCollection;
import com.kingdee.shr.attachment.SHRAttachmentExtFactory;
import com.kingdee.shr.attachment.SHRAttachmentExtInfo;
import com.kingdee.eas.util.app.*;

import java.util.*;
import java.util.Date;
import java.text.*;

import com.kingdee.eas.base.wssc.*;
import com.kingdee.bos.dao.ormapping.*;
import com.kingdee.bos.dao.*;
import com.kingdee.eas.base.attachment.*;

import java.io.*;
import java.sql.*;

import com.kingdee.bos.workflow.service.ormrpc.*;
import com.kingdee.bos.workflow.*;
import com.kingdee.eas.moya.message.app.dingding.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.*;
import javax.servlet.http.*;

public class BillServlet extends HttpServlet
{
    public void destroy() {
        super.destroy();
    }
    
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("method");
        System.out.print("----------------------name:"+name);
        try {
            Method method = this.getClass().getDeclaredMethod(name, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void getBillInfo(HttpServletRequest request, HttpServletResponse response) throws EASBizException, BOSException, IOException, SQLException {
    	System.out.print("----------------------getBillInfo:request:"+request);
    	
    	System.out.print("----------------------getBillInfo:WebContextUtil.getEasContext(request):"+WebContextUtil.getEasContext(request));
    	
    	
    	System.out.print("----------------------getBillInfo:ctx:"+WebContextUtil.getEasContext(request) == null);
    	
    	Context ctx = WebContextUtil.getEasContext(request);
    	System.out.println("***********getBillInfo****userSql="+ctx.getCaller());
        response.setContentType("application/json; charset=utf-8");
        String billid = request.getParameter("billid");
        billid = billid.replaceAll(" ", "+");
        String sourceID = request.getParameter("sourceID");
        System.out.print("----------------------getBillInfo:billid:"+billid+"----sourceID:"+sourceID);
        JSONObject json = MYBIllInfoUtil.getBillMap(ctx, billid, sourceID);
        response.getWriter().println(json);
    }
    
    protected void audit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Context ctx = WebContextUtil.getEasContext(request);
        String parameter = "";
        JSONObject json = new JSONObject();
        try {
            request.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            int isPass = UIRuleUtil.getInt(request.getParameter("isPass"));
            map.put("isPass", isPass);
            System.out.println("===================isPass:"+isPass); 
            String  billid =  UIRuleUtil.getString((Object)request.getParameter("billid")); 
            
            String assignId = UIRuleUtil.getString((Object)request.getParameter("assignId"));
            map.put("assignId", assignId);
            String opinion = UIRuleUtil.getString((Object)request.getParameter("opinion"));
            map.put("opinion", opinion);
            int handlerOpinion = UIRuleUtil.getInt(request.getParameter("handlerOpinion"));
            map.put("handlerOpinion", handlerOpinion);
            String handlerOpinionStr = UIRuleUtil.getString((Object)request.getParameter("handlerOpinionStr"));
            map.put("handlerOpinionStr", handlerOpinionStr);
            parameter = "assignId:" + assignId + "\nassignId" + assignId + "\nopinion" + opinion + "\nhandlerOpinion" + handlerOpinion + "\nhandlerOpinionStr" + handlerOpinionStr;
           
            String  sqlAss = " SELECT ass.FASSIGNID assId  FROM  T_WFR_Assign ass   where  ass.FASSIGNID  = '"+assignId+"' and ass.FSTATE in (1 ,2 ,32)  ";
            IRowSet rowSet = DbUtil.executeQuery(ctx, sqlAss);
            if(rowSet.size() >0 ){
            	String editData = request.getParameter("editData").replace("_", ".");
                net.sf.json.JSONObject editJson = net.sf.json.JSONObject.fromObject(editData);
    			Map<String, String> editmap = (Map)editJson;
                System.out.println("===================editmap:"+editmap); 
                for (Map.Entry<String, String> entry : editmap.entrySet()) { 
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }  
                Boolean flag = true;
                String  isBiOrEn = "";
                isBiOrEn = request.getParameter("isbioren") == null ?"":(String)request.getParameter("isbioren"); 
                if(!"".equals(isBiOrEn)){
                	flag = BillEdit.setEditData( ctx, billid, editmap);
                }
                if(flag){
                	BillAudit.audit(ctx, map);
                	json.put("result", (Object)"success");
                    System.out.println("\u5ba1\u6279\u5f00\u59cb \u02bc end");
                }else{
                	json.put("result", (Object)"error");
                    System.out.println("\u5ba1\u6279\u5f00\u59cb \u02bc endEdit");
                }  
            }  
//          LogUtil.insertLog(ctx, LogTypeEnum.auditAPP, parameter, "\u6210\u529f");
        }
        catch (IOException e) {
            LogUtil.insertLog(ctx, LogTypeEnum.auditAPP, parameter, "\u5931\u8d25" + e.getMessage());
            System.out.println("\u5ba1\u6279\u5f00\u59cb  end");
            json.put("result", (Object)"error");
        }
        response.getWriter().println(json);
    }
    
    //DeliveController
    
    protected void zj(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Context ctx = WebContextUtil.getEasContext(request);
        String parameter = "";
        JSONObject json = new JSONObject();
        try {
        	System.out.println("===================ctx:"+ctx.getCaller()); 
        	
        	System.out.println("===================assignId:"+request.getParameter("assignId")); 
        	System.out.println("===================strData:"+request.getParameter("strData")); 
        	System.out.println("===================userID:"+request.getParameter("userID")); 
            request.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            String assignId = UIRuleUtil.getString((Object)request.getParameter("assignId"));
            map.put("assignId", assignId);
            String userID = UIRuleUtil.getString((Object)request.getParameter("userID"));
            userID = userID.replaceAll(" ", "+");
            map.put("userID", userID);
            String strData = UIRuleUtil.getString((Object)request.getParameter("strData"));
            map.put("strData", strData);
            parameter = "assignId:" + assignId + "\nuserID:" + userID + "\nstrData" + strData;
            
            System.out.println("--------map:"+map);
            BillDeliverTask.deliverTask(ctx, map);
            json.put("result", (Object)"success");
            //LogUtil.insertLog(ctx, LogTypeEnum.zj, parameter, "\u6210\u529f");
        }
        catch (IOException e) {
            e.printStackTrace();
            json.put("result", (Object)"error");
            LogUtil.insertLog(ctx, LogTypeEnum.zj, parameter, "\u5931\u8d25\uff1a" + e.getMessage());
        }
        response.getWriter().println(json);
    }
    
    //PassAssignmentController
    protected void cy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Context ctx = WebContextUtil.getEasContext(request);
        String parameter = "";
        JSONObject json = new JSONObject();
        try {
        	String assignId = request.getParameter("assignId");
	        String signopinion = request.getParameter("signopinion"); 
	        String appendMode = request.getParameter("appendMode");
	        String routeMode = request.getParameter("routeMode");
	        String abortCurrentActivity = request.getParameter("abortCurrentActivity"); 
	        String billIds = request.getParameter("billIds"); 
	        
	        String ids = request.getParameter("ids"); 
	        String positions = request.getParameter("positions"); 
	        String names = request.getParameter("names"); 
	        
	        Map map = new HashMap();
	        
            map.put("assignmentId", assignId);
            map.put("option", signopinion);
            map.put("appendMode", appendMode);
            map.put("routeMode", routeMode); 
            map.put("billIds", billIds);
            map.put("ids", ids);
            map.put("positions", positions);
            map.put("names", names); 
            map.put("abortCurrentActivity", abortCurrentActivity);  
            parameter = "assignId=" + assignId + "\nsignopinion=" + signopinion + "\npositions=" + positions + 
            "\nids=" + ids + "\nnames=" + names + "\nbillIds=" + billIds  +
            "\nappendMode=" + appendMode + "\nrouteMode=" + routeMode + "\nabortCurrentActivity=" + abortCurrentActivity;
    		
            System.out.println("--------map:"+map);
            BillPassAssign.passAssignment(ctx, map);
            json.put("result", (Object)"success");
            //LogUtil.insertLog(ctx, LogTypeEnum.zj, parameter, "\u6210\u529f");
        }
        catch (Exception e) {
            e.printStackTrace();
            json.put("result", (Object)"error");
            LogUtil.insertLog(ctx, LogTypeEnum.zj, parameter, "\u5931\u8d25\uff1a" + e.getMessage());
        }
        response.getWriter().println(json);
    }
    
    protected void getPersons(HttpServletRequest request, HttpServletResponse response) throws SQLException, BOSException, IOException {
        Context ctx = WebContextUtil.getEasContext(request);
        request.setCharacterEncoding("utf-8");
        String pageString = request.getParameter("page");
        System.out.println("\u83b7\u53d6\u7528\u6237\u5f00\u59cb");
        System.out.println(pageString);
        int pageNo = 1;
        if (pageString != null) {
            pageNo = Integer.parseInt(pageString);
        }
        String pagesizeString = request.getParameter("pagesize");
        System.out.println(pagesizeString);
        int pagesize = 20;
        if (pagesizeString != null) {
            pagesize = Integer.parseInt(pagesizeString);
        }
        String pix = request.getParameter("pix");
        System.out.println(pix);
        if (pix == null || "".equals(pix)) {
            pix = "";
        }
        pix = URLDecoder.decode(pix, "UTF-8");
        IRowSet rowSet = this.getUserRowSet(ctx, pageNo, pagesize, pix);
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
        json.put("persons", (Object)ja);
        response.getWriter().println(json);
    }
    
    public IRowSet getUserRowSet(Context ctx, int start, int end, String pix) throws BOSException {
        StringBuffer sb = new StringBuffer();
        sb.append("select userid,orgunitName,personname,personid,potiName from (\n");
        sb.append("\t\tSELECT top 20 users.fid userid,TOA.Fdisplayname_l2 orgunitName,person.fid personid,TOPO.fname_l2 potiName,person.fname_l2 personname\n");
        sb.append("\t\tFROM T_BD_PERSON PERSON --\u05b0\u804c\u5458\n");
        sb.append("\t\tINNER JOIN T_ORG_POSITIONMEMBER TOPM ON TOPM.FPERSONID = PERSON.FID \n");
        sb.append("\t\tINNER JOIN T_ORG_POSITION TOPO ON TOPM.FPOSITIONID = TOPO.FID --\u05b0\u804c\u4f4d\n");
        sb.append("\t\tINNER JOIN T_ORG_ADMIN TOA ON TOA.FID = TOPO.FADMINORGUNITID -- \u7ec4\u7ec7\n");
        sb.append("\t\tINNER join t_pm_user users on users.fpersonid = person.fid\n");
        sb.append("\t\t WHERE  TOPM.FISPRIMARY = 1 and person.fname_l2 like '%" + pix + "%' ) b \n");
        System.out.println(sb.toString());
        IRowSet rowSet = DbUtil.executeQuery(ctx, sb.toString());
        return rowSet;
    }
    
    protected void getAttList(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            Context ctx = WebContextUtil.getEasContext(request);
            String billid = request.getParameter("attid");
            billid = billid.replaceAll(" ", "+");
            List list = new ArrayList();
            Map map = new HashMap();
            try {
                String sql = "select fid fid,fname_l2 fname,fsimplename fsimplename,fcreatetime from T_BAS_Attachment where fid in (select fattachmentid from T_BAS_BoAttchAsso where fboid = '" + billid + "')";
                IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                while (rowSet.next()) {
                    Map mapAtt = new HashMap();
                    String fid = rowSet.getString("fid");
                    mapAtt.put("fid", fid);
                    String fname = rowSet.getString("fname");
                    String fsimplename = rowSet.getString("fsimplename");
                    mapAtt.put("fname", String.valueOf(fname) + "." + fsimplename);
                    mapAtt.put("fcreatetime", rowSet.getTimestamp("fcreatetime"));
                    list.add(mapAtt);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            map.put("att", list);
            response.getWriter().println("");
        }
        catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        catch (BOSException e3) {
            e3.printStackTrace();
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
    }
    
    protected void viewImages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer imageStr = new StringBuffer();
        imageStr.append("http://196.1.105.122:7002/imageCenter/showDetail/showImage.do?BARCODE=KDPBA12020181011-000001& \n");
        imageStr.append("OP_TYPE=1&EVALUATION=0&CANDOWNPRINT=1&COMMENTTYPE=1&IMAGEOP=0&IS_SHOW_ORTHER=1&USER_ID=huchao& \n");
        imageStr.append("PAGE=1&Time=20190107232353&systype=FK&flowflag=&typename=&Timemd5=3E36E885635C215E033CFBEBD7D54C68 \n");
        imageStr.append("&isapp=2&isattachment=&language=zh_CN \n");
        Context ctx = WebContextUtil.getEasContext(request);
        String parameter = "";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        System.out.println("123123" + dateName);
        String billid = request.getParameter("billid");
        String URL = null;
        try {
            IBillImageFacade billimage = BillImageFacadeFactory.getRemoteInstance();
            String imgurl = URL = billimage.getImageURL((String)null, billid);
            System.out.println("11111111111111111111" + URL);
        }
        catch (BOSException e1) {
            e1.printStackTrace();
        }
        JSONObject json = new JSONObject();
        try {
            request.setCharacterEncoding("utf-8");
            Map map = new HashMap();
            String assignId = UIRuleUtil.getString((Object)request.getParameter("assignId"));
            map.put("assignId", assignId);
            String userID = UIRuleUtil.getString((Object)request.getParameter("userID"));
            userID = userID.replaceAll(" ", "+");
            map.put("USER_ID", userID);
            System.out.println("\u5f71\u50cf111111111111" + billid + assignId + userID);
            json.put("result", (Object)URL);
        }
        catch (IOException e2) {
            e2.printStackTrace();
            json.put("result", (Object)"error");
            LogUtil.insertLog(ctx, LogTypeEnum.zj, parameter, "\u5931\u8d25\uff1a" + e2.getMessage());
        }
        response.getWriter().println(json);
    }
    
    protected void initAtt(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            Context ctx = WebContextUtil.getEasContext(request);
            String easHome = System.getProperty("EAS_HOME");
            String path = String.valueOf(easHome) + "/server/deploy/eas.ear/cp_web.war/upload/";
            String attid = request.getParameter("attid");
            attid = attid.replaceAll(" ", "+");
            AttachmentInfo attInfo = AttachmentFactory.getLocalInstance(ctx).getAttachmentInfo((IObjectPK)new ObjectUuidPK(attid));
            String fname = attInfo.getName();
            String fsimplename = attInfo.getSimpleName();
            fname = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
            String name = String.valueOf(fname) + "." + fsimplename;
            byte[] fille = attInfo.getFile();
            File file = new File(String.valueOf(path) + name);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fille);
            fos.close();
            Map map = new HashMap();
            map.put("name", attInfo.getName());
            map.put("fileExt", fsimplename);
            map.put("size", attInfo.getSize());
            map.put("filename", fname);
            response.getWriter().println("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (BOSException e2) {
            e2.printStackTrace();
        }
        catch (EASBizException e3) {
            e3.printStackTrace();
        }
    }
    
    protected void initContractAtt(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            Context ctx = WebContextUtil.getEasContext(request);
            String easHome = System.getProperty("EAS_HOME");
            String path = String.valueOf(easHome) + "/server/deploy/eas.ear/cp_web.war/upload/";
            String attid = request.getParameter("attid");
            attid = attid.replaceAll(" ", "+");
            String name = "";
            String fsimplename = "";
            String fname = new StringBuilder(String.valueOf(System.currentTimeMillis())).toString();
            byte[] fille = null;
            try {
                String sql = "select ffileType,fcontentFile from T_CON_ContractContent where fid = '" + attid + "'";
                IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
                while (rowSet.next()) {
                    fsimplename = rowSet.getString("ffileType");
                    Blob blob = rowSet.getBlob("fcontentFile");
                    BufferedInputStream is = null;
                    try {
                        is = new BufferedInputStream(blob.getBinaryStream());
                        byte[] bytes = new byte[(int)blob.length()];
                        for (int len = bytes.length, offset = 0, read = 0; offset < len && (read = is.read(bytes, offset, len - offset)) >= 0; offset += read) {}
                        fille = bytes;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        try {
                            is.close();
                            is = null;
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        continue;
                    }
                    finally {
                        try {
                            is.close();
                            is = null;
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    try {
                        is.close();
                        is = null;
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            catch (SQLException e3) {
                e3.printStackTrace();
            }
            name = String.valueOf(fname) + "." + fsimplename.substring(fsimplename.lastIndexOf(".") + 1);
            File file = new File(String.valueOf(path) + name);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fille);
            fos.close();
            Map map = new HashMap();
            map.put("name", fsimplename);
            map.put("fileExt", fsimplename.substring(fsimplename.lastIndexOf(".") + 1));
            map.put("filename", fname);
            response.getWriter().println("");
        }
        catch (IOException e4) {
            e4.printStackTrace();
        }
        catch (BOSException e5) {
            e5.printStackTrace();
        }
    }
    
    protected void assignCountersign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = "";
        Context ctx = WebContextUtil.getEasContext(request);
        JSONObject json = new JSONObject();
        try {
            String assignId = request.getParameter("assignId");
            String comments = request.getParameter("comments");
            String personIds = request.getParameter("personIdArray");
            parameter = "assignId:" + assignId + "\ncomments:" + comments + "\npersonIdArray" + personIds;
            System.out.println(parameter);
            String[] personIdArray = personIds.split(";");
            BillPCInfo.assignCountersign(ctx, assignId, comments, personIdArray);
            json.put("result", (Object)"success");
            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, "\ufffd\u0279\ufffd");
        }
        catch (BOSException e) {
            json.put("result", (Object)"error");
            json.put("msg", (Object)e.getMessage());
            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, e.getMessage());
        }
        response.getWriter().println(json);
    }

    protected void appendActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = "";
        Context ctx = WebContextUtil.getEasContext(request);
        JSONObject json = new JSONObject();
        
        System.out.println("---------parameter:"+parameter);
//        try {
        	
            
            /*
            String assignId = request.getParameter("assignId");
            String signopinion = request.getParameter("signopinion");
            String activities = request.getParameter("activities");
            String appendMode = request.getParameter("appendMode");
            String routeMode = request.getParameter("routeMode");
            String abortCurrentActivity = request.getParameter("abortCurrentActivity"); 
            Map map = new HashMap();
            
            map.put("assignId", assignId);
            map.put("signopinion", signopinion);
            map.put("appendMode", appendMode);
            map.put("routeMode", routeMode);
            map.put("abortCurrentActivity", abortCurrentActivity);
            map.put("activities", activities);
             parameter = "assignId=" + assignId + "\nsignopinion=" + signopinion + "\nactivities=" + activities + "\nappendMode=" + appendMode + "\nrouteMode=" + routeMode + "\nabortCurrentActivity=" + abortCurrentActivity;
             */ 
	        String assignId = request.getParameter("assignId");
	        String signopinion = request.getParameter("signopinion"); 
	        String appendMode = request.getParameter("appendMode");
	        String routeMode = request.getParameter("routeMode");
	        String abortCurrentActivity = request.getParameter("abortCurrentActivity"); 
	        String billIds = request.getParameter("billIds"); 
	        
	        String ids = request.getParameter("ids"); 
	        String positions = request.getParameter("positions"); 
	        String names = request.getParameter("names"); 
	        
	        Map map = new HashMap();
	        
            map.put("assignmentId", assignId);
            map.put("option", signopinion);
            map.put("appendMode", appendMode);
            map.put("routeMode", routeMode); 
            map.put("billIds", billIds);
            map.put("ids", ids);
            map.put("positions", positions);
            map.put("names", names); 
            map.put("abortCurrentActivity", abortCurrentActivity);  
            parameter = "assignId=" + assignId + "\nsignopinion=" + signopinion + "\npositions=" + positions + 
            "\nids=" + ids + "\nnames=" + names + "\nbillIds=" + billIds  +
            "\nappendMode=" + appendMode + "\nrouteMode=" + routeMode + "\nabortCurrentActivity=" + abortCurrentActivity;
    		
            System.out.println("---------parameter:"+parameter);
            System.out.println("---------map:"+map);
            System.out.println(parameter);
            try {
            	Map returnMap = BillPCInfo.appendActivityNew(ctx, map);
            	
            	if(returnMap.get("status") == null || returnMap.get("status").toString().equals("false")){
            		json.put("result", (Object)"error");
    	            json.put("msg", "加签失败");
    	            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, "加签失败");
            	}else{
            		json.put("result", (Object)"success");
            	}
			} catch (BOSException e) {
	            json.put("result", (Object)"error");
	            json.put("msg", (Object)e.getMessage());
	            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, e.getMessage());
			}
			
//            String[] personIdArray = personIds.split(";");
//            BillPCInfo.assignCountersign(ctx, assignId, comments, personIdArray);
            //json.put("result", (Object)"success");
//            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, "\ufffd\u0279\ufffd");
//        }
//        catch (BOSException e) {
//            json.put("result", (Object)"error");
//            json.put("msg", (Object)e.getMessage());
//            LogUtil.insertLog(ctx, LogTypeEnum.HQ, parameter, e.getMessage());
//        }
        response.getWriter().println(json);
    }
    
    protected void setNextPerson(HttpServletRequest request, HttpServletResponse response) {
        Context ctx = WebContextUtil.getEasContext(request);
        System.out.println("setNextPerson================================");
        String assignId = UIRuleUtil.getString((Object)request.getParameter("assignId"));
        String nextPerson = UIRuleUtil.getString((Object)request.getParameter("nextPerson"));
        try {
            IEnactmentService server = null;
            if (ctx != null) {
                server = EnactmentServiceFactory.createEnactService(ctx);
            }
            else {
                server = EnactmentServiceFactory.createRemoteEnactService();
            }
            ArrayList<String> actInstList = new ArrayList();
            AssignmentInfo assignmentInfo = server.getAssignmentById(assignId);
            actInstList.add(assignmentInfo.getActInstId());
            Map uiContext = new HashMap();
            uiContext.put("DesignatePerformerUI.BASE_ACT_ID", actInstList);
            String[] actInstIds = actInstList.toArray(new String[actInstList.size()]);
            uiContext.put("actInstIds", actInstIds);
            ArrayList list = new ArrayList();
            String[] persons = nextPerson.split(",");
            for (int i = 0; i < persons.length; ++i) {
                System.out.println(persons[i]);
                list.add(persons[i]);
            }
            DesignatePerformerFacadeFactory.getLocalInstance(ctx).setNextPerson(actInstList, actInstIds, list);
            Map resMap = new HashMap();
            resMap.put("success", "success");
            response.getWriter().println("");
        }
        catch (WfException e1) {
            e1.printStackTrace();
        }
        catch (BOSException e2) {
            e2.printStackTrace();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
    }
    
    protected void getMessageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getMessageList================================");
        long startTime = System.currentTimeMillis();
        try {
            Context ctx = WebContextUtil.getEasContext(request);
            System.out.println("***********getMessageList****userSql="+ctx.getCaller());
            int page = UIRuleUtil.getInt(request.getParameter("page"));
            int size = UIRuleUtil.getInt(request.getParameter("size"));
            String msgtype = UIRuleUtil.getString((Object)request.getParameter("msgtype"));
            String mobile = UIRuleUtil.getString((Object)request.getParameter("mobile"));
            String pix = UIRuleUtil.getString((Object)request.getParameter("pix"));
            System.out.println("\u83b7\u53d6\u6d88\u606f\u53c2\u6570\uff1a" + msgtype + mobile + pix + "111111\u7528\u623732222222222222" );
            JSONObject json = MessageListUtil.getMessageList(ctx, mobile, page, size, msgtype, pix);
            System.out.println("getMessageList================================json:"+json);
            response.getWriter().println(json);
             
        }
        catch (BOSException e) {
            e.printStackTrace();
            throw new RuntimeException((Throwable)e);
        }
        catch (SQLException e2) {
            e2.printStackTrace();
            throw new RuntimeException(e2);
        }
        catch (IOException e3) {
            e3.printStackTrace();
            throw new RuntimeException(e3);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        MYTimeLogUtil.printTime(startTime, "get messageList");
    }
    
    public static String sendInfoPost(String url,String param) throws IOException {
    	 
        CloseableHttpClient httpclient = HttpClients.createDefault();
 
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
 
        String textMsg = param;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        String result = null;
        org.apache.http.HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            result= EntityUtils.toString(response.getEntity(), "utf-8");
        }else{
            result= EntityUtils.toString(response.getEntity(), "utf-8");
        }
 
        return result;
    } 
    protected void getDDMobile(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        System.out.println("getDD================================");
        String code = UIRuleUtil.getString((Object)request.getParameter("code")); 
        String mobile = ""; 
        try {
            String token = Notifies.getToken();
            String userInfo = Notifies.getUserId(code, token);
            System.out.println("userInfo================================userInfo="+userInfo);
            JSONObject idInfo = JSONObject.parseObject(userInfo);
            if (idInfo.getInteger("errcode") != 0) {
                System.out.println(idInfo.getString("errmsg"));
                throw new RuntimeException(idInfo.getString("errmsg"));
            }
            String userid = idInfo.getString("userid");
            userInfo = Notifies.getUser(userid, token);
            System.out.println("userInfo================================userInfo="+userInfo);
            JSONObject info = JSONObject.parseObject(userInfo);
            if (idInfo.getInteger("errcode") != 0) {
                System.out.println(idInfo.getString("errmsg"));
                throw new RuntimeException(idInfo.getString("errmsg"));
            }
            mobile = info.getString("mobile");
            System.out.println("getDD================================"+info.getString("mobile"));
            System.out.println("getDD===============22================="+info.getString("jobnumber"));
            System.out.println("getDD===============33================="+info.getString("job_number"));
            
            mobile = info.getString("jobnumber")== null ? info.getString("job_number"): info.getString("jobnumber"); 
            System.out.println("getDD================================"+mobile);
            response.getWriter().println(mobile); 
        }
        
        catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException((Throwable)e);
        }
        catch (URISyntaxException e2) {
            e2.printStackTrace();
            throw new RuntimeException(e2);
        }
        catch (IOException e3) {
            e3.printStackTrace();
            throw new RuntimeException(e3);
        }
        catch (Exception e4) {
            e4.printStackTrace();
            throw new RuntimeException(e4);
        }
        MYTimeLogUtil.printTime(startTime, "dingding get mobile");
    }
    
    protected void getQywxMobile(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        System.out.println("getQywxMobile================================");
        Context ctx = WebContextUtil.getEasContext(request);
        String code = UIRuleUtil.getString((Object)request.getParameter("code"));
        String mobile = "";
        try {
            HttpSession session = request.getSession();
            Object codeObject = session.getAttribute("code");
            System.out.println("session id :" + session.getId());
            System.out.println("\u7f13\u5b58\u7684\u624b\u673a\u53f7\u7801\uff1a" + codeObject);
            System.out.println("getQywxMobile:" + code);
            String sql = "select * from ct_cus_code_mobile where code = '" + code + "'";
            IRowSet rowSet = DbUtil.executeQuery(ctx, sql);
            if (rowSet.next()) {
                String mobileString = rowSet.getString("mobile");
                response.getWriter().println(mobileString);
            } else {
                String token = com.kingdee.eas.moya.message.app.wechat.comp.Notifies.getToken();
                mobile = com.kingdee.eas.moya.message.app.wechat.comp.Notifies.getUserPhone(code, token);
                session.setAttribute("code", (Object)mobile);
                sql = "insert into ct_cus_code_mobile (code,mobile) values ('" + code + "','" + mobile + "')";
                DbUtil.execute(ctx, sql.toString());
                response.getWriter().println(mobile);
            }
        }catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException((Throwable)e);
        }catch (URISyntaxException e2) {
            e2.printStackTrace();
            throw new RuntimeException(e2);
        }catch (IOException e3) {
            e3.printStackTrace();
            throw new RuntimeException(e3);
        }catch (Exception e4) {
            e4.printStackTrace();
            throw new RuntimeException(e4);
        }
        MYTimeLogUtil.printTime(startTime, "wechat get mobile");
    }
    
    public void init() throws ServletException {
    }
    
    
    /*@RequestMapping(value = "upload/{whatFile}")
    public String upload(@PathVariable("whatFile") String whatFile, Model model, @RequestParam("file") MultipartFile file,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
    		
    	//文件上传...
     
    	return "";
    }*/
    public void upload( HttpServletRequest request, HttpServletResponse response) throws IOException {
    		
    	System.out.println("upload==upload=====upload======upload=====upload==upload=upload===========");
    	//文件上传...
    	/*
    	String savePath = "/tmp/";//存储路径 
        String retMsg = "";//定义将返回给客户端的信息 
        try { 
          if (ServletFileUpload.isMultipartContent(request)) { 
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request); 
            for (FileItem item : items) { 
              if (!item.isFormField()) {// 过滤掉表单中非文件域 
                String fileType = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();//文件类型 
                String fileName = new Date().getTime() + "." + fileType; //保存的文件名 
                String filePath = savePath + "\\" + fileName; //保存的文件路径 
                
                System.out.println("------------------fileType:"+fileType);
                
                System.out.println("------------------fileType:"+fileType);
                
                System.out.println("------------------filePath:"+filePath);
                BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流 
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));// 获得文件输出流 
                org.apache.commons.fileupload.util.Streams.copy(in, out, true);// 开始把文件写到指定的上传文件夹 
                retMsg += "上传文件成功！"; 
                in.close(); 
                out.close(); 
              }  
            } 
          } 
          response.setContentType("text/html;charset=utf8"); 
          PrintWriter pw = response.getWriter(); 
          pw.print(retMsg); 
          pw.flush(); 
          pw.close(); 
          //根据自己需要返回页面一个 retMsg 　//　return retMsg 证明上传成功
          
        } catch (Exception e) { 
          e.printStackTrace(); 
          return "error";
        }
        return "success";*/
    	
    	String uploadPath = "/tmp/"; // 上传文件的目录
    	File tempPathFile = new File(uploadPath); 
    	
    	
    	String billid = request.getParameter("billid");
    	
    	System.out.println("-------------------path.exists():"+tempPathFile.exists());
    	System.out.println("-------------------billid:"+billid);
    	
    	 
    	
    	try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
 
			// Set factory constraints
			factory.setSizeThreshold(40960); // 设置缓冲区大小，这里是40kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录
 
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
 
			// Set overall request size constraint
			upload.setSizeMax(41943040); // 设置最大文件尺寸，这里是40MB
  
			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件 
			for(int i = 0 ; i < items.size() ; i++){
				FileItem fi = items.get(i);
				if(fi.getName() == null ){
					System.out.println("----------11---------fi.getName():"+fi.getName()); 
					billid = new String(fi.getString().getBytes(), "utf-8");
					System.out.println("----------11---------billid:"+billid);
				} 
				break;
			}
			
			Iterator<FileItem> i = items.iterator();
			 
			while (i.hasNext()) {
				 
				FileItem fi = (FileItem) i.next();  
				System.out.println("-------------------fi:"+fi);
				String fileName = fi.getName(); 
				System.out.println("-------------------fileName:"+fileName);
				if (fileName != null) {
					File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题
					File savedFile = new File(uploadPath, fullFile.getName());
					fi.write(savedFile);
					Context ctx = WebContextUtil.getEasContext(request); 
					//String id = FileUtil.upload(ctx,billid, uploadPath, fullFile.getName());
					String id = FileUtil.upload(ctx,billid, savedFile);
					System.out.print("id====:"+id);
					response.getWriter().println(id); 
				}
			}
			System.out.print("upload succeed");
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.print("error :"+ e.getMessage()); 
		}   
    }
    
    
    
    //删除文件
    public void deleteFile( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
    	System.out.println("deleteFile==deleteFile=====deleteFile======deleteFile=====deleteFile");
    	Context ctx = WebContextUtil.getEasContext(request);  
    	
    	
    	String id = request.getParameter("thisid"); 
    	System.out.println("deleteFile==deleteFile=====deleteFile======deleteFile=====thisid:"+id);
		/*try {
			IBoAttchAsso iBoAttchAsso = BoAttchAssoFactory.getLocalInstance(ctx);
			
			 EntityViewInfo view = new EntityViewInfo();
		        FilterInfo filter = new FilterInfo();
		        filter.getFilterItems().add(new FilterItemInfo("ATTACHMENT",  id));
		        view.setFilter(filter);
		        BoAttchAssoCollection coll =  iBoAttchAsso.getBoAttchAssoCollection(view); //查询所关联附件
		        if( null ==  (coll)){
		        	System.out.print("null ==  (coll) :");
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
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("error :"+ e.getMessage()); 
		}  */
    	
    	try { 
			
	        
	        
	        EntityViewInfo view2 = new EntityViewInfo();
	        ISHRAttachmentExt iSHRAttachmentExt =  SHRAttachmentExtFactory.getLocalInstance(ctx);
	        FilterInfo filter2 = new FilterInfo();
	        filter2.getFilterItems().add(new FilterItemInfo("id", id));
	        view2.setFilter(filter2);
	        SHRAttachmentExtCollection coll2 =  iSHRAttachmentExt.getSHRAttachmentExtCollection(view2); //查询所关联附件
	        if( null ==  (coll2)){
	        	System.out.print("null ==  (coll2) :");
	        }
	        
	        IObjectPK[] pks2 = new ObjectUuidPK[coll2.size()];
	        for(int i = 0; i < coll2.size(); i++){  
	        	SHRAttachmentExtInfo shrAttachmentExtInfo =coll2.get(i); //附件对象
	            pks2[i] = new ObjectUuidPK(shrAttachmentExtInfo.getId());
	            
	            IAttachment iAttachment =  AttachmentFactory.getLocalInstance(ctx);
				EntityViewInfo view = new EntityViewInfo();
		        FilterInfo filter = new FilterInfo();
		        filter.getFilterItems().add(new FilterItemInfo("id",  shrAttachmentExtInfo.getAttachment().getId().toString()));
		        view.setFilter(filter);
		        AttachmentCollection coll =  iAttachment.getAttachmentCollection(view); //查询所关联附件
		        if( null ==  (coll)){
		        	System.out.print("null ==  (coll) :");
		        }
		        
		        IObjectPK[] pks = new ObjectUuidPK[coll.size()];
		        for(int j = 0; j < coll.size(); j++){  
		              AttachmentInfo attachment =coll.get(j); //附件对象
		              pks[j] = new ObjectUuidPK(attachment.getId());
		              
		        }
		        iAttachment.delete(pks); //删除附件    
	        }
	        iSHRAttachmentExt.delete(pks2); //删除附件 
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("error :"+ e.getMessage()); 
		}  
    }
    
}
