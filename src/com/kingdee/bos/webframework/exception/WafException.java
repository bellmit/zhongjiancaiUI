package com.kingdee.bos.webframework.exception;

import com.kingdee.bos.webframework.context.WafContext;
import javax.servlet.http.HttpServletResponse;

public class WafException extends RuntimeException {
	private String detail = null;

	public WafException() {
	}

	public WafException(String message) {
		super(message);
		appendErrorHead(message);
	}

	public WafException(String message, Throwable cause) {
		super(message, cause);
		appendErrorHead(message);
	}

	public WafException(Throwable cause) {
		super(cause);
		appendErrorHead(cause.toString());
	}

	protected void appendErrorHead(String message) {
		HttpServletResponse response = (HttpServletResponse) WafContext
				.getInstance().getResponse();
		response.setContentType("text/html;charset=UTF-8");
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return this.detail;
	}
}