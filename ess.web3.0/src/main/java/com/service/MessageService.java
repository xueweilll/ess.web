package com.service;

import com.hibernate.entity.Message;

public interface MessageService {
	Message msg(String key);
}
