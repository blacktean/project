package com.bankTransfer.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JsonCountry implements Serializable{

	private static final long serialVersionUID = 1L;
//{"area":"","country":"中国","city":"苏州市","ip":"117.82.82.104",
//"isp":"电信","region_id":"320000","ipend":"117.82.82.255",
//"region":"江苏省","ipstart":"117.82.82.0","country_id":"CN","city_id":"320500"}
	private String area;
	/** 国家*/
	private String country;
	/** 省份*/
	private String region;
	/** 城市*/
	private String city;
	/** 本机ip*/
	private String ip;
	/** 网络服务商*/
	private String isp;
	/** 省份id*/
	private String region_id;
	/** 结束ip*/
	private String ipend;
	/** 开始ip*/
	private String ipstart;
	/** 国家id*/
	private String country_id;
	/** 城市id*/
	private String city_id;
	
}
