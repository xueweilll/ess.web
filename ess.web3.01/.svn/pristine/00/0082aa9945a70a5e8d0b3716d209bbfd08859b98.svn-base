package com.common.contral;

import java.util.ArrayList;
import java.util.List;

import com.action.ActionBase;
import com.hibernate.entity.Menu;
import com.service.MenuService;

public class MenuTree extends ActionBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TreeNode> arrList;
	private List<Menu> listMenu;
	public MenuTree(MenuService menuService) {
		listMenu=menuService.menulist();
	}
	
	public ArrayList<TreeNode> CreateMenuTree(){
		arrList = new ArrayList<TreeNode>();
		TreeNode treeNode;
		int i=0;
		for(Menu menu:listMenu){
			if(menu.getPareid()==0){
				treeNode = new TreeNode();
				treeNode.setId(String.valueOf(menu.getId()));
				treeNode.setLeaf(true);
				treeNode.setText(this.getText(menu.getMenuname()));
				treeNode.setObjData(menu.getUrl());
				treeNode.setCls("menu");
				treeNode.setIcon("image/menu"+i+".png");
				i++;
				arrList.add(MenuNode(treeNode));
			}
		}
		return arrList;
	}
	
	private TreeNode MenuNode(TreeNode tn) {
		TreeNode treeNode;
		for (Menu menu:listMenu) {
			if (String.valueOf(menu.getPareid()).equals(tn.getId())) {
				tn.setLeaf(false);
				treeNode = new TreeNode();
				treeNode.setId(String.valueOf(menu.getId()));
				treeNode.setText(this.getText(menu.getMenuname()));
				treeNode.setLeaf(true);
				treeNode.setObjData(menu.getUrl());
				//treeNode=DtuNode(treeNode);
				tn.getChildren().add(MenuNode(treeNode));
			}
		}
		return tn;
	}
}
