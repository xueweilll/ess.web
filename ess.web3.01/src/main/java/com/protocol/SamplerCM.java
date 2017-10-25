package com.protocol;

public class SamplerCM {
	private byte[] cmd=new byte[8];
	
	public byte[] SampleHR(int addrModule){
		cmd=ProtocolPUS.GenCmdReadHR(addrModule);
		return cmd;
	}
	public byte[] SampleIR(int addrModule){
		cmd=ProtocolPUS.GenCmdReadIR(addrModule);
		return cmd;
	}
	public byte[] SaveHR(int addrModule,String val,int pos){
		cmd=ProtocolPUS.GenCmdWriteHR(val, pos, addrModule);
		return cmd;
	}
	/*public static void main(String[] args){
		//byte[] test = new SamplerCM().SampleHR(1);
		//byte[] test = new SamplerCM().SampleIR(3);
		byte[] test  = new SamplerCM().SaveHR(1, "1", ProtocolPUS.POS_TARGET_VOLTAGE);
		for(int i=0;i<test.length;i++){
			System.out.println(test[i]);
		}
	}*/
}
