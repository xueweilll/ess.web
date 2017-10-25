package com.common.Interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.hibernate.entity.Rmlink;

public class PermissionInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/*
		 * Object user = ActionContext.getContext().getSession().get("user");
		 * if(user!=null) return invocation.invoke(); HttpServletRequest request
		 * = ServletActionContext.getRequest(); HttpServletResponse response =
		 * ServletActionContext.getResponse();
		 * response.setContentType("text/html");
		 * response.setCharacterEncoding("UTF-8"); PrintWriter out =
		 * response.getWriter();
		 * out.println("<script>alert('���¼���ٲ���!');window.parent.location='" +
		 * request.getContextPath() + "/login.html'</script>"); out.flush();
		 * out.close();
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (JudgeLogin()) {
			if (JudgeLimit()) {
				return invocation.invoke();
			} else {
				out.println("<script>Ext.Msg.alert('您所在用户组无权限访问此页面，如需访问请通知管理员修改权限!')</script>");
			}
		} else {
			out.println("<script>window.parent.location='"
					+ request.getContextPath() + "/login.html'</script>");
		}
		out.flush();
		out.close();
		return null;
	}

	private boolean JudgeLogin() {
		Object user = ActionContext.getContext().getSession().get("user");
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean JudgeLimit() {
		String url = ActionContext.getContext().getName();
		List<Rmlink> list = null;
		try {
			Object obj = ActionContext.getContext().getSession().get("rmlink");
			list = (List<Rmlink>) obj;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (list != null) {
			for (Rmlink rmlink : list) {
				if (rmlink.getMenu().getUrl().equals(url)) {
					return true;
				}
			}
		}
		return false;

	}
}
