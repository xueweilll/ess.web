package com.action.limit;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.User;
import com.service.UserService;
public class UserAction extends ActionBase {

	private static final long serialVersionUID = -7316631810538814193L;

	public String user() {
		return "success";
	}

	private List<User> userList;
//	private List<Role> roleList;
	@Autowired
	private UserService userService;
	private int totalCount;
	private int start;
	private int limit;
	private int uid;
	private int roleId;
	private String roleName;
	private String username;
	private String password;
	private String email;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	private User getUser(){
		User user=new User();
		user.setId(uid);
		user.setEmail(email);
		user.setLogintime(new Date());
		user.setPassword(password);
		user.setRid(roleId);
		user.setUsername(username);
		return user;
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	public String ListUser() {
		String hql = "from User where 1=1";
		int firstResult = start;
		int maxResult = limit;
		totalCount = 0;
		try {
			totalCount = userService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}
		userList = userService.listUser(hql, firstResult,
				maxResult);
		return "success";
	}
	
	public String editUser() {
		User user=getUser();
		System.out.println(status);
		if(status==0) {
			//新增用户信息
			userService.saveUser(user);
			SaveSysLogByUser(1,this.getText("PUBLIC_IS")+this.getText("USER_USER")+":"+user.getUsername());
		}else if(status==2) {
			//修改用户信息
			userService.updateUser(user);
			SaveSysLogByUser(1,this.getText("PUBLIC_UP")+this.getText("USER_USER")+":"+user.getUsername());
		}
		return "success";
	}
	
	/**
	 * 删除用户信息
	 * @return
	 */
	public String delUser() {
		User user = userService.user(uid);
		userService.deleteUser(uid);
		SaveSysLogByUser(1,this.getText("PUBLIC_DL")+this.getText("USER_USER")+":"+user.getUsername());
		return "success";
	}
	
}
