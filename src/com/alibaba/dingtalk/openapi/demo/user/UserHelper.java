package com.alibaba.dingtalk.openapi.demo.user;

import java.util.List;
import java.util.Map;

import com.alibaba.dingtalk.openapi.demo.Env;
import com.alibaba.dingtalk.openapi.demo.OApiException;
import com.alibaba.dingtalk.openapi.demo.utils.FileUtils;
import com.alibaba.dingtalk.openapi.demo.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetByMobileRequest;
import com.dingtalk.api.response.OapiUserGetByMobileResponse;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.CorpUserBaseInfo;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.dingtalk.open.client.api.model.corp.CorpUserDetailList;
import com.dingtalk.open.client.api.model.corp.CorpUserList;
import com.dingtalk.open.client.api.service.corp.CorpUserService;
import com.taobao.api.ApiException;

/**
 * ͨѶ¼��Ա��صĽӿڵ���
 */
public class UserHelper {


    /**
     * ���������Ȩ���ѯ����û�userId
     *
     * @param accessToken
     * @param code
     * @return
     * @throws Exception
     */
    public static CorpUserBaseInfo getUserInfo(String accessToken, String code) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getUserinfo(accessToken, code);
    }

    /**
     * ������ҵ��Ա
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s1
     */
    public static String createUser(String accessToken, CorpUserDetail userDetail) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        JSONObject js = (JSONObject) JSONObject.parse(userDetail.getOrderInDepts());
        Map<Long, Long> orderInDepts = FileUtils.toHashMap(js);

        String userId = corpUserService.createCorpUser(accessToken, userDetail.getUserid(), userDetail.getName(), orderInDepts,
                userDetail.getDepartment(), userDetail.getPosition(), userDetail.getMobile(), userDetail.getTel(), userDetail.getWorkPlace(),
                userDetail.getRemark(), userDetail.getEmail(), userDetail.getJobnumber(),
                userDetail.getIsHide(), userDetail.getSenior(), userDetail.getExtattr());

        // Ա��Ψһ��ʶID
        return userId;
    }


    /**
     * ���³�Ա
     * <p>
     * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&docType=1#s2
     */
    public static void updateUser(String accessToken, CorpUserDetail userDetail) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        JSONObject js = (JSONObject) JSONObject.parse(userDetail.getOrderInDepts());
        Map<Long, Long> orderInDepts = FileUtils.toHashMap(js);

        corpUserService.updateCorpUser(accessToken, userDetail.getUserid(), userDetail.getName(), orderInDepts,
                userDetail.getDepartment(), userDetail.getPosition(), userDetail.getMobile(), userDetail.getTel(), userDetail.getWorkPlace(),
                userDetail.getRemark(), userDetail.getEmail(), userDetail.getJobnumber(),
                userDetail.getIsHide(), userDetail.getSenior(), userDetail.getExtattr());
    }


    /**
     * ɾ����Ա
     */
    public static void deleteUser(String accessToken, String userid) throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        corpUserService.deleteCorpUser(accessToken, userid);
    }


    //��ȡ��Ա
    public static CorpUserDetail getUser(String accessToken, String userid) throws Exception {

        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getCorpUser(accessToken, userid);
    }

    //����ɾ����Ա
    public static void batchDeleteUser(String accessToken, List<String> useridlist)
            throws Exception {
        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        corpUserService.batchdeleteCorpUserListByUserids(accessToken, useridlist);

    }


    //��ȡ���ų�Ա
    public static CorpUserList getDepartmentUser(
            String accessToken,
            long departmentId,
            Long offset,
            Integer size,
            String order)
            throws Exception {

        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getCorpUserSimpleList(accessToken, departmentId,
                offset, size, order);
    }


    //��ȡ���ų�Ա�����飩
    public static CorpUserDetailList getUserDetails(
            String accessToken,
            long departmentId,
            Long offset,
            Integer size,
            String order)
            throws Exception {

        CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
        return corpUserService.getCorpUserList(accessToken, departmentId,
                offset, size, order);
    }


    /**
     * �����̨���ʱͨ��CODE��ȡ΢Ӧ�ù���Ա�������Ϣ
     *
     * @param ssoToken
     * @param code
     * @return
     * @throws OApiException
     */
    public static JSONObject getAgentUserInfo(String ssoToken, String code) throws OApiException {
        String url = Env.OAPI_HOST + "/sso/getuserinfo?" + "access_token=" + ssoToken + "&code=" + code;
        JSONObject response = HttpHelper.httpGet(url);
        return response;
    }

    
    /**
     * �����̨���ʱͨ��CODE��ȡ΢Ӧ�ù���Ա�������Ϣ
     *
     * @param ssoToken
     * @param code
     * @return
     * @throws OApiException
     */
    public static String getUserIdByMobile(String accessToken, String mobile) throws OApiException {
    	System.out.println("*******************************dingding    1");
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get_by_mobile");
        System.out.println("*******************************dingding    2");
        OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
        System.out.println("*******************************dingding    3");
        request.setMobile(mobile);
        System.out.println("*******************************dingding    4");
        String userId = null;
        System.out.println("*******************************dingding    5");
        OapiUserGetByMobileResponse response;
        System.out.println("*******************************dingding    6");
		try {
			System.out.println("*******************************dingding    ��ʼ�����ֻ��Ų�ѯid");
			response = client.execute(request, accessToken);
			System.out.println("*******************************dingding    response="+response);
			if(null != response && response.getErrcode()==0){
				JSONObject obj = JSONObject.parseObject(response.getBody());
	            userId = obj.getString("userid");
			}
			 
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("*******************************dingding    e2="+e.getMessage());
			return null;
		}

        return userId;
    }


}
