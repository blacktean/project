package com.bankTransfer.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.bankTransfer.pojo.JsonCountry;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.JsonUserInfo;

public class APIUtils {
	
	private static final String GET_METHOD = "GET";
	private static final String APP_CODE = "cb91bc7370ac40789335eeacdc4ddcb8";

	/**
	 * 根据ip获取城市
	 * 
	 * @param ip
	 * @return
	 */
	public static JsonCountry getJsonCountry(String ip) {
		String host = "https://ipro.market.alicloudapi.com";
		String path = "/ip";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", ip);
		JsonCountry jsonCountry = null;
		try {
			HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
			String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("result").toString();
			jsonCountry = JSONObject.parseObject(result, JsonCountry.class);
		} catch (Exception e) {
		}
		return jsonCountry;
	}

	/**
	 * 根据ip查询天气
	 * 
	 * @param ip
	 * @return
	 */
	public static String getWeather(String ip) {
		 String host = "https://ali-weather.showapi.com";
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
			HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
			return EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 查询税率
	 * @param money 要转换的金额
	 * @param from	要转换的金额代码
	 * @param to	转换之后的金额代码
	 * @return	
	 */
	public static JsonRate getRate(Float money,String from,String to) {
		 String host = "https://jisuhuilv.market.alicloudapi.com";
		    String path = "/exchange/convert";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + APP_CODE);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("amount", money.toString());
		    querys.put("from", from);
		    querys.put("to", to);
		    JsonRate jsonRate = null;
		    try {
		    	HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
		    	String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("result").toString();
		    	jsonRate = JSONObject.parseObject(result, JsonRate.class);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		return jsonRate;
	}
	/**
	 * 身份证号查询信息
	 * @param number
	 * @return
	 */
	public static  JsonUserInfo getUserInfo(String number) {
		 String host = "https://api10.aliyun.venuscn.com";
		    String path = "/id-card/query";
		    JsonUserInfo jsonUserInfo = null;
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + APP_CODE);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("number", number);
		    try {
		    	HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
		    	String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("data").toString();
		    	jsonUserInfo = JSONObject.parseObject(result, JsonUserInfo.class);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		return  jsonUserInfo;
	}
	
	/**
	 * 未完成
	 * @param phone
	 * @param content
	 * @return
	 */
	public static boolean sendMessage(String phone,String content) {
		String host = "https://chanyoo.market.alicloudapi.com";
        String path = "/sendsms";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + APP_CODE);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("content", content);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
            String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("result").toString();
            return result == "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public static void main(String[] args) {
		boolean sendMessage = sendMessage("18397809478","你好啊,测试内容");
		System.out.println(sendMessage);
	}

}
