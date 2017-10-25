package com.message;

import com.sys.Paramers;

public class ResMgr {
	// private static Parse parse;
	private static String language;
	private static ResMgr _resMgr;
	private static Msg msg;

	private ResMgr() throws Exception {
		init();
	}

	private void init() throws Exception {
		// String relativePath=System.getProperty("user.dir");

		// language = System.getProperty("user.language");
		// language = "en";
		language = Paramers.GetInstance().getLanguage();
		switch (language) {
		case "en":
			msg = new MsgEN();
			break;
		default:
			msg = new MsgCH();
			break;
		}
		// language = "en";
		// String
		// relativePath=this.getClass().getResource("str_"+language+".xml").toString().replace("file:/",
		// "").replace("jar:", "");
		//
		// //System.out.println(language);
		// //relativePath+="/src/com/message/str_"+language+".xml";
		// System.out.println(relativePath);
		// parse=new Parse(relativePath);
	}

	public static ResMgr CreateResMgr() throws Exception {
		if (_resMgr == null) {
			_resMgr = new ResMgr();
		}
		return _resMgr;
	}

	public String GetStr(String name) {
		// return parse.GetStr(name);
		return msg.getMsg(name);
	}
	/*
	 * public static void main(String[] args){ String str=""; byte[] b=new
	 * byte[1024]; int n=0; try{ while(true){ System.out.println("«Î ‰»Î£∫");
	 * n=System.in.read(b); str=new String(b,0,n-2); System.out.println(str);
	 * if(str.equalsIgnoreCase("quit")){ break; } } }catch(Exception ex){
	 * ex.printStackTrace(); } //Scanner s=new Scanner(System.in);
	 * 
	 * //str=s.next();
	 * 
	 * try{ System.out.println(ResMgr.CreateResMgr().GetStr(
	 * "LMU_STATUS_EXCEPTION_OVER_CURRENT")); } catch(Exception ex){
	 * ex.printStackTrace(); } }
	 */
}
