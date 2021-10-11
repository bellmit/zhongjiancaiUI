package com.kingdee.eas.moya.common.sso.util;

import javax.servlet.http.*;

public class WorkflowUtil
{
    public static String getUrl(final HttpServletRequest request) {
        final String assignmentId = request.getParameter("assignmentId");
        final String redirectTo = request.getParameter("redirectTo");
        System.out.println("assignmentId=\t" + assignmentId);
        if (Strs.isEmpty(assignmentId)) {
            return redirectTo;
        }
        ApiUtil.isMobileDevice(request);
        return "/my_app/workflow.do?method=approve&assignmentId=" + assignmentId;
    }
}
