package com.hibernate.entity;

import java.io.Serializable;

public class Message implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 6606753826859770263L;
	private int Id;
	private String msgkey;
	private String msgvalueCH;
	private String msgvalueEN;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMsgkey() {
		return msgkey;
	}

	public void setMsgkey(String msgkey) {
		this.msgkey = msgkey;
	}

	public String getMsgvalueCH() {
		return msgvalueCH;
	}

	public void setMsgvalueCH(String msgvalueCH) {
		this.msgvalueCH = msgvalueCH;
	}

	public String getMsgvalueEN() {
		return msgvalueEN;
	}

	public void setMsgvalueEN(String msgvalueEN) {
		this.msgvalueEN = msgvalueEN;
	}

}
