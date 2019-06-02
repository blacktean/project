package com.bankTransfer.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.bankTransfer.pojo.JsonCountry;

public class APIUtils {
	private static final String HOST = "https://ipro.market.alicloudapi.com"; 
	private static final String  GET_METHOD = "GET";
	private static final String APP_CODE = "cb91bc7370ac40789335eeacdc4ddcb8";
	
	/**
	 * 根据ip获取城市
	 * @param ip
	 * @return
	 */
	public static JsonCountry getJsonCountry(String ip) {
		String path = "/ip";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", ip);
		JsonCountry jsonCountry = null;
		try {
			HttpResponse response = HttpUtils.doGet(HOST, path, GET_METHOD, headers, querys);
			String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("result").toString();
			jsonCountry = JSONObject.parseObject(result, JsonCountry.class);
		} catch (Exception e) {
		}
		return jsonCountry;
	}
	
   //https://api.jisuapi.com/weather/query/1
	/**
	 * 根据ip查询天气
	 * @param ip
	 * @return
	 */
	public static JsonCountry getWeather(String ip) {
		    String path = "/ip-to-weather";
		    Map<String, String> headers = new HashMap<String, String>();
		    headers.put("Authorization", "APPCODE " + APP_CODE);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("ip", ip);
		    querys.put("need3HourForcast", "0");
		    querys.put("needAlarm", "0");
		    querys.put("needHourData", "0");
		    querys.put("needIndex", "0");
		    querys.put("needMoreDay", "0");
		    try {
		    	HttpResponse response = HttpUtils.doGet(HOST, path, GET_METHOD, headers, querys);
		    	System.out.println(response.getEntity().toString());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    
		    return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
