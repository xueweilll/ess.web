package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.LogType;
import com.service.LogTypeService;

@Service
public class LogTypeServiceImpl implements LogTypeService {
	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	@Override
	public List<LogType> LogTypelist() {
		return baseDao.findAll("from LogType",LogType.class);
	}

}
