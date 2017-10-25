package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hibernate.entity.DTU;
import com.hibernate.entity.Sensor;
import com.unit.MessageQueue;

public class QuartzJob {
	/*
	 * @Autowired DtuService dtuService;
	 * 
	 * private CoreSocketService coreSocketService;
	 * 
	 * public void work() { System.out.println("Quartz的任务调度！！！");
	 * coreSocketService = CoreSocketService.Instance();
	 * if(!coreSocketService.isIsrun()){ coreSocketService.start(); } }
	 */

	// private static final String host = "112.21.191.49";
	private static final String url = "http://112.21.191.49:8088/zhny/api/sensor.info";

	/**
	 * 农委上传数据
	 *//*
	public void work() {
		ConcurrentHashMap<DTU, Sensor> cacheData = messageQueue.getCacheData();
		DTU dtu;
		Sensor sensor;
		for (Map.Entry<DTU, Sensor> e : cacheData.entrySet()) {
			dtu = e.getKey();
			sensor = e.getValue();
			try {
				post(sensorJson(dtu.getCode(), sensor));
			} catch (ClientProtocolException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}*/

	@Autowired
	private MessageQueue messageQueue;

	/**
	 * @Title: sensorContent @Description: TODO(生成Json) @param @param
	 *         currentData @param @return 设定文件 @return String 返回类型 @throws
	 */
	private String sensorJson(String dtuCode, Sensor sensor) {
		String str = "";
		str += "[{'type':'0','unit':'mg/L','value':'" + sensor.getV0() + "','id':'00000001','companyid':'11' },";
		// str += "{'type':'1','unit':'%','value':'"+sensor.getV1()+"','id':'" +
		// dtuCode + "','companyid':'11' },";
		// str += "{'type':'2','unit':'℃','value':'"+sensor.getV2()+"','id':'" +
		// dtuCode + "','companyid':'11' },";
		// str += "{'type':'3','unit':'ppm','value':'"+sensor.getV3()+"','id':'"
		// + dtuCode + "','companyid':'11' },";
		// str += "{'type':'4','unit':'ppm','value':'"+sensor.getV4()+"','id':'"
		// + dtuCode + "','companyid':'11' },";
		str += "{'type':'5','unit':'℃','value':'" + sensor.getV5() + "','id':'00000002','companyid':'11' }]";
		// str += "{'type':'6','unit':'ppm','value':'"+sensor.getV6()+"','id':'"
		// + dtuCode + "','companyid':'11' },";
		// str += "{'type':'7','unit':'ppm','value':'"+sensor.getV7()+"','id':'"
		// + dtuCode + "','companyid':'11' },";
		// str += "{'type':'8','unit':'ppm','value':'"+sensor.getV8()+"','id':'"
		// + dtuCode + "','companyid':'11' },";
		// str += "{'type':'9','unit':'ppm','value':'"+sensor.getV9()+"','id':'"
		// + dtuCode + "','companyid':'11' }]";
		return str;
	}

	/**
	 * @throws IOException @throws ClientProtocolException @Title:
	 * postMethod @Description: TODO(Post) @param @param
	 * str @param @return @param @throws IOException 设定文件 @return HttpMethod
	 * 返回类型 @throws
	 */
	private String post(String str) throws ClientProtocolException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Jsondata", str);
		// map.put("password", "");

		HttpClient client = HttpClientUtils.getConnection();
		HttpUriRequest post = HttpClientUtils.getRequestMethod(map, url, "post");
		HttpResponse response = client.execute(post);

		String message = "";

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			message = EntityUtils.toString(entity, "utf-8");
			System.out.println(message);
		} else {
			System.out.println("请求失败");
		}
		return message;
	}
}
