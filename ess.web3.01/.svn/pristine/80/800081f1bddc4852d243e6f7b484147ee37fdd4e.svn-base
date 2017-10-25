package com.action;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.hibernate.entity.Rmlink;
import com.hibernate.entity.User;
import com.service.RmlinkService;
import com.service.UserService;


public class LoginAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		return "success";
	}
	
	private String username;
	private String password;
	private String resStr;
	private boolean resBool;
	@Autowired
	private UserService userService;
	@Autowired
	private RmlinkService rmlinkService;
	private List<Rmlink> listRmlink;
	
	public String getResStr() {
		return resStr;
	}

	public boolean getResBool() {
		return resBool;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRmlinkService(RmlinkService rmlinkService) {
		this.rmlinkService = rmlinkService;
	}
	 */
	public void setListRmlink(List<Rmlink> listRmlink) {
		this.listRmlink = listRmlink;
	}
	
	public String LoginForm( ) throws IOException{	
		if(username.isEmpty()){
        	resBool = false;
        	resStr = "用户名不能为空！";
        	return "success";
        }
        if(password.isEmpty()){
        	resBool = false;
        	resStr = "密码不能为空！";
        	return "success";
        }
      //  HttpServletRequest req = ServletActionContext.getRequest();
       // HttpServletResponse res =ServletActionContext.getResponse();
   
		User user = new User(username,password);
		User login=userService.login(user);
	  //  String s1 = req.getHeader("user-agent");
	    
			if(login!=null){
			//	if(s1.contains("okhttp/3.2.0")) {
					//res.getWriter().write("sussess");
					
			//	}  
				session.put("user",login);
				listRmlink = rmlinkService.Rmlinklist(login.getRid());
				session.put("rmlink",listRmlink);
				SaveSysLog(1,login.getUsername()+":"+this.getText("INDEX_LOGIN_RSIII"));
				resBool = true;
		        
			}else{
			//	if(s1.contains("okhttp/3.2.0")) {
					//res.getWriter().write("error");
		    //} 
				resBool = false;
				resStr = "用户名或密码错误！请重试！";
				//res.getWriter().write("success");
			}
			return "success";
	}
}
