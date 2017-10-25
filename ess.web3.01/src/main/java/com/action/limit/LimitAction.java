package com.action.limit;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.action.ActionBase;
import com.hibernate.entity.Menu;
import com.hibernate.entity.Rmlink;
import com.hibernate.entity.Role;
import com.service.MenuService;
import com.service.RmlinkService;
import com.service.RoleService;

public class LimitAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2549574469351565072L;

	public String limit() {
		return "success";
	}

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RmlinkService rmlinkService;
	private List<Role> roleList;
	private List<Menu> menuList;
	private List<Rmlink> rmlinkList;
	// private List<Menu> menuList;
	private int totalCount;
	private int start;
	private int limit;
	private int rid;
	private String roleName;
	private int status;
	private int PareId;
	private int menuId;
	private String menuName;

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Rmlink> getRmlinkList() {
		return rmlinkList;
	}

	public void setRmlinkList(List<Rmlink> rmlinkList) {
		this.rmlinkList = rmlinkList;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	// public List<Menu> getMenuList() {
	// return menuList;
	// }
	// public void setMenuList(List<Menu> menuList) {
	// this.menuList = menuList;
	// }
	public int getPareId() {
		return PareId;
	}

	public void setPareId(int pareId) {
		PareId = pareId;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
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

	private Role getRole() {
		Role role = new Role();
		role.setId(rid);
		role.setRoleName(roleName);
		role.setPareId(PareId);
		return role;
	}

	public String ListRole() {
		String hql = "from Role where 1=1";
		int firstResult = start;
		int maxResult = limit;
		totalCount = 0;
		try {
			totalCount = roleService.count("select count(*) " + hql);
		} catch (Exception ex) {
			ex.printStackTrace();
			totalCount = 0;
		}
		roleList = roleService.Rolelist(hql, firstResult, maxResult);
		for(Role role:roleList){
			role.setRoleName(this.getText(role.getRoleName()));
		}
		return "success";
	}
	
	public String Roles(){
		roleList = roleService.Roles();
		return "success";
	}

	public String editRole() {
		Role role = getRole();
		System.out.println(status);
		if (status == 0) {
			// 新增角色信息
			roleService.saveRole(role);
			SaveSysLogByUser(1,this.getText("PUBLIC_IS")+this.getText("ROLE_ROLE")+":"+role.getRoleName());
		} else if (status == 2) {
			// 修改角色信息
			roleService.updateRole(role);
			SaveSysLogByUser(1,this.getText("PUBLIC_UP")+this.getText("ROLE_ROLE")+":"+role.getRoleName());
		}
		return "success";
	}

	/**
	 * 删除角色信息
	 * 
	 * @return
	 */
	public String delRole() {
		Role role = roleService.role(rid);
		roleService.deleteRole(rid);
		SaveSysLogByUser(1,this.getText("PUBLIC_DL")+this.getText("ROLE_ROLE")+":"+role.getRoleName());
		return "success";
	}

	/**
	 * 取得菜单列表
	 * 
	 * @return
	 */
	public String listMenu() {
		menuList = menuService.menulist();
		for(Menu menu:menuList){
			menu.setMenuname(this.getText(menu.getMenuname()));
		}
		return "success";
	}

	/**
	 * 权限菜单列表--根据角色取得权限菜单
	 */

	private String Ids;

	public String getIds() {
		return Ids;
	}

	public void setIds(String ids) {
		Ids = ids;
	}

	public String limitList() {
		rmlinkList = rmlinkService.Rmlinklist(rid);
		Ids = "";
		Rmlink rmlink;
		for (Iterator<Rmlink> it = rmlinkList.iterator(); it.hasNext();) {
			rmlink = it.next();
			Ids += rmlink.getMid() + ",";
			/* menuName = rmlink.getMenu().getMenuname(); */

		}
		if (Ids.length() > 1) {
			Ids = Ids.substring(0, Ids.length() - 1);
		}
		// System.out.println("菜单列表：id: " + Ids + "名称：" + menuName);
		return "success";
	}

	public String newLimit() {
		rmlinkService.deleteRmlink(rid);
		Rmlink rmlink = new Rmlink();

		if (Ids.length() > 1) {
			Ids = Ids.substring(0, Ids.length() - 1);
		}
		String[] menuId = Ids.split(",");
		for (int i = 0; i < menuId.length; i++) {
			System.out.println(menuId[i]);
			rmlink.setRid(rid);
			rmlink.setMid(Integer.parseInt(menuId[i]));
			rmlinkService.saveRmlink(rmlink);
		}
		return "success";
	}

}
