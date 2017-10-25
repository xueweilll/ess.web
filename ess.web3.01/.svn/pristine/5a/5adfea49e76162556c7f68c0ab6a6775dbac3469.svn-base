package com.message;

import org.springframework.beans.factory.annotation.Autowired;

//import com.old.domain.Message;
import com.service.MessageService;

public class MsgEN implements Msg {
	@Autowired
	private MessageService messageService;
	@Override
	public String getMsg(String keyvalue) {
		// return Message.GetMsgEN(keyvalue);
		return messageService.msg(keyvalue).getMsgvalueEN();
	}

}
