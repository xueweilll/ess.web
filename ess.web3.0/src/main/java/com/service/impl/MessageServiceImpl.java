package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.BaseDao;
import com.hibernate.entity.Message;
import com.service.MessageService;

public class MessageServiceImpl implements MessageService {
	@Autowired
	private BaseDao baseDao;
	
	@Override
	public Message msg(String key) {
		return baseDao.findAll("from Message where msgkey="+key, Message.class).get(0);
	}

}
