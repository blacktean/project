package com.bankTransfer.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.bankTransfer.pojo.VerifyCodeVo;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * 发送验证码的工具类
 * @author suke
 *
 */

/*public class VerifyCodeUtil {
	private static int[] seeds = {0,1,2,3,4,5,6,7,8,9};
	public static void sendMsg(String phoneNumber) {
		String code = randomCode(6);
		VerifyCodeVo vo = new VerifyCodeVo();
		vo.setError_code(0);
		vo.setLastTime(new Date());
		vo.setPhoneNumber(phoneNumber);
		vo.setReason("操作成功");
		vo.setVerifyCode(code);
		System.out.println(code);
		UserContext.setCurrentVerifyCodeVo(vo);
		System.out.println(UserContext.getCurrentVerifyCodeVo());;
	}*/






public class VerifyCodeUtil {
	private static int[] seeds = {0,1,2,3,4,5,6,7,8,9};
	public static void sendMsg(String phoneNumber) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String code = randomCode(6);
		URI uri=null;
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobile", phoneNumber));
		params.add(new BasicNameValuePair("tpl_id", "157441"));
		params.add(new BasicNameValuePair("tpl_value", encode(code)));
		params.add(new BasicNameValuePair("key", "95856558f2b9be44130f422a2dd3636a"));
		try {
			uri=new URIBuilder().setPath("http://v.juhe.cn/sms/send").setParameters(params).build();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(uri);
		CloseableHttpResponse response = null;
		try {
			response=httpClient.execute(httpPost);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpEntity httpEntity = response.getEntity();
		
		try {
			String entity = EntityUtils.toString(httpEntity, "UTF-8");
			if(httpEntity!=null) {
				if(response.getStatusLine().getStatusCode()==200) {
					VerifyCodeVo vo = JSONObject.parseObject(entity,VerifyCodeVo.class );
					if(vo.getError_code()==0) {
						vo.setPhoneNumber(phoneNumber);
						vo.setLastTime(new Date());
						vo.setVerifyCode(code);
						UserContext.setCurrentVerifyCodeVo(vo);
					}
					else {
						throw new RuntimeException("发送失败");
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}
	/**
	 * 随机生成验证码
	 * @param length  验证码的长度
	 * @return
	 */
	private static String randomCode(int length){
		if(length <= 0){
			throw new IndexOutOfBoundsException("length:"+length);
		}
		StringBuffer  sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random()*seeds.length);
			sb.append(seeds[index]);
		}
		return sb.toString();
	}
	
	/**
	 * 对验证码进行url编码
	 * @param code 需要编码的内容
	 */
	private static String encode(String code){
		StringBuffer  sb = new StringBuffer("#code#=");
		sb.append(code);
		try {
			return URLEncoder.encode(sb.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		String code = randomCode(6);
		URI uri=null;
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobile", "13576342583"));
		params.add(new BasicNameValuePair("tpl_id", "158041"));
		params.add(new BasicNameValuePair("tpl_value", encode(code)));
		params.add(new BasicNameValuePair("key", "8eb169a1d96c4ae01dca2991067e83a6"));
		try {
			uri=new URIBuilder().setPath("http://v.juhe.cn/sms/send").setParameters(params).build();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(uri);
	}
}
