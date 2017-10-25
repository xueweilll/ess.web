package com.service;

import java.util.List;

import com.hibernate.entity.LmuGroup;

public interface LmuGroupService {
	public List<LmuGroup> Grouplist();
 	public LmuGroup lmuGroup(int id);
 	public void saveGroup(LmuGroup lmuGroup);
 	public void updateGroup(LmuGroup lmuGroup);
 	public void deleteGroup(int id);
 	//public Integer[] LmuIds(int id);
}
