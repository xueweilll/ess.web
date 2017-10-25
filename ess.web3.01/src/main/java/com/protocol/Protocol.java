package com.protocol;

public interface Protocol {
	public byte[] GetReadCommand(String dataType);
	public byte[] GetWriteCommand(String dataType);
	public void Parse(byte[] data);
	
	//public Map<String,String> anlysis(byte[] data);
}
