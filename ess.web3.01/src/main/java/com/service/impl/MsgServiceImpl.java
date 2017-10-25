package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Msg;
import com.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService {
	
	@Autowired
	private BaseDao baseDao;
	/*public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}*/
	@Override
	public void saveMsg(Msg msg) {
		baseDao.save(msg);
	}

}
