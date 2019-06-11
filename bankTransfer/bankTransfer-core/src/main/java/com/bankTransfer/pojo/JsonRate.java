package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
/**
 * 税率实体类
 * @author Administrator
 *
 */
public class JsonRate implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 税率*/
	private Float rate;
	/** 转换后的金额名*/
	private String toname;
	/** 需要支付的税额*/
	private BigDecimal camount;
	/** 要转换的金额代码*/
	private String from;
	/** 转换后的金额代码*/
	private String to;
	/** 转换时间*/
	private Date updatetime;
	/** 要转换的金额名*/
	private String fromname;
	
}
