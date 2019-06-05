package com.bankTransfer.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资金归集实体类
 * @author zsy
 *
 */
@Getter
@Setter
@ToString
public class CashSweep implements Serializable {
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**服务项目*/
	private String service_items;
	/**理财序号*/
	private String financial_number;
	/**卡(账)号,非空*/
	//private String card_id;
	/**服务生效时间*/
	private Date start_time;
	/**服务失效时间*/
	private Date end_time;
	/**当前状态*/
	private String state;
	/**收款账户*/
	private String collection_accout;
	/**收款户名*/
	private String receiver_name;
	/**归集方式*/
	private String collecting_type;
	/**归集卡号*/
	private String debit_account;
	/**扣款户名*/
	private String debit_name;
	/**所属银行*/
	private String bank_id;
	/**转入金额*/
	private Integer transferred_money;
	/**扣款户主手机号*/
	private String debit_tel;
}
