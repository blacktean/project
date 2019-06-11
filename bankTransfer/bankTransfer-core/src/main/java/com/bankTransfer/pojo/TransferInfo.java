package com.bankTransfer.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
/**
 * 转账记录表
 * @author Administrator
 *
 */
public class TransferInfo {
	
	/** 主键  */ private Long id;
	/**  汇款人姓名 */ private String t_name;
	/**  汇款人卡号 */ private String t_accout_number;
	/** 汇款时间   */ private Date t_time;
	/**  汇款金额 */ private Float t_amount;
	/**  收款人姓名 */ private String p_name;
	/**  收款账号 */ private String p_accout_number;
	/**  手续费  */ private Float t_handfee;
	/**  结果 */ private String result;
	/** 关键汇款人id	  */ private Long t_id;
}
