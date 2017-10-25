package com.hibernate.entity;

public class Lglink {
	private int Id;
	private int Gid;
	private int Lid;

	// private Group group;
	// private LMU lmu;
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getGid() {
		return Gid;
	}

	public void setGid(int gid) {
		Gid = gid;
	}

	public int getLid() {
		return Lid;
	}

	public void setLid(int lid) {
		Lid = lid;
	}
	/*
	 * public Group getGroup() { return group; } public void setGroup(Group
	 * group) { this.group = group; } public LMU getLmu() { return lmu; } public
	 * void setLmu(LMU lmu) { this.lmu = lmu; }
	 */
}
