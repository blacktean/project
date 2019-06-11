package com.bankTransfer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.bankTransfer.pojo.JsonCountry;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.JsonUserInfo;

public class APIUtils {
	/**
	 * 验证码数组 
	 */
	private static int[] seeds = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	/**
	 * get传递方式
	 */
	private static final String GET_METHOD = "GET";
	/**
	 * 唯一标识符
	 */
	private static final String APP_CODE = "cb91bc7370ac40789335eeacdc4ddcb8";

	/**
	 * 根据ip获取城市
	 * 
	 * @param ip
	 * @return 
	 */
	public static JsonCountry getJsonCountry() {
		String host = "https://ipro.market.alicloudapi.com";
		String path = "/ip";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", getMyIP());
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
	public static String getWeather() {
		String host = "https://ali-weather.showapi.com";
		String path = "/area-to-weather";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("areaid", getMyIP());
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
	 * 
	 * @param money 要转换的金额
	 * @param from  要转换的金额代码
	 * @param to    转换之后的金额代码
	 * @return
	 */
	public static JsonRate getRate(Float money, String from, String to) {
		String host = "https://jisuhuilv.market.alicloudapi.com";
		String path = "/exchange/convert";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
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
	 * 
	 * @param number
	 * @return
	 */
	public static JsonUserInfo getUserInfo(String number) {
		String host = "https://api10.aliyun.venuscn.com";
		String path = "/id-card/query";
		JsonUserInfo jsonUserInfo = null;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("number", number);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
			String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("data").toString();
			System.err.println(result);
			jsonUserInfo = JSONObject.parseObject(result, JsonUserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonUserInfo;
	}
	/**
	 * 验证身份证号和姓名
	 * @param number
	 * @param name
	 * @return
	 */
	public static boolean checkNumberAndName(String number,String name) {
		 String host = "https://eid.shumaidata.com";
		    String path = "/eid/check";
		    Map<String, String> headers = new HashMap<String, String>();
		    headers.put("Authorization", "APPCODE " + APP_CODE);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("idcard", number);
		    querys.put("name", name);
		    Map<String, String> bodys = new HashMap<String, String>();
		    try {
		    	HttpResponse response = HttpUtils.doPost(host, path, GET_METHOD, headers, querys, bodys);
		    	String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("code").toString();
		    	return "0".equals(result);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return false;
	}
	

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	public static String sendMessage(String phone) {
		String host = "https://duanxi.market.alicloudapi.com";
		String path = "/sendSms";
		String code = randomCode(4);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", phone);
		querys.put("content", "{\"userName\":\"用户\",\"code\":\"" + code + "\",\"minute\":\"5\",\"comName\":\",祝您生活愉快\"}");
		querys.put("tNum", "T150606060602");
		try {
			HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
			if(response.getEntity() != null) {
				return code;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 银行卡二要素认证
	 * 
	 * @param name
	 * @param id_card
	 * @return
	 */
	public static String checkCard(String name, String id_card) {
		String host = "https://ali-bankcard4.showapi.com";
		String path = "/bank2";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + APP_CODE);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("acct_name", name);
		querys.put("acct_pan", id_card);
		querys.put("needBelongArea", "true");
		try {
			HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
			String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("showapi_res_body")
					.toString();
			Object object = JSONObject.parseObject(result).get("belong");
			if(object!=null) {
				return "success";
			}
			
		} catch (Exception e) {
		}
		return null;
	}
	
	
	/**
	 * 银行卡三要素认证
	 * @param name	 姓名
	 * @param cardNumber 身份证号
	 * @param id_card  卡号
	 * @return
	 */
	public static String checkCard(String name,String cardNumber,String id_card) {
		 String host = "https://yunyidata3.market.alicloudapi.com";
		    String path = "/bankAuthenticate3";
		    Map<String, String> headers = new HashMap<String, String>();
		    headers.put("Authorization", "APPCODE " + "3d166e9ea0704096be40b93135f7e12f");
		    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		    Map<String, String> querys = new HashMap<String, String>();
		    Map<String, String> bodys = new HashMap<String, String>();
		    bodys.put("cardNo", id_card);
		    bodys.put("idNo", cardNumber);
		    bodys.put("name", name);


		    try {
		    	HttpResponse response = HttpUtils.doGet(host, path, GET_METHOD, headers, querys);
		    	String result = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).get("respCode")
						.toString();
		    	if("0000".equals(result)) {
		    		return "success";
		    	}else {
		    		return null;
		    	}
		    } catch (Exception e) {
		    	return null;
		    }
	}
	
	
	/**
	 * 随机生成验证码
	 * 
	 * @param length 验证码的长度
	 * @return
	 */
	public static String randomCode(int length) {
		if (length <= 0) {
			throw new IndexOutOfBoundsException("length:" + length);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * seeds.length);
			sb.append(seeds[index]);
		}
		return sb.toString();
	}
	
	
	
	
	
	/**
	 * 获取我的ip
	 * @return
	 */
	public static String getMyIP()  {
        InputStream ins = null;
        try {
            String l = "http://www.ip138.com/ips1388.asp";
            URL url = new URL(l);
            URLConnection con = url.openConnection();
            ins = con.getInputStream();
            InputStreamReader isReader = new InputStreamReader(ins, "gb2312");
            BufferedReader bReader = new BufferedReader(isReader);
            StringBuffer webContent = new StringBuffer();
            String str = null;
            while ((str = bReader.readLine()) != null) {
                webContent.append(str);
            }
            int start = webContent.indexOf("您的IP地址是：[") + 9;
            int end = webContent.indexOf("] 来自：");
            return webContent.substring(start, end);
        } catch (IOException e) {
			e.printStackTrace();
		} finally {
                try {
                	 if (ins != null) {
					ins.close();
                	 }
				} catch (IOException e) {
					e.printStackTrace();
				}
            
        }
        return null;
    }
	

}
