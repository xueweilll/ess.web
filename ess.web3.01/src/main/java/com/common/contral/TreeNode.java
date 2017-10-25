package com.common.contral;

import java.util.ArrayList;

public class TreeNode {
	private String id;
	private String text;
	private String nodetype;
	private boolean leaf;
	private String hrefTarget;
	private String href;
	private String cls;
	private String parentId;
	private String icon;
	private double lat;
	private double lon;
	private int level;
	private boolean expanded;
	private Object objData;

	public Object getObjData() {
		return objData;
	}

	public void setObjData(Object objData) {
		this.objData = objData;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	private ArrayList<TreeNode> children;
	
	public TreeNode(){
		children = new ArrayList<TreeNode>();
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getNodetype() {
		return nodetype;
	}
	
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	
	public boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getHrefTarget() {
		return hrefTarget;
	}
	
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public String getCls() {
		return cls;
	}
	
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
}
