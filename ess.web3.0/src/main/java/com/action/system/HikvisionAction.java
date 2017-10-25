package com.action.system;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.action.ActionBase;
import com.common.hikvision.HttpClientUtil;
import com.opensymphony.xwork2.ActionContext;

public class HikvisionAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	
	public static final String HOST = "http://192.168.1.26:82/webapi/service/"; //此处替换成平台SDK所在服务器IP与端口
	public static final String APPKEY = "1349788c";//此处替换成申请的appkey
	public static final String SECRET = "3c40e75f57c04f358a2542d12d5d7505";//此处替换成申请的secret
	
	private int pageNo;
	
	private int pageSize;
	
	private Long startDate;
	
	private Long endDate;
	
	private int shiftType;
	private String personName;
	private String groupName;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public int getShiftType() {
		return shiftType;
	}
	public void setShiftType(int shiftType) {
		this.shiftType = shiftType;
	}


	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String attendanceList() throws IOException{
		String method = "att/getPlatAttRecordResult";
		HashMap<String, Object> map= new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("shiftType", shiftType);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		result = HttpClientUtil.doGet(method, map);
		System.out.println(result);
		//HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		//response.getWriter().write(result);  
		//HttpServletResponse response = ServletActionContext.getResponse();
		//response.getWriter().write(result);  
		//writeJson_Str(response, result);
		return "success";
	}
	
	
	
	
	protected void writeJson_Str(HttpServletResponse response, String object) 
	{
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "-1");
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (null != object) {
				response.getWriter().print(object);
			}
		} catch (Exception e) {
			//log.error("查询出错！", e);
			response.setContentType("text/plain;charset=UTF-8");
			response.setStatus(300);
			response.setHeader("Error-Json",
					"{\"code\":\"Exception\",\"msg\":\"系统内部错误！\"}");
		}
	}
	
	
}
