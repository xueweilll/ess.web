package com.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("portocolFactory")
public class PortocolFactory {
	
	@Autowired
	NQProtocol nQProtocol;
	
	@Autowired
	WSProtocol wSProtocol;
	
	@Autowired
	SDProtocol sdProtocol;
	
	public YZCProtocol getProtocol(int code){
		switch (code) {
		case 2:
			//氨气
			return nQProtocol;
		case 1:
			//温湿
			return wSProtocol;
		case 3:
			//市电
			return sdProtocol;
		default:
			return null;
		}
	}
}
