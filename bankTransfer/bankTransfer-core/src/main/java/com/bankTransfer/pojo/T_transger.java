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
	private BigDecimal t_amount;
	/**收款人姓名*/
	private String p_name;
	/**收款人账号*/
	private String p_accout_number;
	/**手续费*/
	private BigDecimal t_handfee;
	/**结果*/
	private String result;

}
