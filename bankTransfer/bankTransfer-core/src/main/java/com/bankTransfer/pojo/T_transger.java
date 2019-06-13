package com.bankTransfer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 转账记录表
 * @author Administrator
 *
 */
@Setter
@Getter
@ToString
public class T_transger implements Serializable {

	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**转款人姓名*/
	private String t_name;
	/**转款人账号*/
	private String t_accout_number;
	/**转款时间*/
	private Date t_time;
	/**转款金额*/
	private Double t_amount;
	/**收款人姓名*/
	private String p_name;
	/**收款人账号*/
	private String p_accout_number;
	/**手续费*/
	private Double t_handfee;
	/**结果*/
	private String result;
	
	
	/**关联汇款人id*/
	private Integer t_id;
	/**转账类型*/
	private String transfer_nametype;
	/**交易类型*/
	private String transaction_type;
	/**备注*/
	private String msg;
	/**转账模式*/
	private String transfer_mode;
	

}
