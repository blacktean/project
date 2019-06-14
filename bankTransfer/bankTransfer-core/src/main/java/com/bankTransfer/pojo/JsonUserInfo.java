package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 身份证查询到的信息的类
 * @author Administrator
 *
 */
@Setter
@Getter
@ToString
public class JsonUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 用户身份证详细地址*/
	private String area; 
	/** 用户身份证号*/
	private String number;
	/** 地址id*/
	private Integer addrcode;
	/** 省份*/
	private String province;
	/** 城市*/
	private String	city;
	/** 镇/乡/区*/
	private String region;
	/** 性别*/
	private String sex;
	/** 身份证长度*/
	private Integer length;
	/** 出生年月*/
	private Date birth;
	/** 年龄*/
	private String age;
	
	
}
